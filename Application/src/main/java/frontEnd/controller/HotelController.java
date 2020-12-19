package frontEnd.controller;

import business.dto.HotelDTO;
import business.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/hotels")
public class HotelController {

    @Autowired
    HotelService hotelService;

    @PostMapping(path = "/insertHotel")
    public ResponseEntity insertHotel(@Valid @RequestBody HotelDTO hotelDTO) {
        if (hotelService.isInserted(hotelDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Hotel " + hotelDTO.getName() + " is already present.");
        } else {
            hotelService.insertHotel(hotelDTO);
            return ResponseEntity.ok("Hotel " + hotelDTO.getName() + " was added.");
        }
    }
}
