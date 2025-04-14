package com.accountingAPI.accountingSoftware.components;

import javax.persistence.*;
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
        ledgerIdSet = ledgerId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountNameSet) {
        accountNameSet = accountName;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate dateSet) {
        dateSet = date;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String descriptionSet) {
        descriptionSet = description;
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
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balanceSet) {
        balanceSet = balance;
    }
    public String getPostReference() {
        return postReference;
    }
    public void setPostReference(String postReferenceSet) {
        postReferenceSet = postReference;
    }
}
