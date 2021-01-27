package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Client;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository

public class ClientDAO {

    public void insertClient(Client client) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        session.close();
    }

    public void insertClient(Session session, Client client) {
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
    }

    public void deleteClientByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteClientByEmail = session.createNamedQuery("deleteClientByEmail");
        deleteClientByEmail.setParameter("email", email);
        int rowsAffected = deleteClientByEmail.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public Client findClientByAccountId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectClientByAccountId = session.createNamedQuery("selectClientByAccountId");
        selectClientByAccountId.setParameter("id", id);
        Client foundClient = null;
        try {
            foundClient = (Client) (selectClientByAccountId.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundClient;
    }

    public Client findClientByAccountId(Session session, int id) {
        session.beginTransaction();
        Query selectClientByAccountId = session.createNamedQuery("selectClientByAccountId");
        selectClientByAccountId.setParameter("id", id);
        Client foundClient = null;
        try {
            foundClient = (Client) (selectClientByAccountId.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundClient;
    }

    public Client findClientByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectClientByEmail = session.createNamedQuery("selectClientByEmail");
        selectClientByEmail.setParameter("email", email);
        Client foundClient = null;
        try {
            foundClient = (Client) (selectClientByEmail.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundClient;
    }

    public Client findClientByEmail(Session session, String email) {
        session.beginTransaction();
        Query selectClientByEmail = session.createNamedQuery("selectClientByEmail");
        selectClientByEmail.setParameter("email", email);
        Client foundClient = null;
        try {
            foundClient = (Client) (selectClientByEmail.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            ;
        }
        session.getTransaction().commit();
        return foundClient;
    }

    public Integer findClientIdByAccountId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectClientIdByAccountId = session.createNamedQuery("selectClientIdByAccountId");
        selectClientIdByAccountId.setParameter("id", id);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectClientIdByAccountId.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            ;
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findClientIdByAccountId(Session session, int id) {
        session.beginTransaction();
        Query selectClientIdByAccountId = session.createNamedQuery("selectClientIdByAccountId");
        selectClientIdByAccountId.setParameter("id", id);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectClientIdByAccountId.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
            ;
        }
        session.getTransaction().commit();
        return foundId;
    }

    public Client findClientByAccountNameAndPassword(String accountName, String accountPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectClientByAccountNameAndPassword = session.createNamedQuery("selectClientByAccountNameAndPassword");
        selectClientByAccountNameAndPassword.setParameter("accountName", accountName);
        selectClientByAccountNameAndPassword.setParameter("accountPassword", accountPassword);
        Client foundClient = null;
        try {
            foundClient = (Client) (selectClientByAccountNameAndPassword.getSingleResult());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return foundClient;
    }

    public Client findClientByAccountNameAndPassword(Session session, String accountName, String accountPassword) {
        session.beginTransaction();
        Query selectClientByAccountNameAndPassword = session.createNamedQuery("selectClientByAccountNameAndPassword");
        selectClientByAccountNameAndPassword.setParameter("accountName", accountName);
        selectClientByAccountNameAndPassword.setParameter("accountPassword", accountPassword);
        Client foundClient = null;
        try {
            foundClient = (Client) (selectClientByAccountNameAndPassword.getSingleResult());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        return foundClient;
    }
}
