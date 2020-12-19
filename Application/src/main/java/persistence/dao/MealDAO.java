package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Meal;

import javax.persistence.NoResultException;

@Repository
public class MealDAO {

    public void insertMeal(Meal meal) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(meal);
        session.getTransaction().commit();
        session.close();
    }

    public void insertMeal(Session session, Meal meal) {
        session.beginTransaction();
        session.save(meal);
        session.getTransaction().commit();
    }

    public Meal findMealByHotelId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectMealByHotelId = session.createNamedQuery("selectMealByHotelId");
        selectMealByHotelId.setParameter("id", id);
        Meal foundMeal = null;
        try {
            foundMeal = (Meal) selectMealByHotelId.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundMeal;
    }

    public Meal findMealByHotelId(Session session, int id) {
        session.beginTransaction();
        Query selectMealByHotelId = session.createNamedQuery("selectMealByHotelId");
        selectMealByHotelId.setParameter("id", id);
        Meal foundMeal = null;
        try {
            foundMeal = (Meal) selectMealByHotelId.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundMeal;
    }

    public Integer findMealIdByHotelId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectMealIdByHotelId = session.createNamedQuery("selectMealIdByHotelId");
        selectMealIdByHotelId.setParameter("id", id);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectMealIdByHotelId.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findMealIdByHotelId(Session session, int id) {
        session.beginTransaction();
        Query selectMealIdByHotelId = session.createNamedQuery("selectMealIdByHotelId");
        selectMealIdByHotelId.setParameter("id", id);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectMealIdByHotelId.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

}
