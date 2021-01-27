package business.service;

import business.dto.PurchaseDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.ClientDAO;
import persistence.dao.PurchaseDAO;
import persistence.dao.TravelPackageDAO;
import persistence.entities.Client;
import persistence.entities.Purchase;
import persistence.entities.TravelPackage;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PurchaseService {

    @Autowired
    PurchaseDAO purchaseDAO;
    @Autowired
    ClientDAO clientDAO;
    @Autowired
    ClientService clientService;
    @Autowired
    TravelPackageDAO travelPackageDAO;
    @Autowired
    TravelPackageService travelPackageService;

    public void insertPurchase(PurchaseDTO purchaseDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Purchase purchase = new Purchase();
        setupPurchaseDTO(session, purchaseDTO, purchase);
        purchase.setTotalPrice(calculateTravelPackagePrice(purchaseDTO));
        purchase.setClient(checkForClient(session, purchaseDTO));
        purchase.setPurchaseDate(setPurchaseDate());
        purchaseDAO.insertPurchase(session, purchase);
        updateStock(session, purchaseDTO);
        session.close();
    }

    private TravelPackage checkForTravelPackage(Session session, PurchaseDTO purchaseDTO) {
        TravelPackage foundTravelPackage = travelPackageDAO.findTravelPackageByName(session, purchaseDTO.getTravelPackageDTO().getName());
        return foundTravelPackage;
    }

    private double calculateTravelPackagePrice(PurchaseDTO purchaseDTO) {
        double adultPrice = purchaseDTO.getNoOfAdults() * purchaseDTO.getTravelPackageDTO().getAdultPrice();
        double childPrice = purchaseDTO.getNoOfChildren() * purchaseDTO.getTravelPackageDTO().getChildPrice();
        return adultPrice + childPrice;
    }

    private String setPurchaseDate() {
        Date currentDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentDate);
    }

    private Client checkForClient(Session session, PurchaseDTO purchaseDTO) {
        String encryptedPassword = clientService.accountService.encryptPassword(purchaseDTO.getClientDTO().getAccountDTO().getAccountPassword());
        Client client = clientDAO.findClientByAccountNameAndPassword(session, purchaseDTO.getClientDTO().getAccountDTO().getAccountName(), encryptedPassword);
        return client;
    }

    void updateStock(Session session, PurchaseDTO purchaseDTO) {
        int noOfPeople = purchaseDTO.getNoOfAdults() + purchaseDTO.getNoOfChildren();
        travelPackageService.updateStock(session, purchaseDTO.getTravelPackageDTO(), noOfPeople);
    }

    private PurchaseDTO setupPurchaseDTO(Session session, PurchaseDTO purchaseDTO, Purchase purchase) {
        purchase.setTravelPackage(checkForTravelPackage(session, purchaseDTO));
        purchaseDTO.getTravelPackageDTO().setChildPrice(purchase.getTravelPackage().getChildPrice()); // Set adult price from found travelPackage
        purchaseDTO.getTravelPackageDTO().setAdultPrice(purchase.getTravelPackage().getAdultPrice()); // Set child price from found travelPackage
        purchaseDTO.getTravelPackageDTO().setAvailablePackages(purchase.getTravelPackage().getAvailablePackages()); // Set stock
        return purchaseDTO;
    }

}
