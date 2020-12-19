package business.service;

import business.dto.AccountDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.AccountDAO;
import persistence.entities.Account;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

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
        accountDAO.insertAccount(session, account);
        return account;
    }

    private String encryptPassword(String password) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        byte[] hashedPassword = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
        return hashedPassword.toString();
    }

    public boolean isInserted(AccountDTO accountDTO) {
        String password = encryptPassword(accountDTO.getAccountPassword());
        Integer foundId = accountDAO.findAccountIdByNameAndPassword(accountDTO.getAccountName(), password);
        return foundId != 0;
    }

    public boolean isInserted(Session session, AccountDTO accountDTO) {
        String password = encryptPassword(accountDTO.getAccountPassword());
        Integer foundId = accountDAO.findAccountIdByNameAndPassword(session, accountDTO.getAccountName(), password);
        return foundId != 0;
    }
}
