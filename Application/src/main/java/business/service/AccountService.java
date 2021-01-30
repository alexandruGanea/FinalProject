package business.service;

import business.dto.AccountDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.AccountDAO;
import persistence.entities.Account;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;

    public void insertAccount(AccountDTO accountDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Account account = new Account();
        account.setAccountName(accountDTO.getAccountName());
        account.setAccountPassword(encryptPassword(accountDTO.getAccountPassword()));
        account.setUserLogin(false);
        account.setAdminLogin(false);
        accountDAO.insertAccount(session, account);
        session.close();
    }

    public Account insertAccount(Session session, AccountDTO accountDTO) {
        Account account = new Account();
        account.setAccountName(accountDTO.getAccountName());
        account.setAccountPassword(encryptPassword(accountDTO.getAccountPassword()));
        account.setUserLogin(false);
        account.setAdminLogin(false);
        accountDAO.insertAccount(session, account);
        return account;
    }

    public String encryptPassword(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        BigInteger bigInteger = new BigInteger(1, messageDigest.digest(password.getBytes()));
        return bigInteger.toString();
    }

    public void accountLogin(AccountDTO accountDTO){
        accountDAO.updateUserLogin(accountDTO.getAccountName(), encryptPassword(accountDTO.getAccountPassword()), true);
    }

    public void accountLogout(AccountDTO accountDTO) {
        accountDAO.updateUserLogin(accountDTO.getAccountName(), encryptPassword(accountDTO.getAccountPassword()), false);
    }

    public boolean isLoggedIn(AccountDTO accountDTO){
        return accountDAO.isLoggedIn(accountDTO.getAccountName(), encryptPassword(accountDTO.getAccountPassword()));
    }

    public boolean isInserted(AccountDTO accountDTO) {
        String password = encryptPassword(accountDTO.getAccountPassword());
        Integer foundId = accountDAO.findAccountIdByNameAndPassword(accountDTO.getAccountName(), password);
        return foundId != 0;
    }

    public boolean isInserted(Session session, AccountDTO accountDTO) {
        String password = encryptPassword(accountDTO.getAccountPassword());
        Integer foundId = accountDAO.findAccountIdByNameAndPassword(session, accountDTO.getAccountName(), password);
        System.out.println("AccountId: " + foundId);
        return foundId != 0;
    }

}