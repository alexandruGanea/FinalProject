package frontEnd.controller;

import business.dto.PurchaseDTO;
import business.service.AccountService;
import business.service.ClientService;
import business.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/purchases")


public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;
    @Autowired
    ClientService clientService;
    @Autowired
    AccountService accountService;


    @PostMapping(path = "/insertPurchase")
    public ResponseEntity insertPurchase(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        if (!accountService.isInserted(purchaseDTO.getClientDTO().getAccountDTO())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You do not have an account, please register an account");
        } else if (!accountService.isLoggedIn(purchaseDTO.getClientDTO().getAccountDTO())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not logged in. Please Log in.");
        } else if (!clientService.isInserted(purchaseDTO.getClientDTO())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Please insert your client data to purchase");
        } else {
            purchaseService.insertPurchase(purchaseDTO);
            return ResponseEntity.status(HttpStatus.OK).body("Purchase added.");
        }
    }
}
