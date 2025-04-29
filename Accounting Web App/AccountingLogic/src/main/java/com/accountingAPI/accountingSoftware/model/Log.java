package com.accountingAPI.accountingSoftware.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "logs")
public class Log {

    public Log(String newEventType, String newFromValue,String newToValue, String newChandedBy, LocalDateTime newdateModified){
        eventType = newEventType;
        fromValue = newFromValue;
        toValue = newToValue;
        changedBy = newChandedBy;
        dateModified = newdateModified;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer eventID;

    @Column(name = "event_type", nullable = false)
    private String eventType;

    @Column(name = "date_modified", nullable = false)
    private LocalDateTime dateModified;

    @Column(name = "changed_by", nullable = false)
    private String changedBy;

    @Column(name = "from_value")
    private String fromValue;

    @Column(name = "to_value")
    private String toValue;

    // Getters and Setters
    public Integer getEventID() {
        return eventID;
    }

    public void setEventID(Integer id) {
        this.eventID = id;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String type) {
        this.eventType = type;
    }

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime date) {
        this.dateModified = date;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changed) {
        this.changedBy = changed;
    }

    public String getFromValue() {
        return fromValue;
    }

    public void setFromValue(String value) {
        this.fromValue = value;
    }

    public String getToValue() {
        return toValue;
    }

    public void setToValue(String value) {
        this.toValue = value;
    }
}
