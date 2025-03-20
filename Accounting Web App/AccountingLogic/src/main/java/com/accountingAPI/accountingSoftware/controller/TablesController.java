package com.accountingAPI.accountingSoftware.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accountingAPI.accountingSoftware.model.RequestedUser;
import com.accountingAPI.accountingSoftware.model.Users;
import com.accountingAPI.accountingSoftware.repository.RequestedUserRepository;
import com.accountingAPI.accountingSoftware.repository.UserRepository;

@RestController
@RequestMapping("")  // Base URL for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access backend
public class TablesController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestedUserRepository requestedUserRepository;

    // GET ALL USERS (ADMIN ONLY)
    @PostMapping("/getAllUsers")
    public ResponseEntity<?> getAllUsers(@RequestBody Map<String, String> requestData) {
        String makerID = requestData.get("makerID");
        Optional<Users> adminUser = userRepository.findByUserID(makerID);
        if (adminUser.isEmpty() || !adminUser.get().getUserType().equals("Admin")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not authorized. Only admins can access this data."));
        }

        if (!adminUser.get().getActive()){
            return ResponseEntity.badRequest().body(Map.of("error", "Not authorized. Account Deactivated."));
        }
        List<Users> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    // GET ALL REQUESTED USERS (ADMIN ONLY)
    @PostMapping("/getAllRequestedUsers")
    public ResponseEntity<?> getAllRequestedUsers(@RequestBody Map<String, String> requestData) {
        String makerID = requestData.get("makerID");
        System.out.println("MakerID: " + makerID);
        Optional<Users> adminUser = userRepository.findByUserID(makerID);
        if (adminUser.isEmpty() || !adminUser.get().getUserType().equals("Admin")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Not authorized. Only admins can access this data."));
        }

        if (!adminUser.get().getActive()){
            return ResponseEntity.badRequest().body(Map.of("error", "Not authorized. Account Deactivated."));
        }

        List<RequestedUser> requestUsers = requestedUserRepository.findAll();
        

        return ResponseEntity.ok(requestUsers);
    }
}
