package business.service;

import business.dto.AirportDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.AirportDAO;
import persistence.dao.CityDAO;
import persistence.entities.Airport;
import persistence.entities.City;

@Service
public class AirportService {

    @Autowired
    AirportDAO airportDAO;
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CityService cityService;

    public void insertAirport(AirportDTO airportDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Airport airport = new Airport(airportDTO.getName());
        airport.setCityContainingAirport(checkForCity(session, airportDTO));
        airportDAO.insertAirport(session, airport);
        session.close();
    }

    public Airport insertAirport(Session session, AirportDTO airportDTO) {
        Airport airport = new Airport(airportDTO.getName());
        airport.setCityContainingAirport(checkForCity(session, airportDTO));
        airportDAO.insertAirport(session, airport);
        return airport;
    }

    private City checkForCity(Session session, AirportDTO airportDTO) {
        if (cityService.isInserted(session, airportDTO.getCityDTO())) {
            return cityDAO.findCityByName(session, airportDTO.getCityDTO().getName());
        } else {
            return cityService.insertCity(session, airportDTO.getCityDTO());
        }
    }

    public boolean isInserted(AirportDTO airportDTO) {
        return airportDAO.findAirportIdByName(airportDTO.getName()) != 0;
    }

    public boolean isInserted(Session session, AirportDTO airportDTO) {
        return airportDAO.findAirportIdByName(session,airportDTO.getName()) != 0;
    }
}
