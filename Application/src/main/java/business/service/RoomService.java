package business.service;

import business.dto.HotelDTO;
import business.dto.RoomDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.HotelDAO;
import persistence.dao.RoomDAO;
import persistence.entities.Hotel;
import persistence.entities.Room;

import java.util.List;
import java.util.Set;

@Service
public class RoomService {

    @Autowired
    RoomDAO roomDAO;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    HotelService hotelService;

    public Room insertRoom(RoomDTO roomDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Room room = new Room(roomDTO.getRoomType());
        room.setHotelContainingRoom(checkForHotel(session, roomDTO));
        room.setMaxGuests(roomDTO.getMaxGuests());
        room.setGuestPrice(roomDTO.getGuestPrice());
        room.setAvailableRooms(roomDTO.getAvailableRooms());
        roomDAO.insertRoom(session, room);
        session.close();
        return room;
    }

    public Room insertRoom(Session session, RoomDTO roomDTO) {
        Room room = new Room(roomDTO.getRoomType());
        room.setHotelContainingRoom(checkForHotel(session, roomDTO));
        room.setMaxGuests(roomDTO.getMaxGuests());
        room.setGuestPrice(roomDTO.getGuestPrice());
        room.setAvailableRooms(roomDTO.getAvailableRooms());
        roomDAO.insertRoom(session, room);
        return room;
    }


    private Hotel checkForHotel(Session session, RoomDTO roomDTO) {
        if (hotelService.isInserted(session, roomDTO.getHotelDTO())) {
            return hotelDAO.findHotelByNameAndCity(session, roomDTO.getHotelDTO().getName(), roomDTO.getHotelDTO().getCityDTO().getName());
        } else {
            return hotelService.insertHotel(session, roomDTO.getHotelDTO());
        }
    }

    public boolean isInserted(RoomDTO roomDTO) {
        Integer hotelId = hotelDAO.findHotelIdByNameAndCity(roomDTO.getHotelDTO().getName(), roomDTO.getHotelDTO().getCityDTO().getName());
        Integer idFound = roomDAO.findRoomIdByTypeAndHotelId(roomDTO.getRoomType(), hotelId);
        return idFound != 0;
    }

    public boolean isInserted(Session session, RoomDTO roomDTO) {
        Integer hotelId = hotelDAO.findHotelIdByNameAndCity(session, roomDTO.getHotelDTO().getName(), roomDTO.getHotelDTO().getCityDTO().getName());
        Integer idFound = roomDAO.findRoomIdByTypeAndHotelId(session, roomDTO.getRoomType(), hotelId);
        return idFound != 0;
    }

    public RoomDTO findRoomByTypeAndHotelId(Session session, String roomType, HotelDTO hotelDTO) {
        Hotel foundHotel = hotelDAO.findHotelByNameAndCity(hotelDTO.getName(), hotelDTO.getCityDTO().getName());
        Room foundRoom = roomDAO.findRoomByTypeAndHotelId(session, roomType, foundHotel.getId());
        return setRoomDTO(foundRoom);
    }

    void updateStocks(Session session, RoomDTO roomDTO) {
        roomDAO.updateRoomAvailabilityById(session, roomDTO.getRoomId());
    }


    RoomDTO setRoomDTO(Room room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setAvailableRooms(room.getAvailableRooms());
        roomDTO.setGuestPrice(room.getGuestPrice());
        roomDTO.setMaxGuests(room.getMaxGuests());
        roomDTO.setRoomId(room.getId());
        return roomDTO;
    }

}
