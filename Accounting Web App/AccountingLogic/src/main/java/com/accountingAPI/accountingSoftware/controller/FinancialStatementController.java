package com.accountingAPI.accountingSoftware.controllers;

import com.accountingAPI.accountingSoftware.services.FinancialStatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/financial")
@CrossOrigin(origins = "http://localhost:3000")
public class FinancialStatementController {

    @Autowired
    private FinancialStatementService financialStatementService;

    @PostMapping("/trialBalance")
    public ResponseEntity<?> generateTrialBalance(@RequestBody Map<String, String> data) {
        return financialStatementService.generateTrialBalance(data);
    }

    @PostMapping("/incomeStatement")
    public ResponseEntity<?> generateIncomeStatement(@RequestBody Map<String, String> data) {
        return financialStatementService.generateIncomeStatement(data);
    }

    @PostMapping("/balanceSheet")
    public ResponseEntity<?> generateBalanceSheet(@RequestBody Map<String, String> data) {
        return financialStatementService.generateBalanceSheet(data);
    }

    @PostMapping("/retainedEarnings")
    public ResponseEntity<?> generateRetainedEarnings(@RequestBody Map<String, String> data) {
        return financialStatementService.generateRetainedEarnings(data);
    }

    @PostMapping("/getStatement")
    public ResponseEntity<?> getStatementByDateRange(@RequestBody Map<String, String> data) {
        return financialStatementService.getStatementByDateRange(data);
    }

    @PostMapping("/email")
    public ResponseEntity<?> emailStatement(@RequestBody Map<String, String> data) {
        return financialStatementService.emailStatement(data);
    }

    @PostMapping("/download")
    public ResponseEntity<?> downloadStatement(@RequestBody Map<String, String> data) {
        return financialStatementService.downloadStatement(data);
    }
}
