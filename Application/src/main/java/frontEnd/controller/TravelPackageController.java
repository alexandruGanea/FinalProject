package frontEnd.controller;

import business.dto.TravelPackageDTO;
import business.service.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/travelPackages")

public class TravelPackageController {
    @Autowired
    TravelPackageService travelPackageService;

    @PostMapping(path = "/insertTravelPackage")
    public ResponseEntity insertTravelPackage(@Valid @RequestBody TravelPackageDTO travelPackageDTO) {
        if (travelPackageService.isInserted(travelPackageDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Travel package is already present.");
        } else {
            travelPackageService.insertTravelPackage(travelPackageDTO);
            return ResponseEntity.ok("Travel Package was inserted.");
        }
    }

   @GetMapping(path = "/getTravelPackage")
    public String getTravelPackageByName(@RequestParam String name){
        TravelPackageDTO foundTravelPackageDTO = travelPackageService.findTravelPackageByName(name);
        if (foundTravelPackageDTO != null){
            return foundTravelPackageDTO.toString();
        }else{
            return "There is no such travel package.";
        }

    }
}


