package frontEnd.controller;

import business.dto.ClientDTO;
import business.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/clients")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping(path = "/insertClient")
    public ResponseEntity insertClient(@Valid @RequestBody ClientDTO clientDTO){
        if(clientService.isInserted(clientDTO)){
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Client is already present.");

        }else{
            clientService.insertClient(clientDTO);
            return ResponseEntity.ok("Client was added.");
        }
    }
}
