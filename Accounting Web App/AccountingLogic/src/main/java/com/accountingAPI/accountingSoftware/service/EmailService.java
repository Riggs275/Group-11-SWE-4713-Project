package com.accountingAPI.accountingSoftware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public ResponseEntity<?> sendEmail(String toEmail, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply@mailslurp.biz"); // ✅ Must match MailSlurp domain
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(content);

        mailSender.send(message);
        System.out.println("✅ Email sent successfully to " + toEmail);
        return null;
    }

    public void sendEmailWithAttachment(String recipient, String subject, String text, byte[] fileData, String fileName) throws Exception {

        MimeMessage mimeMsg = null;
        MimeMessageHelper msgHelper = null;

        try {
            mimeMsg = mailSender.createMimeMessage();
            msgHelper = new MimeMessageHelper(mimeMsg, true); // true => multipart message

            msgHelper.setTo(recipient);
            msgHelper.setSubject(subject);
            msgHelper.setText(text);

            ByteArrayResource attachResource = new ByteArrayResource(fileData);
            msgHelper.addAttachment(fileName, attachResource);

            mailSender.send(mimeMsg);
        }
        catch (MessagingException e) {
            throw new Exception("Failed to send email", e);
        }
    }
}
