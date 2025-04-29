package com.accountingAPI.accountingSoftware.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.Users;

import com.accountingAPI.accountingSoftware.repository.UserRepository;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private JavaMailSender mailSender;

    public void notifyManager(String message, String notificationMessage) {
        List<Users> managerUsers = userRepo.findByRole("Manager");
        
        for (int i = 0; i < managerUsers.size(); i++) {
            Users managerUser = managerUsers.get(i);
            SimpleMailMessage notif = new SimpleMailMessage();
            notif.setTo(managerUser.getEmail());
            notif.setSubject("Radiant Flow Accounting");
            notif.setText(notificationMessage);

            mailSender.send(notif);
        }
    }
}
