package com.accountingAPI.accountingSoftware.service;

import com.accountingAPI.accountingSoftware.model.User;
import com.accountingAPI.accountingSoftware.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> loginUser(Map<String, String> loginData) {
        Optional<User> user = userRepository.findByUsername(loginData.get("username"));
    //Password ref should use the userrepository to get the table data from it.
        if (user.isPresent() && user.get().getPassRef().equals(loginData.get("password"))) {
            return ResponseEntity.ok().body(Map.of("message", "Login successful"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
    }

    public ResponseEntity<?> forgotAccount(Map<String, String> forgotData) {
        Optional<User> user = userRepository.findByEmail(forgotData.get("email"));

        if (user.isPresent()) {
            return ResponseEntity.ok().body(Map.of("message", "Account found, security questions sent"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "No account found with this email"));
        }
    }

    public ResponseEntity<?> createAccount(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Username already exists"));
        }
        userRepository.save(user);
        return ResponseEntity.ok().body(Map.of("message", "Account created successfully"));
    }

    public ResponseEntity<?> setNewPassword(Map<String, String> passwordData) {
        Optional<User> user = userRepository.findByUsername(passwordData.get("username"));

        if (user.isPresent()) {
            User updatedUser = user.get();
            updatedUser.setPassword(passwordData.get("newPassword"));
            userRepository.save(updatedUser);
            return ResponseEntity.ok().body(Map.of("message", "Password updated successfully"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
        }
    }

    public ResponseEntity<?> checkSecurityQuestion(Map<String, String> securityData) {
        Optional<User> user = userRepository.findByUsername(securityData.get("username"));

        if (user.isPresent() && user.get().getSecurityAnswer().equals(securityData.get("answer"))) {
            return ResponseEntity.ok().body(Map.of("message", "Security question answered correctly"));
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Incorrect security answer"));
        }
    }
}
