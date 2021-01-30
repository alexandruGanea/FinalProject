package frontEnd.controller;

import business.dto.TravelPackageDTO;
import business.service.TravelPackageService;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import persistence.HibernateUtil;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/travelPackages")

public class TravelPackageController {
    @Autowired
    TravelPackageService travelPackageService;

    @PostMapping(path = "/insertTravelPackage")
    public ResponseEntity insertTravelPackage(@Valid @RequestBody TravelPackageDTO travelPackageDTO) {
        Session session = HibernateUtil.getSessionFactory().openSession();
     //   if (travelPackageService.isInserted(session, travelPackageDTO)) {
      //      return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Travel package is already present.");
      //  } else {
            travelPackageService.insertTravelPackage(travelPackageDTO);
            return ResponseEntity.ok("Travel Package was inserted.");
        }


   @GetMapping(path = "/getTravelPackage")
    public String getTravelPackageByName(@RequestParam String name){
        TravelPackageDTO foundTravelPackageDTO = travelPackageService.findTravelPackage(name);
        if (foundTravelPackageDTO != null){
            return foundTravelPackageDTO.toString();
        }else{
            return "There is no such travel package.";
        }

    }

    @GetMapping(path = "/getTravelPackageByDepartureAirport")
    public String getTravelPackageByAirport(@RequestParam String airportName){
        List<TravelPackageDTO> foundTravelPackageDTO = travelPackageService.findTravelPackageByAirport(airportName);
        if (!foundTravelPackageDTO.isEmpty()){
            return foundTravelPackageDTO.toString();
        }else{
            return "There is no such travel package.";
        }

    }

    @GetMapping(path = "/getTravelPackageByHotel")
    public String getTravelPackageByHotel(@RequestParam String hotelName){
        List<TravelPackageDTO> foundTravelPackageDTO = travelPackageService.findTravelPackageByHotel(hotelName);
        if (!foundTravelPackageDTO.isEmpty()){
            return foundTravelPackageDTO.toString();
        }else{
            return "There is no such travel package.";
        }

    }
}


