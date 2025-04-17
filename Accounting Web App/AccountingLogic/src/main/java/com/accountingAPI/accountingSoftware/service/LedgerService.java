package com.accountingAPI.accountingSoftware.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.LedgerEntry;
import com.accountingAPI.accountingSoftware.repository.LedgerRepository;

@Service
public class LedgerService {

    @Autowired
    private LedgerRepository ledgerRepo;

    public ResponseEntity<?> postJournalToLedger(Map<String, String> data) {
        // Build a ledger entry from the map
        LedgerEntry ledgerentry = new LedgerEntry();
        ledgerentry.setAccountName(data.get("accountName"));
        ledgerentry.setDate(LocalDate.parse(data.get("date")));
        ledgerentry.setDescription(data.get("description"));
        ledgerentry.setDebit(Double.parseDouble(data.get("debit")));
        ledgerentry.setCredit(Double.parseDouble(data.get("credit")));
        ledgerentry.setBalance(Double.parseDouble(data.get("balance")));
        ledgerentry.setPostReference(data.get("postReference"));
        LedgerEntry saved = ledgerRepo.save(ledgerentry);
        return ResponseEntity.ok(saved);
    }
    public ResponseEntity<?> getLedgerByAccount(Map<String, String> data) {
        List<LedgerEntry> list = ledgerRepo.findByAccountName(data.get("accountName"));
        return ResponseEntity.ok(list);
    }
    public ResponseEntity<?> searchLedger(Map<String, String> data) {
        String token = data.get("token");
        List<LedgerEntry> results = ledgerRepo.findByAccountName(token)
            .stream()
            .filter(ledgerentry -> ledgerentry.getDescription().contains(token)
                       || ledgerentry.getPostReference().contains(token))
            .toList();
        return ResponseEntity.ok(results);
    }
}
