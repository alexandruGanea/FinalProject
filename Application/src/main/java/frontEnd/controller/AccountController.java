package frontEnd.controller;

import business.dto.AccountDTO;
import business.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping(path = "/loginAccount")
    public ResponseEntity loginAccount(@Valid @RequestBody AccountDTO accountDTO) {
        if (!accountService.isInserted(accountDTO)) {
            insertAccount(accountDTO);
            return loginAccount(accountDTO);
        } else if(accountService.isLoggedIn(accountDTO)) {
           return ResponseEntity.status(HttpStatus.CONFLICT).body("You are already logged in on another device. Please log out there first");
        }else{
            accountService.accountLogin(accountDTO);
            return ResponseEntity.status(HttpStatus.OK).body("You have successfully logged in.");
        }
    }

    @PutMapping(path = "/logoutAccount")
    public ResponseEntity logoutAccount(@Valid @RequestBody AccountDTO accountDTO){
        if(accountService.isLoggedIn(accountDTO)){
            accountService.accountLogout(accountDTO);
            return ResponseEntity.status(HttpStatus.OK).body("You have successfully logged out.");
        }else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: the account does not exist.");
    }
}
