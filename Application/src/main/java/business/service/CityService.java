package business.service;

import business.dto.CityDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.CityDAO;
import persistence.dao.CountryDAO;
import persistence.entities.City;
import persistence.entities.Country;

@Service
public class CityService {
    @Autowired
    CityDAO cityDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    CountryService countryService;


    public void insertCity(CityDTO cityDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        City city = new City(cityDTO.getName());
        city.setCountry(checkForCountry(session, cityDTO));
        cityDAO.insertCity(session, city);
        session.close();
    }

    public City insertCity(Session session, CityDTO cityDTO) {
        City city = new City(cityDTO.getName());
        city.setCountry(checkForCountry(session, cityDTO));
        cityDAO.insertCity(session, city);
        return city;
    }

    private Country checkForCountry(Session session, CityDTO cityDTO) {
        if (countryService.isInserted(session, cityDTO.getCountryDTO())) {
            return countryDAO.findCountryByName(session, cityDTO.getCountryDTO().getName());
        } else {
            return countryService.insertCountry(session, cityDTO.getCountryDTO());
        }
    }

    public boolean isInserted(CityDTO cityDTO) {
        Integer idFound = cityDAO.findCityIdByName(cityDTO.getName());
        return idFound != 0;
    }

    public boolean isInserted(Session session, CityDTO cityDTO) {
        Integer idFound = cityDAO.findCityIdByName(session, cityDTO.getName());
        return idFound != 0;
    }
}
