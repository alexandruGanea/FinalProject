package business.service;

import business.dto.ClientDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.AccountDAO;
import persistence.dao.ClientDAO;
import persistence.entities.Account;
import persistence.entities.Client;

@Service
public class ClientService {

    @Autowired
    ClientDAO clientDAO;
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    AccountService accountService;

    public void insertClient(ClientDTO clientDTO){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setYearOfBirth(clientDTO.getYearOfBirth());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhoneNo());
        client.setEmail(clientDTO.getEmail());
        client.setClientAccount(checkForAccount(session, clientDTO));
        clientDAO.insertClient(session, client);
        session.close();
    }

    public Client insertClient(Session session, ClientDTO clientDTO){
        Client client = new Client();
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setYearOfBirth(clientDTO.getYearOfBirth());
        client.setAddress(clientDTO.getAddress());
        client.setPhone(clientDTO.getPhoneNo());
        client.setEmail(clientDTO.getEmail());
        client.setClientAccount(checkForAccount(session, clientDTO));
        clientDAO.insertClient(session, client);
        return client;
    }

    private Account checkForAccount(Session session, ClientDTO clientDTO){
        if (accountService.isInserted(session, clientDTO.getAccountDTO())){
            String encryptedPassword = accountService.encryptPassword(clientDTO.getAccountDTO().getAccountPassword());
            return accountDAO.findAccountByNameAndPassword(session, clientDTO.getAccountDTO().getAccountName(), encryptedPassword);
        }else{
            return accountService.insertAccount(session, clientDTO.getAccountDTO());
        }
    }

    public boolean isLoggedIn(ClientDTO clientDTO){
        return accountService.isLoggedIn(clientDTO.getAccountDTO());
    }

    public boolean isInserted(ClientDTO clientDTO){
        String encryptedPassword = accountService.encryptPassword(clientDTO.getAccountDTO().getAccountPassword());
        Integer accountId = accountDAO.findAccountIdByNameAndPassword(clientDTO.getAccountDTO().getAccountName(), encryptedPassword);
        Integer idFound = clientDAO.findClientIdByAccountId(accountId);
        return idFound!=0;
    }

    public boolean isInserted(Session session, ClientDTO clientDTO){
        String encryptedPassword = accountService.encryptPassword(clientDTO.getAccountDTO().getAccountPassword());
        Integer accountId = accountDAO.findAccountIdByNameAndPassword(session, clientDTO.getAccountDTO().getAccountName(), encryptedPassword);
        Integer idFound = clientDAO.findClientIdByAccountId(session, accountId);
        return idFound!=0;
    }

}