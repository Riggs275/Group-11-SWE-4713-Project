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
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Users {

    @Id
    @Column(name = "UserNameID", length = 50, nullable = false)
    private String userNameID;
    public String getUserName(){
        return userNameID;
    }
    public void setUserName(String newID){
        userNameID = newID;
    }

    @Column(name = "FirstName", length = 100)
    private String firstName;
     public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String newFName){
        firstName = newFName;
    }

    @Column(name = "LastName", length = 100)
    private String lastName;

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String newLName){
        lastName = newLName;
    }

    @Column(name = "PasswordRef")
    private Integer passwordRef;
     public Integer getPassRef(){
        return passwordRef;
    }
    public void setPassRef(Integer newPassRef){
        passwordRef = newPassRef;
    }

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    public Date getDOB(){
        return dob;
    }
    public void setDOB(Date newDob){
        dob = newDob;
    }

    @Column(name = "DateAdded", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    public Date getDateAdded(){
        return dateAdded;
    }
    public void setDateAdded(Date newDateAdded){
        dateAdded = newDateAdded;
    }

    @Column(name = "UserType", length = 50)
    private String userType;
    public String getUserType(){
        return userType;
    }
    public void setUserType(String newUserType){
        userType = newUserType;
    }

    @Column(name = "Address", length = 100)
    private String address;
    public String getAddress(){
        return address;
    }
    public void setAddress(String newAddress){
        address = newAddress;
    }

    @Column(name = "SecurityQuestion", length = 100)
    private String securityQuestion = "How many dogs do you have in your childhood";
    public String getSecurityQ(){
        return securityQuestion;
    }
    public void setSecurityQ(String newSecurityQ){
        securityQuestion = newSecurityQ;
    }

    @Column(name = "SecurityAnswer", length = 100)
    private String securityAnswer = "3";
    public String getSecurityAnswer(){
        return securityAnswer;
    }
    public void setSecurityAnswer(String newSecAnswer){
        securityAnswer = newSecAnswer;
    }

    @Column(name = "ActiveStatus")
    private Boolean activeStatus = false;
    public Boolean getActive(){
        return activeStatus;
    }
    public void setActive(Boolean newActive){
        activeStatus = newActive;
    }

    @Column(name="Email")
    private String email = "";
    public String getEmail(){
        return email;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }

    private String role;
     public String getRole(){
        return role;
    }
    public void setRole(String roleSet){
        role = roleSet;
    }

    @PrePersist
    protected void onCreate() {
        // Automatically set DateAdded when creating a new user
        dateAdded = new Date();
    }




}
