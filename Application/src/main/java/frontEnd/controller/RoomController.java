package frontEnd.controller;


import business.dto.RoomDTO;
import business.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/rooms")

public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping(path = "/insertRoom")
    public ResponseEntity insertRoom(@Valid @RequestBody RoomDTO roomDTO) {
        if (roomService.isInserted(roomDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Room " + roomDTO.getRoomType() + " in hotel "
                    + roomDTO.getHotelDTO().getName() + " is already present.");
        } else {
            roomService.insertRoom(roomDTO);
            return ResponseEntity.ok("Room " + roomDTO.getRoomType() + " in hotel " + roomDTO.getHotelDTO().getName() + " wass added.");
        }
    }

}
