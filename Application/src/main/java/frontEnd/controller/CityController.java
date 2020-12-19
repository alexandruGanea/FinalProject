package frontEnd.controller;

import business.dto.CityDTO;
import business.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cities")
public class CityController {
    @Autowired
    CityService cityService;


    @PostMapping(path = "/insertCity")
    public ResponseEntity insertCity(@RequestBody @Valid CityDTO cityDTO) {
        if (cityService.isInserted(cityDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("City: " + cityDTO.getName() + " is already inserted");
        } else {
            cityService.insertCity(cityDTO);
            return ResponseEntity.ok(cityDTO.getName() + " was added.");
        }
    }

}
