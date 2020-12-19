package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Purchase;

@Repository

public class PurchaseDAO {

    public void insertSession(Purchase purchase){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.save(purchase);
    }
}
