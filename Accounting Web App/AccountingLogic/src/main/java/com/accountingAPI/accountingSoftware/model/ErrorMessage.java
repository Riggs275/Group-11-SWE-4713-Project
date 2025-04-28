package com.accountingAPI.accountingSoftware.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "error_messages")
public class ErrorMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_id")
    private int errorId;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;

    public ErrorMessage() {}

    // Getters and Setters
    public int getErrorId() {
        return errorId;
    }
    public void setErrorId(int id) {
        errorId = id;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String newMessage) {
        message = newMessage;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestampSet) {
        timestamp = timestampSet;
    }
}
