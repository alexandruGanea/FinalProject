package frontEnd.controller;

import business.dto.FlightDTO;
import business.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;


    @PostMapping(path = "/insertFlight")
    public ResponseEntity insertFlight(@RequestBody @Valid FlightDTO flightDTO) {
        if (flightService.isInserted(flightDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Flight " + flightDTO.getName() + " is already present");
        } else{
            flightService.insertFlight(flightDTO);
            return ResponseEntity.ok("Flight " + flightDTO.getName() + " was added.");
        }
    }
}
