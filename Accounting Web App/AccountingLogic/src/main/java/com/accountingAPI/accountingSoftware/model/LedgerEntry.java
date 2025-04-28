package com.accountingAPI.accountingSoftware.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ledger_entries")
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private int ledgerId;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "entry_date", nullable = false)
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @Column(name = "debit", nullable = false)
    private double debit;

    @Column(name = "credit", nullable = false)
    private double credit;

    @Column(name = "balance", nullable = false)
    private double balance;

    @Column(name = "post_reference")
    private String postReference;

    public LedgerEntry() {}

    // Getters and Setters
    public int getLedgerId() {
        return ledgerId;
    }
    public void setLedgerId(int ledgerIdSet) {
        ledgerId = ledgerIdSet;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountNameSet) {
        accountName = accountNameSet;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate dateSet) {
        date = dateSet;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String descriptionSet) {
        description = descriptionSet;
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
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balanceSet) {
        balance = balanceSet;
    }
    public String getPostReference() {
        return postReference;
    }
    public void setPostReference(String postReferenceSet) {
        postReference = postReferenceSet;
    }
}
