package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Room;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class RoomDAO {

    public void insertRoom(Room room) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
        session.close();
    }

    public void insertRoom(Session session, Room room) {
        session.beginTransaction();
        session.save(room);
        session.getTransaction().commit();
    }

    public List<Room> findRoomByType(String roomType) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findRoomByType = session.createNamedQuery("selectRoomByType");
        findRoomByType.setParameter("roomType", roomType);
        List<Room> foundRooms = findRoomByType.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundRooms;
    }

    public Room findRoomByTypeAndHotelId(String roomType, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectRoomByTypeAndHotel = session.createNamedQuery("selectRoomByTypeAndHotelId");
        selectRoomByTypeAndHotel.setParameter("roomType", roomType);
        selectRoomByTypeAndHotel.setParameter("id", id);
        Room room = null;
        try {
            room = (Room) selectRoomByTypeAndHotel.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return room;
    }

    public Room findRoomByTypeAndHotelId(Session session , String roomType, int id) {
        session.beginTransaction();
        Query selectRoomByTypeAndHotel = session.createNamedQuery("selectRoomByTypeAndHotelId");
        selectRoomByTypeAndHotel.setParameter("roomType", roomType);
        selectRoomByTypeAndHotel.setParameter("id", id);
        Room room = null;
        try {
            room = (Room) selectRoomByTypeAndHotel.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return room;
    }

    public Integer findRoomIdByTypeAndHotelId(String roomType, int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectRoomIdByTypeAndHotel = session.createNamedQuery("selectRoomIdByTypeAndHotelId");
        selectRoomIdByTypeAndHotel.setParameter("roomType", roomType);
        selectRoomIdByTypeAndHotel.setParameter("id", id);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectRoomIdByTypeAndHotel.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findRoomIdByTypeAndHotelId(Session session, String roomType, int id) {
        Query selectRoomIdByTypeAndHotel = session.createNamedQuery("selectRoomIdByTypeAndHotelId");
        selectRoomIdByTypeAndHotel.setParameter("roomType", roomType);
        selectRoomIdByTypeAndHotel.setParameter("id", id);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectRoomIdByTypeAndHotel.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public Room findRoomByTypeAndHotel(Session session, String roomType, String hotelName) {
        session.beginTransaction();
        Query selectRoomByTypeAndHotel = session.createNamedQuery("selectRoomByTypeAndHotel");
        selectRoomByTypeAndHotel.setParameter("roomType", roomType);
        selectRoomByTypeAndHotel.setParameter("hotelName", hotelName);
        Room room = null;
        try {
            room = (Room) selectRoomByTypeAndHotel.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return room;
    }

    public List<Room> findRoomByPrice(int minPrice, int maxPrice) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query findRoomByPrice = session.createNamedQuery("selectRoomByPrice");
        findRoomByPrice.setParameter("minPrice", minPrice);
        findRoomByPrice.setParameter("maxPrice", maxPrice);
        List<Room> foundRooms = findRoomByPrice.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundRooms;
    }


}
