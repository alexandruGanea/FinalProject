package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Airport;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository

public class AirportDAO {

    public void insertAirport(Airport airport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(airport);
        session.close();
    }

    public void insertAirport(Session session, Airport airport) {
        session.save(airport);
    }

    public Airport findAirportByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectAirportByName = session.createNamedQuery("selectAirportByName");
        selectAirportByName.setParameter("name", name);
        Airport airport = null;
        try {
            airport = (Airport) (selectAirportByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return airport;
    }

    public Airport findAirportByName(Session session, String name) {
        session.beginTransaction();
        Query selectAirportByName = session.createNamedQuery("selectAirportByName");
        selectAirportByName.setParameter("name", name);
        Airport airport = null;
        try {
            airport = (Airport) (selectAirportByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return airport;
    }

    public Integer findAirportIdByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectAirportIdByName = session.createNamedQuery("selectAirportIdByName");
        selectAirportIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectAirportIdByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findAirportIdByName(Session session, String name) {
        session.beginTransaction();
        Query selectAirportIdByName = session.createNamedQuery("selectAirportIdByName");
        selectAirportIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectAirportIdByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public List<Airport> findAirportByCityName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectAirportByCityName = session.createNamedQuery("selectAirportByCityName");
        selectAirportByCityName.setParameter("name", name);
        List<Airport> foundAirports = selectAirportByCityName.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundAirports;
    }

    public void changeAirportName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateAirportName = session.createNamedQuery("updateAirportName");
        updateAirportName.setParameter("oldName", oldName);
        updateAirportName.setParameter("newName", newName);
        int rowsAffected = updateAirportName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteAirportByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteAirportByName = session.createNamedQuery("deleteAirportByName");
        deleteAirportByName.setParameter("name", name);
        int rowsAffected = deleteAirportByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }
}
