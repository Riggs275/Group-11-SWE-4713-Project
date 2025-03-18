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
@RequestMapping("login")  // Base URL for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3001") // Allow frontend to access backend
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginUserRequest")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        return userService.loginUser(loginData);
    }
    @PostMapping("/forgotAccountRequest")
    public ResponseEntity<?> resetPassword(@RequestBody Map<String,String> forgotPasswordData){
        return userService.forgotPassword(forgotPasswordData);
    }
    @PostMapping("/checkSecurityQ")
    public ResponseEntity<?> securityQ(@RequestBody Map<String,String> securityData, String userID){
        return userService.securityQuestions(securityData, userID);
    }
    @PostMapping("/setNewPassword")
    public ResponseEntity<?> setNewP(@RequestBody Map<String,String> passwordData, String userID){
        return userService.setNewPassword(passwordData, userID);
    }
/*
    @PostMapping("/forgotAccountRequest")
    public ResponseEntity<?> forgotAccount(@RequestBody Map<String, String> forgotData) {
        return userService.forgotAccount(forgotData);
    }

    @PostMapping("/createAccountRequest")
    public ResponseEntity<?> createAccount(@RequestBody User user) {
        return userService.createAccount(user);
    }

    @PostMapping("/setNewPassword")
    public ResponseEntity<?> setNewPassword(@RequestBody Map<String, String> passwordData) {
        return userService.setNewPassword(passwordData);
    }

    @PostMapping("/checkSecurityQ")
    public ResponseEntity<?> checkSecurityQuestion(@RequestBody Map<String, String> securityData) {
        return userService.checkSecurityQuestion(securityData);
    }*/
}
