package com.accountingAPI.accountingSoftware.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "RequestedUser") // Match your actual database table name
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestedUser {
    @Id
    @Column(name = "UserNameID", length=50, nullable=false)
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

    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    public Date getDOB(){
        return dob;
    }
    public void setDOB(Date newDob){
        dob = newDob;
    }


    @Column(name = "Address", length = 100)
    private String address;
    public String getAddress(){
        return address;
    }
    public void setAddress(String newAddress){
        address = newAddress;
    }

    @Column(name="Email")
    private String email = "";
    public String getEmail(){
        return email;
    }
    public void setEmail(String newEmail){
        email = newEmail;
    }
}