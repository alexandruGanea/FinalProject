package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.TravelPackage;

@Repository

public class TravelPackageDAO {

    public void insertTravelPackage(TravelPackage travelPackage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(travelPackage);
        session.close();
    }
}
