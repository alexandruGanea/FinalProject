package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.City;
import persistence.entities.Hotel;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class HotelDAO {

    public void insertHotel(Hotel hotel) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(hotel);
        session.getTransaction().commit();
        session.close();
    }

    public void insertHotel(Session session, Hotel hotel) {
        session.beginTransaction();
        session.save(hotel);
        session.getTransaction().commit();
    }

    public List<Hotel> findHotelsByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectHotelByName = session.createNamedQuery("selectHotelByName");
        selectHotelByName.setParameter("name", name);
        List<Hotel> foundHotelsList = selectHotelByName.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundHotelsList;
    }

    public List<Hotel> findHotelsByName(Session session, String name) {
        session.beginTransaction();
        Query selectHotelByName = session.createNamedQuery("selectHotelByName");
        selectHotelByName.setParameter("name", name);
        List<Hotel> foundHotelsList = selectHotelByName.getResultList();
        session.getTransaction().commit();
        return foundHotelsList;
    }

    public Hotel findHotelByNameAndCity(String hotelName, String cityName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectHotelByNameAndCity = session.createNamedQuery("selectHotelByNameAndCity");
        selectHotelByNameAndCity.setParameter("hotelName", hotelName);
        selectHotelByNameAndCity.setParameter("cityName", cityName);
        Hotel foundHotel = null;
        try {
            foundHotel = (Hotel) selectHotelByNameAndCity.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundHotel;
    }

    public Hotel findHotelById(Session session, int hotelId) {
        session.beginTransaction();
        Query selectHotelById = session.createNamedQuery("selectHotelById");
        selectHotelById.setParameter("hotelId", hotelId);
        Hotel foundHotel = null;
        try {
            foundHotel = (Hotel) selectHotelById.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        return foundHotel;
    }

    public Hotel findHotelByNameAndCity(Session session, String hotelName, String cityName) {
        session.beginTransaction();
        Query selectHotelByNameAndCity = session.createNamedQuery("selectHotelByNameAndCity");
        selectHotelByNameAndCity.setParameter("hotelName", hotelName);
        selectHotelByNameAndCity.setParameter("cityName", cityName);
        Hotel foundHotel = null;
        try {
            foundHotel = (Hotel) selectHotelByNameAndCity.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundHotel;
    }

    public Integer findHotelIdByNameAndCity(String hotelName, String cityName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectHotelIdByNameAndCity = session.createNamedQuery("selectHotelIdByNameAndCity");
        selectHotelIdByNameAndCity.setParameter("hotelName", hotelName);
        selectHotelIdByNameAndCity.setParameter("cityName", cityName);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectHotelIdByNameAndCity.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findHotelIdByNameAndCity(Session session, String hotelName, String cityName) {
        session.beginTransaction();
        Query selectHotelIdByNameAndCity = session.createNamedQuery("selectHotelIdByNameAndCity");
        selectHotelIdByNameAndCity.setParameter("hotelName", hotelName);
        selectHotelIdByNameAndCity.setParameter("cityName", cityName);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectHotelIdByNameAndCity.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public List<Hotel> findHotelsByCityName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectHotelByCityName = session.createNamedQuery("selectHotelByCityName");
        selectHotelByCityName.setParameter("name", name);
        List<Hotel> foundHotels = selectHotelByCityName.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundHotels;
    }

    public void updateHotelName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateHotelName = session.createNamedQuery("updateHotelName");
        updateHotelName.setParameter("oldName", oldName);
        updateHotelName.setParameter("newName", newName);
        int rowsAffected = updateHotelName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void updateHotelRating(int oldRating, int newRating) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateHotelRating = session.createNamedQuery("updateHotelRating");
        updateHotelRating.setParameter("oldRating", oldRating);
        updateHotelRating.setParameter("newRating", newRating);
        int rowsAffected = updateHotelRating.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void updateHotelDescription(String oldDescription, String newDescription) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateHotelDescription = session.createNamedQuery("updateHotelDescription");
        updateHotelDescription.setParameter("oldDescription", oldDescription);
        updateHotelDescription.setParameter("newDescription", newDescription);
        int rowsAffected = updateHotelDescription.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteHotelByNameAndCity(String name, City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteHotel = session.createNamedQuery("deleteHotelByNameAndCity");
        deleteHotel.setParameter("name", name);
        deleteHotel.setParameter("city", city);
        int rowsAffected = deleteHotel.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public List<Hotel> findHotelByRoomTypeAndAvailability(String roomType, String roomAvailability) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectHotelByRoomTypeAndAvailability = session.createNamedQuery("selectHotelByRoomTypeAndAvailability");
        selectHotelByRoomTypeAndAvailability.setParameter("roomType", roomType);
        selectHotelByRoomTypeAndAvailability.setParameter("roomAvailability", roomAvailability);
        List<Hotel> foundHotels = selectHotelByRoomTypeAndAvailability.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundHotels;
    }
}
