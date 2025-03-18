package com.accountingAPI.accountingSoftware.service;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.Passwords;
import com.accountingAPI.accountingSoftware.model.User;
import com.accountingAPI.accountingSoftware.repository.PasswordRepository;
import com.accountingAPI.accountingSoftware.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordRepository passwordRepository;

    public ResponseEntity<?> loginUser(Map<String, String> loginData) {
        String userNameid = loginData.get("userID");
        String sentPassword = loginData.get("password");
        Optional<User> user = userRepository.findByUserID(userNameid);
        System.out.println("Received user_nameid: " + userNameid);
        System.out.println("Recieved password: " + sentPassword);
        System.out.print("USER" + user.toString());
    //Password ref should use the userrepository to get the table data from it.
        if (user.isPresent()) {
            Optional<Passwords> pass = userRepository.findPasswordByUserId(user.get().getPassRef());
            System.out.print(pass.toString());

            if(pass.isPresent()){
                return ResponseEntity.ok().body(Map.of("message", "Login successful"));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
            }
        } else {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid credentials"));
        }
    }

    public ResponseEntity<?> forgotPassword(Map<String,String> forgotPasswordData){
        String email = forgotPasswordData.get("email");
        String userID = forgotPasswordData.get("userID");
        Optional<User> user = userRepository.findByUserID(userID);
        System.out.println("Recieved user_id: " + userID);
        System.out.println("USER: " + (user.isPresent() ? user.get().toString() : "User not found"));
        if(user.isPresent()){
            if(user.get().getEmail().equalsIgnoreCase(email)){
                return ResponseEntity.ok().body(Map.of("message", "email found","userID", userID, "userQ1", user.get().getSecurityQ()));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Could not find ID or email"));
            }
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Could not find ID or email"));
        }
    }
    //Only checks answer 1 currently but soon will do better
    public ResponseEntity<?> securityQuestions(Map<String,String> securityData, String userID){
        String answer1 = securityData.get("sec1");


        Optional<User> user = userRepository.findByUserID(userID);
        if(user.isPresent()){
            Boolean match1 = user.get().getSecurityAnswer().equals(answer1);
            if (match1){
                return ResponseEntity.ok().body(Map.of("message", "security questions correct", "userID", userID));
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "security questions not correct"));
            }
        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "Could not find ID or email"));
        }   
    }


    public ResponseEntity<?> setNewPassword(Map<String,String> newPasswordData, String userID){
        String newPassword = newPasswordData.get("newPassword");

        if (newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Password cannot be empty"));
        }

        Optional<User> userOptional = userRepository.findByUserID(userID);

        if(userOptional.isPresent()){
            User user = userOptional.get();
            Optional<Passwords> passwordOptional = passwordRepository.findById(user.getPassRef());
            
            if(passwordOptional.isPresent()){
                Passwords password = passwordOptional.get();
                password.setPasswordHash(newPassword);
                passwordRepository.save(password);
            }else{
                return ResponseEntity.badRequest().body(Map.of("error", "Password record not found"));
            }
            return ResponseEntity.ok().body(Map.of("message", "Password updated successfully"));

        }else{
            return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
        }
    }
    /*public ResponseEntity<?> forgotAccount(Map<String, String> forgotData) {
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
    }*/
}
