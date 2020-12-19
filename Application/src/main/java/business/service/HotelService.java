package business.service;

import business.dto.HotelDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.CityDAO;
import persistence.dao.HotelDAO;
import persistence.entities.City;
import persistence.entities.Hotel;

@Service
public class HotelService {

    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CityService cityService;

    public void insertHotel(HotelDTO hotelDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Hotel hotel = new Hotel(hotelDTO.getName());
        hotel.setCityContainingHotel(checkForCity(session, hotelDTO));
        hotel.setHotelRating(hotelDTO.getRating());
        hotel.setDescription(hotelDTO.getDescription());
        hotelDAO.insertHotel(session, hotel);
        session.close();
    }

    public Hotel insertHotel(Session session, HotelDTO hotelDTO) {
        Hotel hotel = new Hotel(hotelDTO.getName());
        hotel.setCityContainingHotel(checkForCity(session, hotelDTO));
        hotel.setHotelRating(hotelDTO.getRating());
        hotel.setDescription(hotelDTO.getDescription());
        hotelDAO.insertHotel(session, hotel);
        return hotel;
    }

    private City checkForCity(Session session, HotelDTO hotelDTO) {
        if (cityService.isInserted(session, hotelDTO.getCityDTO())) {
            return cityDAO.findCityByName(session, hotelDTO.getCityDTO().getName());
        } else {
            return cityService.insertCity(session, hotelDTO.getCityDTO());
        }
    }

    public boolean isInserted(HotelDTO hotelDTO) {
        Integer idFound = hotelDAO.findHotelIdByNameAndCity(hotelDTO.getName(), hotelDTO.getCityDTO().getName());
        return idFound != 0;
    }

    public boolean isInserted(Session session, HotelDTO hotelDTO) {
        Integer idFound = hotelDAO.findHotelIdByNameAndCity(session, hotelDTO.getName(), hotelDTO.getCityDTO().getName());
        return idFound != 0;
    }

}
