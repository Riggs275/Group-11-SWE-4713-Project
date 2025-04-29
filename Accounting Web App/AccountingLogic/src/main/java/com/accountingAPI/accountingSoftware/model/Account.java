package com.accountingAPI.accountingSoftware.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
    
    // Account variables
    @Id
    @Column(name = "account_id")
    private String accountId; // ID of the account

    @Column(name = "account_name", nullable = false, unique = true)
    private String accountName; // Account name the user can identitify
    
    @Column(name = "account_number", nullable = false)
    private int accountNumber; // Must have correct starting values

    @Column(name = "account_description")
    private String accountDescription;

    @Column(name = "normal_side", nullable = false, length = 1)
    private char normalSide;

    @Column(name = "account_category")
    private String accountCategory; // e.g., Assets, Liabilities, Equity, Income, Expenses, etc.

    @Column(name = "account_subcategory")
    private String accountSubcategory; // Smaller categories within account categories

    @Column(name = "initial_balance")
    private double initialBalance;

    @Column(name = "debit")
    private double debit;

    @Column(name = "credit")
    private double credit;

    @Column(name = "balance")
    private double balance;

    @Column(name = "account_added")
    private LocalDateTime dateTimeAccountAdded;

    @Column(name = "added_by_user")
    private String userId; // ID of the user who created the account

    @Column(name = "order_field")
    private int orderField; // e.g., cash can be 01

    @Column(name = "statement")
    private String statement; // e.g., IS (income statement), BS (balance sheet), RE (Retained Earnings statement)

    @Column(name = "comment")
    private String comment;

    @Column(name = "active")
    private boolean isActive = true;

    // Getters and Setters
    public String getAccountId() {
        return accountId;
    }
    public void setAccountId(String newAccountId) {
        accountId = newAccountId;
    }
    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String newName) {
        accountName = newName;
    }
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int newNum) {
        accountNumber = newNum;
    }
    public String getAccountDescription() {
        return accountDescription;
    }
    public void setAccountDescription(String newDes) {
        accountDescription = newDes;
    }
    public char getNormalSide() {
        return normalSide;
    }
    public void setNormalSide(char newNorm) {
        // Validate that the normal side is either 'L' or 'R'
        if(newNorm == 'L' || newNorm == 'R') {
            normalSide = newNorm;
        }   
        
    }
    public String getAccountCategory() {
        return accountCategory;
    }
    public void setAccountCategory(String newCategory) {
        accountCategory = newCategory;
    }
    public String getAccountSubcategory() {
        return accountSubcategory;
    }
    public void setAccountSubcategory(String newSubcategory) {
        accountSubcategory = newSubcategory;
    }
    public double getInitialBalance() {
        return initialBalance;
    }
    public void setInitialBalance(Double initial) {
        initialBalance = initial;
    }
    public double getDebit() {
        return debit;
    }
    public void setDebit(double  newDebit) {
        debit = newDebit;
    }
    public double getCredit() {
        return credit;
    }
    public void setCredit(double  newCredit) {
        credit = newCredit;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double  newBalance) {
        balance = newBalance;
    }
    public LocalDateTime getDateTimeAccountAdded() {
        return dateTimeAccountAdded;
    }
    public void setDateTimeAccountAdded() {
        dateTimeAccountAdded = LocalDateTime.now();
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String newUserId) {
        userId = newUserId;
    }
    public int getOrderField() {
        return orderField;
    }
    public void setOrderField(int newOrderField) {
        orderField = newOrderField;
    }
    public String getStatement() {
        return statement;
    }
    public void setStatement(String newStatement) {
        statement = newStatement;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String newComment) {
        comment = newComment;
    }
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        isActive = active;
    }
}
