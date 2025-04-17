package com.accountingAPI.accountingSoftware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepo;

    public ResponseEntity<?> notifyManager(String message) {
        //
        // Method will need to find users by userType == "Manager" and notify
        //
        return ResponseEntity.ok("Managers notified");
    }
}
