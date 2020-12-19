package frontEnd.controller;

import business.dto.AccountDTO;
import business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(path = "/insertAccount")
    public ResponseEntity insertAccount(@Valid @RequestBody AccountDTO accountDTO) {
        if (accountService.isInserted(accountDTO)) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Account already registered.");
        } else {
            accountService.insertAccount(accountDTO);
            return ResponseEntity.ok("Account was added.");
        }
    }

}
