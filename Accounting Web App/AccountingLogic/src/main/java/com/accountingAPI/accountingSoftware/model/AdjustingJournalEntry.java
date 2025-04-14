package com.accountingAPI.accountingSoftware.components;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "adjusting_journal_entries")
public class AdjustingJournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_id")
    private int entryId;

    @Column(name = "entry_date", nullable = false)
    private LocalDate date;

    @Column(name = "submitted_by", nullable = false)
    private String submittedBy;

    @Column(name = "status")
    private String status;

    @Column(name = "comment")
    private String comment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adj_journal_entry_id")
    private List<JournalLine> lines;

    // Generate ErrorMessage(s)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adj_journal_entry_id")
    private List<ErrorMessage> errorMessages;

    // AdjustingJournalEntry posts to LedgerEntries
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adj_journal_entry_id")
    private List<LedgerEntry> ledgerEntries;

    public AdjustingJournalEntry() {}

    // Getters and Setters
    public int getEntryId() {
        return entryId;
    }
    public void setEntryId(int id) {
        id = entryId;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate dateSet) {
        dateSet = date;
    }
    public String getSubmittedBy() {
        return submittedBy;
    }
    public void setSubmittedBy(String userSubmitted) {
        userSubmitted = submittedBy;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String statusSet) {
        statusSet = status;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String newComment) {
        newComment = comment;
    }
    public List<JournalLine> getLines() {
        return lines;
    }
    public void setLines(List<JournalLine> newLines) {
        newLines = lines;
    }
    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
    public void setErrorMessages(List<ErrorMessage> error) {
        error = errorMessages;
    }
    public List<LedgerEntry> getLedgerEntries() {
        return ledgerEntries;
    }
    public void setLedgerEntries(List<LedgerEntry> newLedgerEntries) {
        newLedgerEntries = ledgerEntries;
    }
}
