package com.accountingAPI.accountingSoftware.controller;

import com.accountingAPI.accountingSoftware.service.AdjustingJournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/adjusting")
@CrossOrigin(origins = "http://localhost:3000")
public class AdjustingJournalController {

    @Autowired
    private AdjustingJournalService adjustingJournalService;

    @PostMapping("/create")
    public ResponseEntity<?> createAdjustingEntry(@RequestBody Map<String, String> data) {
        return adjustingJournalService.createAdjustingEntry(data);
    }

    @PostMapping("/approve")
    public ResponseEntity<?> approveAdjustingEntry(@RequestBody Map<String, String> data) {
        return adjustingJournalService.approveAdjustingEntry(data);
    }

    @PostMapping("/reject")
    public ResponseEntity<?> rejectAdjustingEntry(@RequestBody Map<String, String> data) {
        return adjustingJournalService.rejectAdjustingEntry(data);
    }

    @PostMapping("/cancel")
    public ResponseEntity<?> cancelAdjustingEntry(@RequestBody Map<String, String> data) {
        return adjustingJournalService.cancelAdjustingEntry(data);
    }

    @PostMapping("/submit")
    public ResponseEntity<?> submitAdjustingEntry(@RequestBody Map<String, String> data) {
        return adjustingJournalService.submitAdjustingEntry(data);
    }

    @PostMapping("/status")
    public ResponseEntity<?> getAdjustingEntriesByStatus(@RequestBody Map<String, String> data) {
        return adjustingJournalService.getAdjustingEntriesByStatus(data);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchAdjustingEntries(@RequestBody Map<String, String> data) {
        return adjustingJournalService.searchAdjustingEntries(data);
    }

    @PostMapping("/attach")
    public ResponseEntity<?> attachSourceDocument(@RequestBody Map<String, String> data) {
        return adjustingJournalService.attachSourceDocument(data);
    }
}
