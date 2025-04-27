package com.accountingAPI.accountingSoftware.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.accountingAPI.accountingSoftware.service.AccountService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;

@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    // Add new account
    @PostMapping("/addAccount")
    public ResponseEntity<?> addAccount(@RequestBody Map<String, String> accountData) throws Exception {
        return accountService.addAccount(accountData);
    }

    // Edit account details
    @PutMapping("/editAccount")
    public ResponseEntity<?> editAccount(@RequestBody Map<String, String> accountData) throws Exception {
        return accountService.editAccount(accountData);
    }

    // View individual account
    @GetMapping("/viewAccounts")
    public ResponseEntity<?> viewAccounts(@RequestParam Map<String, String> accessorData) {
        return accountService.viewAccount(accessorData);
    }

    // Set active status of an account
    @PostMapping("/setActive")
    public ResponseEntity<?> setActive(@RequestBody Map<String, String> accountData) throws Exception {
        return accountService.setActive(accountData);
    }

    // Retrieve the ledger for a given account
    @PostMapping("/getLedger")
    public ResponseEntity<?> getLedger(@RequestBody Map<String, String> ledgerData) {
        return accountService.getLedger(ledgerData);
    }

    // Get full chart of accounts
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }
}
