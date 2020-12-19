package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Continent;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class ContinentDAO {

    public void insertContinent(Continent continent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(continent);
        session.getTransaction().commit();
        session.close();
    }

    public void insertContinent(Session session, Continent continent) {
        session.beginTransaction();
        session.save(continent);
        session.getTransaction().commit();
    }


    public void updateContinentName(String oldName, String newName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateContinentName = session.createNamedQuery("updateContinentName");
        updateContinentName.setParameter("oldName", oldName);
        updateContinentName.setParameter("newName", newName);
        int rowsAffected = updateContinentName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public Continent findContinentByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectContinentByName = session.createNamedQuery("selectContinentByName");
        selectContinentByName.setParameter("name", name);
        Continent foundContinent = null;
        try {
            foundContinent = (Continent) selectContinentByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundContinent;
    }

    public Continent findContinentByName(Session session, String name) {
        session.beginTransaction();
        Query selectContinentByName = session.createNamedQuery("selectContinentByName");
        selectContinentByName.setParameter("name", name);
        Continent foundContinent = null;
        try {
            foundContinent = (Continent) selectContinentByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundContinent;
    }


    public Integer findContinentIdByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectContinentIdByName = session.createNamedQuery("selectContinentIdByName");
        selectContinentIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) (selectContinentIdByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findContinentIdByName(Session session, String name) {
        session.beginTransaction();
        Query selectContinentIdByName = session.createNamedQuery("selectContinentIdByName");
        selectContinentIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) (selectContinentIdByName.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public void deleteContinentByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteContinentByName = session.createNamedQuery("deleteContinentByName");
        deleteContinentByName.setParameter("name", name);
        int rowsAffected = deleteContinentByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }
}
