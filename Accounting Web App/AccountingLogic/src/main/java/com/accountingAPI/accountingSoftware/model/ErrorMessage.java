package com.accountingAPI.accountingSoftware.model;

import javax.persistence.*;
import java.time.LocalDateTime;

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
        id = errorId;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String newMessage) {
        newMessage = message;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestampSet) {
        timestampSet = timestamp;
    }
}
