package com.accountingAPI.accountingSoftware.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "journal_entries")
public class JournalEntry {

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

    @Column(name = "account_name")
    private String accountName;

    // One JournalEntry contains multiple JournalLine entries
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "journal_entry_id") // foreign key column in journal_lines table
    private List<JournalLine> lines;

    // Generate ErrorMessage(s)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "journal_entry_id")
    private List<ErrorMessage> errorMessages;

    // Constructors, getters and setters
    public JournalEntry() {}

    public int getEntryId() {
        return entryId;
    }
    public void setEntryId(int id) {
        entryId = id;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate dateSet) {
        date = dateSet;
    }
    public String getSubmittedBy() {
        return submittedBy;
    }
    public void setSubmittedBy(String userSubmitted) {
        submittedBy = userSubmitted;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String statusSet) {
        status = statusSet;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String newComment) {
        comment = newComment;
    }
    public List<JournalLine> getLines() {
        return lines;
    }
    public void setLines(List<JournalLine> addLines) {
       lines = addLines;
    }
    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
    public void setErrorMessages(List<ErrorMessage> error) {
        errorMessages = error;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String addName) {
        accountName = addName;
    }
}
