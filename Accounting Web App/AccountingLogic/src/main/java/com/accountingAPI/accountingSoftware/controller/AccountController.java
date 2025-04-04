package com.accountingAPI.accountingSoftware.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.accountingAPI.accountingSoftware.service.AccountService;
@RestController
public class AccountController {
    @Autowired
    private AccountService accountService;

    // Add new account
    @PostMapping("/addAccount")
    public ResponseEntity<?> addAccount(@RequestBody Map<String, String> accountData) {
        return accountService.addAccount(accountData);
    }

    // Edit account details
    @PutMapping("/editAccount")
    public ResponseEntity<?> editAccount(@RequestBody Map<String, String> accountData) {
        return accountService.editAccount(accountData);
    }

    // View individual account
    @GetMapping("/viewAccounts")
    public ResponseEntity<?> viewAccounts(@RequestBody Map<String, String> accessorData) {
        return accountService.viewAccount(accessorData);
    }

    // Set active status of an account
    @PostMapping("/setActive")
    public ResponseEntity<?> setActive(@RequestBody Map<String, String> accountData) {
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
        return ResponseEntity.getAllAccounts(accountService.getAllAccounts());
    }
}
