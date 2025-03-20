package com.accountingAPI.accountingSoftware.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountingAPI.accountingSoftware.service.UserService;

@RestController
@RequestMapping("")  // Base URL for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access backend
public class UserController {

    @Autowired
    private UserService userService;

    // 🔹 LOGIN USER
    @PostMapping("/loginUserRequest")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        return userService.loginUser(loginData);
    }

    // 🔹 FORGOT ACCOUNT REQUEST
    @PostMapping("/forgotAccountRequest")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String, String> forgotPasswordData) {
        return userService.forgotPassword(forgotPasswordData);
    }

    // 🔹 CHECK SECURITY QUESTION
    @PostMapping("/checkSecurityQ")
    public ResponseEntity<?> securityQ(@RequestBody Map<String, String> securityData) {
        String userID = securityData.get("userID");
        return userService.securityQuestions(securityData, userID);
    }

    // 🔹 SET NEW PASSWORD
    @PostMapping("/setNewPassword")
    public ResponseEntity<?> setNewPassword(@RequestBody Map<String, String> passwordData) {
        String userID = passwordData.get("userID");
        return userService.setNewPassword(passwordData, userID);
    }

    // 🔹 CREATE A REQUESTED ACCOUNT (User requests an account, Admin must approve)
    @PostMapping("/createAccountRequest")
    public ResponseEntity<?> createAccountRequest(@RequestBody Map<String, String> accountData) {
        return userService.createRequestedUser(accountData);
    }

    // ADMIN CREATES ACCOUNT
    @PostMapping("/createAccount")
    public ResponseEntity<?> createAccount(@RequestBody Map<String, String> accountData) {
        String makerID = accountData.get("makerID"); // Extract makerID from JSON
        return userService.createAccount(accountData, makerID);
    }

    // 🔹 ADMIN ACCEPTS REQUESTED USER AND CONVERTS TO FULL ACCOUNT
    @PostMapping("/acceptRequest")
    public ResponseEntity<?> acceptUserRequest(@RequestBody Map<String, String> accountData) {
        String makerID = accountData.get("makerID");
        return userService.acceptRequest(accountData, makerID);
    }

    // 🔹 ADMIN DENIES REQUESTED USER
    @PostMapping("/denyRequest")
    public ResponseEntity<?> denyUserRequest(@RequestBody Map<String, String> accountData) {
        String makerID = accountData.get("makerID");
        return userService.denyRequest(accountData, makerID);
    }

    // 🔹 ADMIN EDITS USER DETAILS
    @PostMapping("/editUser")
    public ResponseEntity<?> editUser(@RequestBody Map<String, String> userData) {
        String makerID = userData.get("makerID"); // Ensure the request is made by an admin
        return userService.editUserRequest(userData, makerID);
    }

    // 🔹 ADMIN CHANGES USER ACTIVE STATUS (Activate/Deactivate)
    @PostMapping("/changeUserStatus")
    public ResponseEntity<?> changeUserStatus(@RequestBody Map<String, String> userData) {
        String makerID = userData.get("makerID"); // Ensure the request is made by an admin
        return userService.changeUserStatus(userData, makerID);
    }

}
