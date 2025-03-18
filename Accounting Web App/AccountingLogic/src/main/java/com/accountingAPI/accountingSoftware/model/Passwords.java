package com.accountingAPI.accountingSoftware.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "PASSWORDS")
public class Passwords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremented primary key
    @Column(name = "password_ref")
    private int passwordRef;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "old_passwords", columnDefinition = "TEXT")
    private String oldPasswords;

    // Constructors
    public Passwords() {}

    public Passwords(String passwordHash, String oldPasswords) {
        this.passwordHash = passwordHash;
        this.oldPasswords += oldPasswords+",";
    }

    // Getters and Setters
    public int getPasswordRef() {
        return passwordRef;
    }

    public void setPasswordRef(int passwordRef) {
        this.passwordRef = passwordRef;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getOldPasswords() {
        return oldPasswords;
    }
    public void addOldPasswords(String addPassword){
        this.oldPasswords += "," + addPassword;
    }

    public void setOldPasswords(String oldPasswords) {
        this.oldPasswords = oldPasswords;
    }
}
