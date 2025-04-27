package com.accountingAPI.accountingSoftware.controller;

import com.accountingAPI.accountingSoftware.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/journal")
@CrossOrigin(origins = "http://localhost:3000")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping("/create")
    public ResponseEntity<?> createJournalEntry(@RequestBody Map<String, String> data) {
        return journalService.createJournalEntry(data);
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approveJournalEntry(@RequestBody Map<String, String> data) {
        return journalService.approveJournalEntry(data);
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectJournalEntry(@RequestBody Map<String, String> data) {
        return journalService.rejectJournalEntry(data);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelJournalEntry(@RequestBody Map<String, String> data) {
        return journalService.cancelJournalEntry(data);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitJournalEntry(@RequestBody Map<String, String> data) {
        return journalService.submitJournalEntry(data);
    }

    @PostMapping("/status")
    public ResponseEntity<?> getJournalEntriesByStatus(@RequestBody Map<String, String> data) {
        return journalService.getJournalEntriesByStatus(data);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchJournalEntries(@RequestBody Map<String, String> data) {
        return journalService.searchJournalEntries(data);
    }

    @PostMapping("/attach")
    public ResponseEntity<?> attachSourceDocument(@RequestBody Map<String, String> data) {
        return journalService.attachSourceDocument(data);
    }
}
