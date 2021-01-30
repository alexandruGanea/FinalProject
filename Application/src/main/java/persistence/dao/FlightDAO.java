package persistence.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import persistence.HibernateUtil;
import persistence.entities.Airport;
import persistence.entities.Flight;

import javax.persistence.NoResultException;
import java.util.List;

@Repository

public class FlightDAO {

    public void insertFlight(Flight flight) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(flight);
        session.getTransaction().commit();
        session.close();
    }

    public void insertFlight(Session session, Flight flight) {
        session.beginTransaction();
        session.save(flight);
        session.getTransaction().commit();
    }

    public Flight findFlightByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectFlightByName = session.createNamedQuery("selectFlightByName");
        selectFlightByName.setParameter("name", name);
        Flight foundFlight = null;
        try {
            foundFlight = (Flight) selectFlightByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundFlight;
    }

    public Flight findFlightByName(Session session, String name) {
        session.beginTransaction();
        Query selectFlightByName = session.createNamedQuery("selectFlightByName");
        selectFlightByName.setParameter("name", name);
        Flight foundFlight = null;
        try {
            foundFlight = (Flight) selectFlightByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundFlight;
    }

    public Integer findFlightIdByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectFlightIdByName = session.createNamedQuery("selectFlightIdByName");
        selectFlightIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectFlightIdByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        session.close();
        return foundId;
    }

    public Integer findFlightIdByName(Session session, String name) {
        session.beginTransaction();
        Query selectFlightIdByName = session.createNamedQuery("selectFlightIdByName");
        selectFlightIdByName.setParameter("name", name);
        Integer foundId = 0;
        try {
            foundId = (Integer) selectFlightIdByName.getSingleResult();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }
        session.getTransaction().commit();
        return foundId;
    }

    public List<Flight> findFlightByDepartureAirport(Airport departureAirport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectFlightByDepartureAirport = session.createNamedQuery("selectFlightByDepartureAirport");
        selectFlightByDepartureAirport.setParameter("departureAirport", departureAirport);
        List<Flight> foundFlights = selectFlightByDepartureAirport.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundFlights;
    }

    public List<Flight> findFlightByArrivalAirport(Airport arrivalAirport) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Query selectFlightByArrivalAirport = session.createNamedQuery("selectFlightByArrivalAirport");
        selectFlightByArrivalAirport.setParameter("departureAirport", arrivalAirport);
        List<Flight> foundFlights = selectFlightByArrivalAirport.getResultList();
        session.getTransaction().commit();
        session.close();
        return foundFlights;
    }

    public void updateFlightSeatsByName(Session session, String flightName, int seatsSold) {
        session.beginTransaction();
        Query updateFlightSeatsByName = session.createNamedQuery("updateFlightSeatsByName");
        updateFlightSeatsByName.setParameter("seatsSold", seatsSold);
        updateFlightSeatsByName.setParameter("flightName", flightName);
        int rowsAffected = updateFlightSeatsByName.executeUpdate();
        System.out.println("Rows affected: " + rowsAffected);
        session.getTransaction().commit();
    }


}
