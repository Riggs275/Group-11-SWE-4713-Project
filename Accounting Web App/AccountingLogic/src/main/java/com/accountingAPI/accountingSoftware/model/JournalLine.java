package com.accountingAPI.accountingSoftware.model;

import javax.persistence.*;

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
        lineIdSet = lineId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String setName) {
        setName = accountName;
    }
    public double getDebit() {
        return debit;
    }
    public void setDebit(double debitSet) {
        debitSet = debit;
    }
    public double getCredit() {
        return credit;
    }
    public void setCredit(double creditSet) {
        creditSet = credit;
    }
    public Attachment getAttachment() {
        return attachment;
    }
    public void setAttachment(Attachment newAttachment) {
        newAttachment = attachment;
    }
}
