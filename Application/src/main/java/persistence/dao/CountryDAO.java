package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Country;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class CountryDAO {

    public void insertCountry(Country countryToInsert) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(countryToInsert);
        session.getTransaction().commit();
        session.close();
    }

    public void insertCountry(Session session, Country countryToInsert) {
        session.beginTransaction();
        session.save(countryToInsert);
        session.getTransaction().commit();
    }

    public Country findCountryByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectCountryByName = session.createNamedQuery("selectCountryByName");
        selectCountryByName.setParameter("name", name);
        Country foundCountries = null;
        try {
            foundCountries = (Country) (selectCountryByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundCountries;
    }

    public Country findCountryByName(Session session, String name) {
        session.beginTransaction();
        Query selectCountryByName = session.createNamedQuery("selectCountryByName");
        selectCountryByName.setParameter("name", name);
        Country foundCountries = null;
        try {
            foundCountries = (Country) (selectCountryByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundCountries;
    }

    public List<Country> findCountriesByContinentName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectCountriesByContinentName = session.createNamedQuery("selectCountriesByContinentName");
        selectCountriesByContinentName.setParameter("name", name);
        List<Country> countriesOnContinent = selectCountriesByContinentName.getResultList();
        session.getTransaction().commit();
        session.close();
        return countriesOnContinent;
    }

    public Integer findCountryIdByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectCountryIdByName = session.createNamedQuery("selectCountryIdByName");
        selectCountryIdByName.setParameter("name", name);
        Integer idFound = 0;
        try {
            idFound = (Integer) selectCountryIdByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return idFound;
    }

    public Integer findCountryIdByName(Session session, String name) {
        session.beginTransaction();
        Query selectCountryIdByName = session.createNamedQuery("selectCountryIdByName");
        selectCountryIdByName.setParameter("name", name);
        Integer idFound = 0;
        try {
            idFound = (Integer) selectCountryIdByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return idFound;
    }

    public void deleteCountryByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteCountryByName = session.createNamedQuery("deleteCountryByName");
        deleteCountryByName.setParameter("name", name);
        int rowsAffected = deleteCountryByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void updateCountryName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateCountryByName = session.createNamedQuery("updateCountryName");
        updateCountryByName.setParameter("oldName", oldName);
        updateCountryByName.setParameter("newName", newName);
        int rowsAffected = updateCountryByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }
}
