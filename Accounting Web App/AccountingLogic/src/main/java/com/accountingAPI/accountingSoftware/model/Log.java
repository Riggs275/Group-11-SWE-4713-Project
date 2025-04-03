package com.accountingAPI.accountingSoftware.components;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "logs")
public class Log {

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
        id = eventID;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String type) {
        type = eventType;
    }
    public LocalDateTime getDateModified() {
        return dateModified;
    }
    public void setDateModified(LocalDateTime date) {
        date = dateModified;
    }
    public String getChangedBy() {
        return changedBy;
    }
    public void setChangedBy(String changed) {
        changed = changedBy;
    }
    public String getFromValue() {
        return fromValue;
    }
    public void setFromValue(String value) {
        value = fromValue;
    }
    public String getToValue() {
        return toValue;
    }
    public void setToValue(String value) {
        value = toValue;
    }
}
