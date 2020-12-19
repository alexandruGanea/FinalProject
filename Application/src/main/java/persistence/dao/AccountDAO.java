package persistence.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Account;

import javax.persistence.NoResultException;
import javax.persistence.Query;

@Repository
public class AccountDAO {

    public void insertAccount(Account account) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        session.close();
    }

    public void insertAccount(Session session, Account account) {
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
    }

    public void deleteAccountByName(String accountName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query deleteAccountByName = session.createNamedQuery("deleteAccountByName");
        deleteAccountByName.setParameter("accountName", accountName);
        int rowsAffected = deleteAccountByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void changeAccountPassword(String accountName, String newPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query changePassword = session.createNamedQuery("changeAccountPassword");
        changePassword.setParameter("accountName", accountName);
        changePassword.setParameter("accountPassword", newPassword);
        int rowsAffected = changePassword.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void updateUserLogin(String accountName, boolean isUserLogin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateUserLogin = session.createNamedQuery("updateUserLogin");
        updateUserLogin.setParameter("accountName", accountName);
        updateUserLogin.setParameter("isUserLogin", isUserLogin);
        int rowsAffected = updateUserLogin.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void updateAdminLogin(String accountName, boolean isUserLogin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateAdminLogin = session.createNamedQuery("updateAdminLogin");
        updateAdminLogin.setParameter("accountName", accountName);
        updateAdminLogin.setParameter("isAdminLogin", isUserLogin);
        int rowsAffected = updateAdminLogin.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public Account findAccountByNameAndPassword(String accountName, String accountPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectAccountByNameAndPassword = session.createNamedQuery("selectAccountByNameAndPassword");
        selectAccountByNameAndPassword.setParameter("accountName", accountName);
        selectAccountByNameAndPassword.setParameter("accountPassword", accountPassword);
        Account account = (Account) (selectAccountByNameAndPassword.getSingleResult());
        session.getTransaction().commit();
        session.close();
        return account;
    }

    public Account findAccountByNameAndPassword(Session session, String accountName, String accountPassword) {
        session.beginTransaction();
        Query selectAccountByNameAndPassword = session.createNamedQuery("selectAccountByNameAndPassword");
        selectAccountByNameAndPassword.setParameter("accountName", accountName);
        selectAccountByNameAndPassword.setParameter("accountPassword", accountPassword);
        Account account = null;
        try {
            account = (Account) (selectAccountByNameAndPassword.getSingleResult());
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return account;
    }

    public Integer findAccountIdByNameAndPassword(String accountName, String accountPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectAccountIdByNameAndPassword = session.createNamedQuery("selectAccountIdByNameAndPassword");
        selectAccountIdByNameAndPassword.setParameter("accountName", accountName);
        selectAccountIdByNameAndPassword.setParameter("accountPassword", accountPassword);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectAccountIdByNameAndPassword.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findAccountIdByNameAndPassword(Session session, String accountName, String accountPassword) {
        session.beginTransaction();
        Query selectAccountIdByNameAndPassword = session.createNamedQuery("selectAccountIdByNameAndPassword");
        selectAccountIdByNameAndPassword.setParameter("accountName", accountName);
        selectAccountIdByNameAndPassword.setParameter("accountPassword", accountPassword);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectAccountIdByNameAndPassword.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }
}
