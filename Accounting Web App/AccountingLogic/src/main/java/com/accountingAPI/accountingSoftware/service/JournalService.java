package com.accountingAPI.accountingSoftware.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.JournalEntry;
import com.accountingAPI.accountingSoftware.repository.JournalEntryRepository;
import com.accountingAPI.accountingSoftware.repository.AttachmentRepository;
import com.accountingAPI.accountingSoftware.repository.ErrorRepository;

@Service
public class JournalService {

    @Autowired
    private JournalEntryRepository journalRepo;
    @Autowired
    private AttachmentRepository attRepo;
    @Autowired
    private ErrorRepository errRepo;

    public ResponseEntity<?> createJournalEntry(Map<String, String> data) {
        JournalEntry entry = new JournalEntry();
        entry.setDate(LocalDate.parse(data.get("date")));
        entry.setSubmittedBy(data.get("submittedBy"));
        entry.setStatus("PENDING");
        entry.setComment(data.get("comment"));
        JournalEntry saved = journalRepo.save(entry);
        return ResponseEntity.ok(saved);
    }
    public ResponseEntity<?> approveJournalEntry(Map<String, String> data) {
        int id = Integer.parseInt(data.get("entryId"));
        Optional<JournalEntry> opt = journalRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Entry not found");
        JournalEntry e = opt.get();
        e.setStatus("APPROVED");
        journalRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> rejectJournalEntry(Map<String, String> data) {
        int id = Integer.parseInt(data.get("entryId"));
        Optional<JournalEntry> opt = journalRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Entry not found");
        JournalEntry e = opt.get();
        e.setStatus("REJECTED");
        e.setComment(data.get("comment"));
        journalRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> cancelJournalEntry(Map<String, String> data) {
        int id = Integer.parseInt(data.get("entryId"));
        Optional<JournalEntry> opt = journalRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Entry not found");
        JournalEntry e = opt.get();
        e.setStatus("CANCELLED");
        journalRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> submitJournalEntry(Map<String, String> data) {
        int id = Integer.parseInt(data.get("entryId"));
        Optional<JournalEntry> opt = journalRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Entry not found");
        JournalEntry e = opt.get();
        e.setStatus("SUBMITTED");
        journalRepo.save(e);
        return ResponseEntity.ok(e);
    }
    public ResponseEntity<?> getJournalEntriesByStatus(Map<String, String> data) {
        List<JournalEntry> list = journalRepo.findByStatus(data.get("status"));
        return ResponseEntity.ok(list);
    }
    public ResponseEntity<?> searchJournalEntries(Map<String, String> data) {
        String token = data.get("token");
        List<JournalEntry> results = journalRepo.findAll()
            .stream()
            .filter(e -> e.getSubmittedBy().contains(token)
                      || e.getComment().contains(token))
            .toList();
        return ResponseEntity.ok(results);
    }
    public ResponseEntity<?> attachSourceDocument(Map<String, String> data) {
        int entryId = Integer.parseInt(data.get("entryId"));
        Optional<JournalEntry> opt = journalRepo.findById(entryId);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Entry not found");

        Attachment a = new Attachment();
        a.setFileName(data.get("fileName"));
        a.setFileType(data.get("fileType"));
        a.setFileData(data.get("fileData").getBytes());
        Attachment saved = attRepo.save(a);

        // Link to lines or entry
        JournalEntry e = opt.get();
        e.getLines().forEach(line -> line.setAttachment(saved));
        journalRepo.save(e);

        return ResponseEntity.ok("Attachment linked");
    }
}
