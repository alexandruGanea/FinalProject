package business.service;

import business.dto.PurchaseDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.*;
import persistence.entities.Client;
import persistence.entities.Purchase;
import persistence.entities.Room;
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
    @Autowired
    RoomDAO roomDAO;
    @Autowired
    RoomService roomService;
    @Autowired
    FlightService flightService;
    @Autowired
    FlightDAO flightDAO;


    public void insertPurchase(PurchaseDTO purchaseDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Purchase purchase = new Purchase();
        purchase.setTravelPackage(checkForTravelPackage(purchaseDTO));
        purchase.setClient(checkForClient(session, purchaseDTO));
        purchase.setTotalPrice(calculateTravelPackagePrice(purchaseDTO, purchase));
        purchase.setPurchaseDate(setPurchaseDate());
        purchaseDAO.insertPurchase(session, purchase);
        purchaseDTO.getTravelPackageDTO().setOutboundFlightDTO(flightService.setFlightDTO(purchase.getTravelPackage().getOutboundFlight()));
        purchaseDTO.getTravelPackageDTO().setInboundFlightDTO((flightService.setFlightDTO(purchase.getTravelPackage().getInboundFlight())));
        updateStock(session, purchaseDTO);
        session.close();
    }

    public TravelPackage checkForTravelPackage(PurchaseDTO purchaseDTO) {
        TravelPackage travelPackage = travelPackageDAO.findTravelPackageByName(purchaseDTO.getTravelPackageDTO().getName());
        int noOfPeople = purchaseDTO.getNoOfAdults() + purchaseDTO.getNoOfChildren();
        String roomType = setRoomTypeByNoOfPeople(purchaseDTO.getNoOfAdults(), purchaseDTO.getNoOfChildren());
        Room room = roomDAO.findRoomByTypeAndHotelId(roomType, travelPackage.getHotel().getId());
        if (room.getAvailableRooms() > 0
                && travelPackage.getInboundFlight().getAvailableSeats() >= noOfPeople
                && travelPackage.getOutboundFlight().getAvailableSeats() >= noOfPeople) {
            purchaseDTO.getTravelPackageDTO().setRoomDTO(roomService.setRoomDTO(room));
            return travelPackage;
        } else {
            return null;
        }
    }

    private String setRoomTypeByNoOfPeople(int noOfAdults, int noOfChildren) {
        if (noOfAdults + noOfChildren <= 2) {
            return "single";
        } else if (noOfAdults + noOfChildren >= 2 && noOfAdults + noOfChildren <= 3 && noOfChildren <= 2) {
            return "double";
        } else if (noOfAdults + noOfChildren >= 3 && noOfAdults + noOfChildren <= 4 && noOfChildren <= 2) {
            return "triple";
        } else return null;
    }

    private double calculateTravelPackagePrice(PurchaseDTO purchaseDTO, Purchase purchase) {
        double adultPrice = purchaseDTO.getNoOfAdults() * purchase.getTravelPackage().getAdultPrice();
        double childPrice = purchaseDTO.getNoOfChildren() * purchase.getTravelPackage().getChildPrice();
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
       // travelPackageService.updateStocks(session, purchaseDTO.getTravelPackageDTO(), noOfPeople);
        flightService.updateStocks(session, purchaseDTO.getTravelPackageDTO().getInboundFlightDTO(), noOfPeople);
        flightService.updateStocks(session, purchaseDTO.getTravelPackageDTO().getOutboundFlightDTO(), noOfPeople);
        roomService.updateStocks(session, purchaseDTO.getTravelPackageDTO().getRoomDTO());


    }

}
