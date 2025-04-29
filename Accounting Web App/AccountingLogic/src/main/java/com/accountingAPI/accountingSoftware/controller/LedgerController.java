package com.accountingAPI.accountingSoftware.controller;

import com.accountingAPI.accountingSoftware.service.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/ledger")
@CrossOrigin(origins = "http://localhost:3000")
public class LedgerController {

    @Autowired
    private LedgerService ledgerService;

    @PostMapping("/post")
    public ResponseEntity<?> postJournalToLedger(@RequestBody Map<String, String> data) {
        return ledgerService.postJournalToLedger(data);
    }

    @PostMapping("/get")
    public ResponseEntity<?> getLedgerByAccount(@RequestBody Map<String, String> data) {
        return ledgerService.getLedgerByAccount(data);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchLedger(@RequestBody Map<String, String> data) {
        return ledgerService.searchLedger(data);
    }
}
