package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Purchase;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class PurchaseDAO {

    public void insertPurchase(Purchase purchase) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
        session.close();
    }

    public void insertPurchase(Session session, Purchase purchase) {
        session.beginTransaction();
        session.save(purchase);
        session.getTransaction().commit();
    }

    public List<Purchase> findPurchasesByPrice(double price) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectPurchasesByPrice = session.createNamedQuery("selectPurchasesByPrice");
        selectPurchasesByPrice.setParameter("price", price);
        List<Purchase> purchasesByValueList = null;
        try {
            purchasesByValueList = selectPurchasesByPrice.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return purchasesByValueList;
    }

    public List<Purchase> findPurchasesByPrice(Session session, double price) {
        session.beginTransaction();
        Query selectPurchasesByPrice = session.createNamedQuery("selectPurchasesByPrice");
        selectPurchasesByPrice.setParameter("price", price);
        List<Purchase> purchasesByValueList = null;
        try {
            purchasesByValueList = selectPurchasesByPrice.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        return purchasesByValueList;
    }

    public List<Purchase> findPurchasesByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectPurchasesByDate = session.createNamedQuery("selectPurchasesByDate");
        selectPurchasesByDate.setParameter("date", date);
        List<Purchase> purchasesByDateList = null;
        try {
            purchasesByDateList = selectPurchasesByDate.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return purchasesByDateList;
    }

    public List<Purchase> findPurchaseByDate(Session session, String date) {
        session.beginTransaction();
        Query selectPurchasesByDate = session.createNamedQuery("selectPurchasesByDate");
        selectPurchasesByDate.setParameter("date", date);
        List<Purchase> purchasesByDateList = null;
        try {
            purchasesByDateList = selectPurchasesByDate.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        return purchasesByDateList;
    }


}
