package business.service;

import business.dto.FlightDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.AirportDAO;
import persistence.dao.FlightDAO;
import persistence.entities.Airport;
import persistence.entities.Flight;

@Service
public class FlightService {

    @Autowired
    FlightDAO flightDAO;
    @Autowired
    AirportDAO airportDAO;
    @Autowired
    AirportService airportService;

    public void insertFlight(FlightDTO flightDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Flight flight = new Flight(flightDTO.getName());
        flight.setDepartureAirport(checkForDepartureAirport(session, flightDTO));
        flight.setDeparture(flightDTO.getDepartureDate());
        flight.setDestinationAirport(checkForDestinationAirport(session, flightDTO));
        flight.setArrival(flightDTO.getArrivalDate());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setSeatPrice(flightDTO.getSeatPrice());
        System.out.println(flight.toString());
        flightDAO.insertFlight(session, flight);
        session.close();
    }

    public Flight insertFlight(Session session, FlightDTO flightDTO) {
        Flight flight = new Flight(flightDTO.getName());
        flight.setDepartureAirport(checkForDepartureAirport(session, flightDTO));
        flight.setDeparture(flightDTO.getDepartureDate());
        flight.setDestinationAirport(checkForDestinationAirport(session, flightDTO));
        flight.setArrival(flightDTO.getArrivalDate());
        flight.setAvailableSeats(flightDTO.getAvailableSeats());
        flight.setSeatPrice(flightDTO.getSeatPrice());
        System.out.println(flight.toString());
        flightDAO.insertFlight(session, flight);
        return flight;
    }

    private Airport checkForDepartureAirport(Session session, FlightDTO flightDTO) {
        if (airportService.isInserted(session, flightDTO.getDepartureAirportDTO())) {
            return airportDAO.findAirportByName(session, flightDTO.getDepartureAirportDTO().getName());
        } else {
            return airportService.insertAirport(session, flightDTO.getDepartureAirportDTO());
        }
    }

    private Airport checkForDestinationAirport(Session session, FlightDTO flightDTO) {
        if (airportService.isInserted(session, flightDTO.getDestinationAirportDTO())) {
            return airportDAO.findAirportByName(session, flightDTO.getDestinationAirportDTO().getName());
        } else {
            return airportService.insertAirport(session, flightDTO.getDestinationAirportDTO());
        }
    }

    public boolean isInserted(FlightDTO flightDTO) {
        Integer idFound = flightDAO.findFlightIdByName(flightDTO.getName());
        return idFound != 0;
    }

    public boolean isInserted(Session session, FlightDTO flightDTO) {
        Integer idFound = flightDAO.findFlightIdByName(session, flightDTO.getName());
        return idFound != 0;
    }

    FlightDTO setFlightDTO(Flight flight) {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setName(flight.getName());
        flightDTO.setDepartureAirportDTO(airportService.setAirportDTO(flight.getDepartureAirport()));
        flightDTO.setDestinationAirportDTO(airportService.setAirportDTO(flight.getDestinationAirport()));
        flightDTO.setDepartureDate(flight.getDeparture());
        flightDTO.setArrivalDate(flight.getArrival());
        flightDTO.setAvailableSeats(flight.getAvailableSeats());
        flightDTO.setSeatPrice(flight.getSeatPrice());
        return flightDTO;
    }

    public void updateStocks(Session session, FlightDTO flightDTO, int seatsSold) {
        flightDAO.updateFlightSeatsByName(session, flightDTO.getName(), seatsSold);
    }
}

