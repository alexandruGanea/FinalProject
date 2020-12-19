package business.service;

import business.dto.CountryDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.HibernateUtil;
import persistence.dao.ContinentDAO;
import persistence.dao.CountryDAO;
import persistence.entities.Continent;
import persistence.entities.Country;

@Service
public class CountryService {
    @Autowired
    ContinentDAO continentDAO;
    @Autowired
    CountryDAO countryDAO;
    @Autowired
    ContinentService continentService;


    public void insertCountry(CountryDTO countryDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Country country = new Country(countryDTO.getName());
        country.setContinent(checkForContinent(session, countryDTO));
        countryDAO.insertCountry(session, country);
        session.close();
    }

    public Country insertCountry(Session session, CountryDTO countryDTO) {
        Country country = new Country(countryDTO.getName());
        country.setContinent(checkForContinent(session, countryDTO));
        countryDAO.insertCountry(session, country);
        return country;
    }

    private Continent checkForContinent(Session session, CountryDTO countryDTO) {
        if (continentService.isInserted(session, countryDTO.getContinentDTO())) {
            return continentDAO.findContinentByName(session, countryDTO.getContinentDTO().getName());
        } else {
            return continentService.insertContinent(session, countryDTO.getContinentDTO());
        }
    }

    public boolean isInserted(CountryDTO countryDTO) {
        Integer idFound = countryDAO.findCountryIdByName(countryDTO.getName());
        return idFound != 0;
    }

    public boolean isInserted(Session session, CountryDTO countryDTO) {
        Integer idFound = countryDAO.findCountryIdByName(session, countryDTO.getName());
        return idFound != 0;
    }
}


