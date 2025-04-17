package com.accountingAPI.accountingSoftware.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.AdjustingJournalEntry;
import com.accountingAPI.accountingSoftware.repository.AdjustingJournalEntryRepository;
import com.accountingAPI.accountingSoftware.repository.AttachmentRepository;
import com.accountingAPI.accountingSoftware.repository.ErrorRepository;

@Service
public class AdjustingJournalService {

    @Autowired
    private AdjustingJournalEntryRepository adjRepo;
    @Autowired
    private AttachmentRepository attRepo;
    @Autowired
    private ErrorRepository errRepo;

    public ResponseEntity<?> createAdjustingEntry(Map<String, String> data) {
        // Parse fields
        AdjustingJournalEntry entry = new AdjustingJournalEntry();
        entry.setDate(LocalDate.parse(data.get("date")));
        entry.setSubmittedBy(data.get("submittedBy"));
        entry.setStatus("PENDING");
        entry.setComment(data.get("comment"));
        // Persist
        AdjustingJournalEntry saved = adjRepo.save(entry);
        return ResponseEntity.ok(saved);
    }
    public ResponseEntity<?> approveAdjustingEntry(Map<String, String> data) {
        int id = Integer.parseInt(data.get("entryId"));
        Optional<AdjustingJournalEntry> opt = adjRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body("Entry not found");
        }
        AdjustingJournalEntry e = opt.get();
        e.setStatus("APPROVED");
        adjRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> rejectAdjustingEntry(Map<String, String> data) {int id = Integer.parseInt(data.get("entryId"));
        String reason = data.get("comment");
        Optional<AdjustingJournalEntry> opt = adjRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body("Entry not found");
        }
        AdjustingJournalEntry e = opt.get();
        e.setStatus("REJECTED");
        e.setComment(reason);
        adjRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> cancelAdjustingEntry(Map<String, String> data) {int id = Integer.parseInt(data.get("entryId"));
        Optional<AdjustingJournalEntry> opt = adjRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body("Entry not found");
        }
        AdjustingJournalEntry e = opt.get();
        e.setStatus("CANCELLED");
        adjRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> submitAdjustingEntry(Map<String, String> data) {
        int id = Integer.parseInt(data.get("entryId"));
        Optional<AdjustingJournalEntry> opt = adjRepo.findById(id);
        if (opt.isEmpty()) {
            return ResponseEntity.badRequest().body("Entry not found");
        }
        AdjustingJournalEntry e = opt.get();
        e.setStatus("SUBMITTED");
        e.setDate(LocalDate.now());
        adjRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> getAdjustingEntriesByStatus(Map<String, String> data) {
        List<AdjustingJournalEntry> list = adjRepo.findByStatus(data.get("status"));
        return ResponseEntity.ok(list);
    }
    public ResponseEntity<?> searchAdjustingEntries(Map<String, String> data) {
        String token = data.get("token");
        List<AdjustingJournalEntry> results = adjRepo.findAll()
            .stream()
            .filter(e -> e.getSubmittedBy().contains(token)
                      || e.getComment().contains(token))
            .toList();
        return ResponseEntity.ok(results);
    }
    public ResponseEntity<?> attachSourceDocument(Map<String, String> data) {
        int entryId = Integer.parseInt(data.get("entryId"));
        Optional<AdjustingJournalEntry> optEntry = adjRepo.findById(entryId);
        if (optEntry.isEmpty()) {
            return ResponseEntity.badRequest().body("Entry not found");
        }
        AdjustingJournalEntry entry = optEntry.get();

        // Create and persist the attachment
        Attachment attachment = new Attachment();
        attachment.setFileName(data.get("fileName"));
        attachment.setFileType(data.get("fileType"));
        attachment.setFileData(data.get("fileData").getBytes());
        Attachment savedAtt = attRepo.save(attachment);

        // Associate back to entry
        entry.getLines().forEach(line -> {
            line.setAttachment(savedAtt);
        });
        adjRepo.save(entry);

        return ResponseEntity.ok("Attachment linked");
    }
}
