package com.accountingAPI.accountingSoftware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepo;

    public void notifyManager(String message) {
        List<User> managerUsers = userRepository.findByRole("Manager");
        
        for (int i = 0; i < managerUsers.size(); i++) {
            User managerUser = managerUsers.get(i);
            SimpleMailMessage notif = new SimpleMailMessage();
            notif.setTo(managerUser.getEmail());
            notif.setSubject("Radiant Flow Accounting");
            notif.setText(notificationMessage);

            mailSender.send(notif);
        }
    }
}
