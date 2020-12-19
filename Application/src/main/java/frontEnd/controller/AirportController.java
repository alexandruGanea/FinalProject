package frontEnd.controller;

import business.dto.AirportDTO;
import business.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/airports")
public class AirportController {

    @Autowired
    AirportService airportService;

    @PostMapping(path = "/insertAirport")
    public ResponseEntity insertAirport(@RequestBody @Valid AirportDTO airportDTO) {
        if (airportService.isInserted(airportDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(airportDTO.getName() + " is already present.");
        } else {
            airportService.insertAirport(airportDTO);
            return ResponseEntity.ok("Airport " + airportDTO.getName() + " was added.");
        }
    }
}
