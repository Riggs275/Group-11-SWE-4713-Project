package com.accountingAPI.accountingSoftware.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "User") // Match your actual database table name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @Column(name = "UserNameID", length = 50, nullable = false)
    private String userNameID;

    @Column(name = "FirstName", length = 100)
    private String firstName;

    @Column(name = "LastName", length = 100)
    private String lastName;

    @Column(name = "PasswordRef")
    private Integer passwordRef; // Assuming PasswordRef is an integer reference

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(name = "DateAdded", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    @Column(name = "UserType", length = 50)
    private String userType;

    @Column(name = "Address", length = 100)
    private String address;

    @Column(name = "SecurityQuestion", length = 100)
    private String securityQuestion;

    @Column(name = "SecurityAnswer", length = 100)
    private String securityAnswer;

    @Column(name = "ActiveStatus")
    private Boolean activeStatus;

    @PrePersist
    protected void onCreate() {
        dateAdded = new Date(); // Automatically set DateAdded when creating a new user
    }


}
