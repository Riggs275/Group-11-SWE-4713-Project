package com.accountingAPI.accountingSoftware.controller;

import com.accountingAPI.accountingSoftware.service.AccountService;
import java.util.Map;

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
