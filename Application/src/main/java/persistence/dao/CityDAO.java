package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.City;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class CityDAO {

    public void insertCity(City city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
        session.close();
    }

    public void insertCity(Session session, City city) {
        session.beginTransaction();
        session.save(city);
        session.getTransaction().commit();
    }

    public City findCityByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectCityByName = session.createNamedQuery("selectCityByName");
        selectCityByName.setParameter("name", name);
        City city = null;
        try {
            city = (City) (selectCityByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return city;
    }

    public City findCityByName(Session session, String name) {
        session.beginTransaction();
        Query selectCityByName = session.createNamedQuery("selectCityByName");
        selectCityByName.setParameter("name", name);
        City city = null;
        try {
            city = (City) (selectCityByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return city;
    }

    public Integer findCityIdByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectCityIdByName = session.createNamedQuery("selectCityIdByName");
        selectCityIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectCityIdByName.getSingleResult();
        } catch (NoResultException e) {
            e.getMessage();
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findCityIdByName(Session session, String name) {
        session.beginTransaction();
        Query selectCityIdByName = session.createNamedQuery("selectCityIdByName");
        selectCityIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectCityIdByName.getSingleResult();
        } catch (NoResultException e) {
            e.getMessage();
        }
        session.getTransaction().commit();
        return foundId;
    }

    public List<City> findCitiesByCountryName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectCitiesByCountryName = session.createNamedQuery("selectCitiesByCountryName");
        selectCitiesByCountryName.setParameter("name", name);
        List<City> foundCitiesList = selectCitiesByCountryName.getResultList();
        return foundCitiesList;
    }

    public void changeCityName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateCityName = session.createNamedQuery("updateCityName");
        updateCityName.setParameter("oldName", oldName);
        updateCityName.setParameter("newName", newName);
        int rowsAffected = updateCityName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCityByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteCityByName = session.createNamedQuery("deleteCityByName");
        deleteCityByName.setParameter("name", name);
        int rowsAffected = deleteCityByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }


}
