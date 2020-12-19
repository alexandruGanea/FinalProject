package frontEnd.controller;

import business.dto.ContinentDTO;
import business.service.ContinentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/continents")
public class ContinentController {
    @Autowired
    ContinentService continentService;

    @PostMapping(path = "/insertContinent")
    public ResponseEntity insertContinent(@RequestBody @Valid ContinentDTO continentDTO) {
        if (continentService.isInserted(continentDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(continentDTO.getName() + " is already present.");
        } else {
            continentService.insertContinent(continentDTO);
            return ResponseEntity.ok("Continent " + continentDTO.getName() + " was added.");
        }
    }
}
