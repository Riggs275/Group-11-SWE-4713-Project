package com.accountingAPI.accountingSoftware.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.RequestedUser;
import com.accountingAPI.accountingSoftware.model.Users;
import com.accountingAPI.accountingSoftware.repository.RequestedUserRepository;
import com.accountingAPI.accountingSoftware.repository.UserRepository;



@Service
public class TablesService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RequestedUserRepository requestedUserRepository;


    public ResponseEntity<?> requestUserTable(){
        List<Users> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<?> requestRequestedTable(){
        List<RequestedUser> requestUsers = requestedUserRepository.findAll();
        return ResponseEntity.ok(requestUsers);
    }
}