package com.accountingAPI.accountingSoftware.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountingAPI.accountingSoftware.model.User;
import com.accountingAPI.accountingSoftware.service.UserService;

@RestController
@RequestMapping("/api")  // Base URL for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access backend
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/loginUserRequest")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> loginData) {
        return userService.loginUser(loginData);
    }

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
    }
}
