package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Hotel;
import persistence.entities.TravelPackage;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class TravelPackageDAO {

    public void insertTravelPackage(TravelPackage travelPackage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(travelPackage);
        session.getTransaction().commit();
        session.close();
    }

    public void insertTravelPackage(Session session, TravelPackage travelPackage) {
        session.beginTransaction();
        session.save(travelPackage);
        session.getTransaction().commit();
    }

    public Integer findTravelPackageIdByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectUniqueTravelPackageId = session.createNamedQuery("selectTravelPackageIdByName");
        selectUniqueTravelPackageId.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) (selectUniqueTravelPackageId.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public List<Integer> findTravelPackageIdByName(Session session, String name) {
        session.beginTransaction();
        Query selectUniqueTravelPackageId = session.createNamedQuery("selectTravelPackageIdByName");
        selectUniqueTravelPackageId.setParameter("name", name);
        List foundId = null;
        try {
            foundId = selectUniqueTravelPackageId.getResultList();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public TravelPackage findTravelPackageByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectTravelPackageByName = session.createNamedQuery("selectTravelPackageByName");
        selectTravelPackageByName.setParameter("name", name);
        TravelPackage foundTravelPackage = null;
        try {
            foundTravelPackage = (TravelPackage) selectTravelPackageByName.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return foundTravelPackage;
    }

    public TravelPackage findTravelPackageByName(Session session, String name) {
        session.beginTransaction();
        Query selectTravelPackageByName = session.createNamedQuery("selectTravelPackageByName");
        selectTravelPackageByName.setParameter("name", name);
        TravelPackage foundTravelPackage = null;
        try {
            foundTravelPackage = (TravelPackage) selectTravelPackageByName.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        return foundTravelPackage;
    }

    public Integer findHotelIdByTravelPackageName(Session session, String travelPackageName) {
        session.beginTransaction();
        Query selectHotelIdByTravelPackageName = session.createNamedQuery("selectHotelIdByTravelPackageName");
        selectHotelIdByTravelPackageName.setParameter("travelPackageName", travelPackageName);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectHotelIdByTravelPackageName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public void updateTravelPackageStockByName(Session session, String name, int soldItems){
        session.beginTransaction();
        Query updateTravelPackageStockByName = session.createNamedQuery("updateTravelPackageStockByName");
        updateTravelPackageStockByName.setParameter("name", name);
        updateTravelPackageStockByName.setParameter("soldItems", soldItems);
        int rowsAffected = updateTravelPackageStockByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
    }

    public void updateTravelPackageStockByName(String name, int soldItems){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateTravelPackageStockByName = session.createNamedQuery("updateTravelPackageStockByName");
        updateTravelPackageStockByName.setParameter("name", name);
        updateTravelPackageStockByName.setParameter("soldItems", soldItems);
        int rowsAffected = updateTravelPackageStockByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public List<TravelPackage> findTravelPackagesByAirportOfDeparture(String airportName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectTravelPackagesByAirportOfDeparture = session.createNamedQuery("selectTravelPackagesByAirportOfDeparture");
        selectTravelPackagesByAirportOfDeparture.setParameter("airportName", airportName);
        List foundTravelPackages = null;
        try {
            foundTravelPackages = selectTravelPackagesByAirportOfDeparture.getResultList();
        }catch(NoResultException e){
            e.printStackTrace();
            }
        session.getTransaction().commit();
        session.close();
        return foundTravelPackages;
    }

    public List<TravelPackage> findTravelPackagesByHotel(String hotelName){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectTravelPackagesByHotel = session.createNamedQuery("selectTravelPackagesByHotel");
        selectTravelPackagesByHotel.setParameter("hotelName", hotelName);
        List foundTravelPackages = null;
        try {
            foundTravelPackages = selectTravelPackagesByHotel.getResultList();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return foundTravelPackages;
    }


}
