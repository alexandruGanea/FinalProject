package frontEnd.controller;

import business.dto.CountryDTO;
import business.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/countries")
public class CountryController {
    @Autowired
    CountryService countryService;

    @PostMapping(path = "/insertCountry")
    public ResponseEntity insertCountry(@Valid @RequestBody CountryDTO countryDTO) {
        if (countryService.isInserted(countryDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(countryDTO.getName() + " is already inserted");
        } else {
            countryService.insertCountry(countryDTO);
            return ResponseEntity.ok("Country " + countryDTO.getName() + " was added.");
        }
    }
}
