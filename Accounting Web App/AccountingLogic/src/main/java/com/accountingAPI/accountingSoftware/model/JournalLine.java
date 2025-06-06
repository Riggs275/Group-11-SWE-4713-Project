package com.accountingAPI.accountingSoftware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_lines")
public class JournalLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "line_id")
    private int lineId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "debit", nullable = false)
    private double debit;

    @Column(name = "credit", nullable = false)
    private double credit;

    // One-to-One mapping with Attachment (each JournalLine may have one attachment)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "attachment_id")
    private Attachment attachment;

    public JournalLine() {}

    // Getters and Setters
    public int getLineId() {
        return lineId;
    }
    public void setLineId(int lineIdSet) {
        lineId = lineIdSet;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String setName) {
        accountName = setName;
    }
    public double getDebit() {
        return debit;
    }
    public void setDebit(double debitSet) {
        debit = debitSet;
    }
    public double getCredit() {
        return credit;
    }
    public void setCredit(double creditSet) {
        credit = creditSet;
    }
    public Attachment getAttachment() {
        return attachment;
    }
    public void setAttachment(Attachment newAttachment) {
        newAttachment = attachment;
    }
}
