package business.service;

import business.dto.TravelPackageDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.FlightDAO;
import persistence.dao.HotelDAO;
import persistence.dao.MealDAO;
import persistence.dao.TravelPackageDAO;
import persistence.entities.Flight;
import persistence.entities.Hotel;
import persistence.entities.TravelPackage;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class TravelPackageService {

    @Autowired
    TravelPackageDAO travelPackageDAO;
    @Autowired
    FlightDAO flightDAO;
    @Autowired
    FlightService flightService;
    @Autowired
    HotelDAO hotelDAO;
    @Autowired
    HotelService hotelService;
    @Autowired
    MealDAO mealDAO;
    @Autowired
    MealService mealService;
    @Autowired
    RoomService roomService;


    public void insertTravelPackage(TravelPackageDTO travelPackageDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        TravelPackage travelPackage = new TravelPackage(travelPackageDTO.getName());
        travelPackage.setDescription(travelPackageDTO.getDescription());
        travelPackage.setInboundFlight(checkForInboundFlight(session, travelPackageDTO));
        travelPackage.setOutboundFlight(checkForOutboundFlight(session, travelPackageDTO));
        travelPackage.setHotel(checkForHotel(session, travelPackageDTO));
        travelPackage.setPackageType(travelPackageDTO.getPackageType());
        checkForMeal(session, travelPackageDTO);
        travelPackage.setAdultPrice(calculateAdultPrice(travelPackageDTO));
        travelPackage.setChildPrice(calculateChildPrice(travelPackageDTO));
        travelPackage.setPromoted(travelPackageDTO.isPromoted());
        travelPackage.setAvailablePackages(travelPackageDTO.getAvailablePackages());
        travelPackageDAO.insertTravelPackage(session, travelPackage);
        session.close();
    }

    private Flight checkForInboundFlight(Session session, TravelPackageDTO travelPackageDTO) {
        if (flightService.isInserted(session, travelPackageDTO.getInboundFlightDTO())) {
            return flightDAO.findFlightByName(session, travelPackageDTO.getInboundFlightDTO().getName());
        } else {
            return flightService.insertFlight(session, travelPackageDTO.getInboundFlightDTO());
        }
    }

    private Flight checkForOutboundFlight(Session session, TravelPackageDTO travelPackageDTO) {
        if (flightService.isInserted(session, travelPackageDTO.getOutboundFlightDTO())) {
            return flightDAO.findFlightByName(session, travelPackageDTO.getOutboundFlightDTO().getName());
        } else {
            return flightService.insertFlight(session, travelPackageDTO.getOutboundFlightDTO());
        }
    }

    private Hotel checkForHotel(Session session, TravelPackageDTO travelPackageDTO) {
        if (hotelService.isInserted(session, travelPackageDTO.getHotelDTO())) {
            return hotelDAO.findHotelByNameAndCity(session, travelPackageDTO.getHotelDTO().getName(), travelPackageDTO.getHotelDTO().getCityDTO().getName());
        } else {
            return hotelService.insertHotel(session, travelPackageDTO.getHotelDTO());
        }
    }

    private void checkForMeal(Session session, TravelPackageDTO travelPackageDTO) {
        if (!mealService.isInserted(session, travelPackageDTO.getMealDTO())) {
            mealService.insertMeal(session, travelPackageDTO.getMealDTO());
        }
    }

    private void checkForRoom(Session session, TravelPackageDTO travelPackageDTO) {
        if (!roomService.isInserted(session, travelPackageDTO.getRoomDTO())) {
            roomService.insertRoom(session, travelPackageDTO.getRoomDTO());
        }
    }

    private double getDailyMealPrice(TravelPackageDTO travelPackageDTO) {
        double mealPrice = 0;
        switch (travelPackageDTO.getPackageType()) {
            case "BB":
                mealPrice = travelPackageDTO.getMealDTO().getPriceBB();
                break;
            case "HB":
                mealPrice = travelPackageDTO.getMealDTO().getPriceHB();
                break;
            case "FB":
                mealPrice = travelPackageDTO.getMealDTO().getPriceFB();
                break;
            case "AI":
                mealPrice = travelPackageDTO.getMealDTO().getPriceAI();
            default:
                break;
        }
        return mealPrice;
    }

    private double getDailyRoomPrice(TravelPackageDTO travelPackageDTO) {
        return travelPackageDTO.getRoomDTO().getGuestPrice();
    }

    private double getFlightPrice(TravelPackageDTO travelPackageDTO) {
        return travelPackageDTO.getInboundFlightDTO().getSeatPrice() + travelPackageDTO.getOutboundFlightDTO().getSeatPrice();
    }

    private long getTravelDays(TravelPackageDTO travelPackageDTO) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date checkInDate = null;
        Date checkOutDate = null;
        try {
            checkInDate = simpleDateFormat.parse(travelPackageDTO.getInboundFlightDTO().getArrivalDate());
            checkOutDate = simpleDateFormat.parse(travelPackageDTO.getOutboundFlightDTO().getDepartureDate());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return TimeUnit.DAYS.convert((Math.abs(checkOutDate.getTime() - checkInDate.getTime())), TimeUnit.MILLISECONDS);
    }

    private double calculateAdultPrice(TravelPackageDTO travelPackageDTO) {
        long noOfDays = getTravelDays(travelPackageDTO);
        return getFlightPrice(travelPackageDTO) + ((getDailyRoomPrice(travelPackageDTO) + getDailyMealPrice(travelPackageDTO)) * noOfDays);
    }

    private double calculateChildPrice(TravelPackageDTO travelPackageDTO) {
        long noOfDays = getTravelDays(travelPackageDTO);
        return (getFlightPrice(travelPackageDTO) + (getDailyMealPrice(travelPackageDTO) * noOfDays) / 2);
    }

    void updateStocks(Session session, TravelPackageDTO travelPackageDTO, int soldItems) {
        travelPackageDAO.updateTravelPackageStockByName(session, travelPackageDTO.getName(), soldItems);

    }

    public boolean isInserted(TravelPackageDTO travelPackageDTO) {
        Integer idFound = travelPackageDAO.findTravelPackageIdByName(travelPackageDTO.getName());
        return idFound != 0;
    }

    public boolean isInserted(Session session, TravelPackageDTO travelPackageDTO) {
        Integer idFound = travelPackageDAO.findTravelPackageIdByName(session, travelPackageDTO.getName()).get(0);
        return idFound != 0;
    }

    public TravelPackageDTO findTravelPackage(Session session, String name) {
        TravelPackage foundTravelPackage = travelPackageDAO.findTravelPackageByName(session, name);
        return setupTravelPackageDTO(session, foundTravelPackage);
    }

    public TravelPackageDTO findTravelPackage(String name) {
        TravelPackage foundTravelPackage = travelPackageDAO.findTravelPackageByName(name);
        return setupTravelPackageDTO(foundTravelPackage);
    }

    public List<TravelPackageDTO> findTravelPackageByAirport(String airportName) {
        List<TravelPackageDTO> foundTravelPackagesList = new ArrayList<>();
        for (TravelPackage travelPackage : travelPackageDAO.findTravelPackagesByAirportOfDeparture(airportName)) {
            foundTravelPackagesList.add(setupTravelPackageDTO(travelPackage));
        }
        return foundTravelPackagesList;

    }

    public List<TravelPackageDTO> findTravelPackageByHotel(String hotelName) {
        List<TravelPackageDTO> foundTravelPackagesList = new ArrayList<>();
        for (TravelPackage travelPackage : travelPackageDAO.findTravelPackagesByHotel(hotelName)) {
            foundTravelPackagesList.add(setupTravelPackageDTO(travelPackage));
        }
        return foundTravelPackagesList;
    }


        TravelPackageDTO setupTravelPackageDTO(TravelPackage travelPackage) {
        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.setName(travelPackage.getName());
        travelPackageDTO.setDescription(travelPackage.getDescription());
        travelPackageDTO.setInboundFlightDTO(flightService.setFlightDTO(travelPackage.getInboundFlight()));
        travelPackageDTO.setOutboundFlightDTO(flightService.setFlightDTO(travelPackage.getOutboundFlight()));
        travelPackageDTO.setHotelDTO(hotelService.setHotelDTO(travelPackage.getHotel()));
        travelPackageDTO.setMealDTO(mealService.setMealDTO(mealDAO.findMealByHotelId(travelPackage.getHotel().getId())));
        travelPackageDTO.setPackageType(travelPackage.getPackageType());
        travelPackageDTO.setAdultPrice(travelPackage.getAdultPrice());
        travelPackageDTO.setChildPrice(travelPackage.getChildPrice());
        travelPackageDTO.setPromoted(travelPackage.isPromoted());
        travelPackageDTO.setAvailablePackages(travelPackage.getAvailablePackages());
        return travelPackageDTO;
    }

    TravelPackageDTO setupTravelPackageDTO(Session session, TravelPackage travelPackage) {
        TravelPackageDTO travelPackageDTO = new TravelPackageDTO();
        travelPackageDTO.setName(travelPackage.getName());
        travelPackageDTO.setDescription(travelPackage.getDescription());
        travelPackageDTO.setInboundFlightDTO(flightService.setFlightDTO(travelPackage.getInboundFlight()));
        travelPackageDTO.setOutboundFlightDTO(flightService.setFlightDTO(travelPackage.getOutboundFlight()));
        travelPackageDTO.setHotelDTO(hotelService.setHotelDTO(travelPackage.getHotel()));
        travelPackageDTO.setMealDTO(mealService.setMealDTO(mealDAO.findMealByHotelId(session, travelPackage.getHotel().getId())));
        travelPackageDTO.setPackageType(travelPackage.getPackageType());
        travelPackageDTO.setAdultPrice(travelPackage.getAdultPrice());
        travelPackageDTO.setChildPrice(travelPackage.getChildPrice());
        travelPackageDTO.setPromoted(travelPackage.isPromoted());
        travelPackageDTO.setAvailablePackages(travelPackage.getAvailablePackages());
        return travelPackageDTO;
    }
}
