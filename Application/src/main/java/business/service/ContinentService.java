package business.service;

import business.dto.ContinentDTO;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import persistence.dao.ContinentDAO;
import persistence.entities.Continent;


@Service
public class ContinentService {
    @Autowired
    ContinentDAO continentDAO;


    public void insertContinent(ContinentDTO continentDTO) {
        Continent continent = new Continent(continentDTO.getName());
        continentDAO.insertContinent(continent);
    }

    public Continent insertContinent(Session session, ContinentDTO continentDTO) {
        Continent continent = new Continent(continentDTO.getName());
        continentDAO.insertContinent(session, continent);
        return continent;
    }


    public boolean isInserted(ContinentDTO continentDTO) {
        Integer foundId = continentDAO.findContinentIdByName(continentDTO.getName());
        return foundId != 0;
    }

    public boolean isInserted(Session session, ContinentDTO continentDTO) {
        Integer foundId = continentDAO.findContinentIdByName(session, continentDTO.getName());
        return foundId != 0;
    }

    public ContinentDTO setContinentDTO(Continent continent) {
        ContinentDTO continentDTO = new ContinentDTO();
        continentDTO.setName(continent.getName());
        return continentDTO;
    }
}
