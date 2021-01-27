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

    public void updateUserLogin(String accountName, String accountPassword, boolean isUserLogin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateUserLogin = session.createNamedQuery("updateUserLogin");
        updateUserLogin.setParameter("accountName", accountName);
        updateUserLogin.setParameter("accountPassword", accountPassword);
        updateUserLogin.setParameter("isUserLogin", isUserLogin);
        int rowsAffected = updateUserLogin.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
        session.close();
    }

    public void updateAdminLogin(String accountName, String accountPassword, boolean isAdminLogin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query updateAdminLogin = session.createNamedQuery("updateAdminLogin");
        updateAdminLogin.setParameter("accountName", accountName);
        updateAdminLogin.setParameter("accountPassword", accountPassword);
        updateAdminLogin.setParameter("isAdminLogin", isAdminLogin);
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

    public boolean isLoggedIn(String accountName, String accountPassword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectLoginByNameAndPassword = session.createNamedQuery("selectLoginByNameAndPassword");
        selectLoginByNameAndPassword.setParameter("accountName", accountName);
        selectLoginByNameAndPassword.setParameter("accountPassword", accountPassword);
        boolean isLoggedIn = false;
        try {
            isLoggedIn = (boolean) (selectLoginByNameAndPassword.getSingleResult());
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        session.getTransaction().commit();
        session.close();
        return isLoggedIn;
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
