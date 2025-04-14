package com.accountingAPI.accountingSoftware.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.ErrorMessage;
import com.accountingAPI.accountingSoftware.repository.ErrorRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errorRepo;

    public ErrorMessage createError(String message, int errorID) {

        ErrorMessage newError = new ErrorMessage();

        newError.setMessage(message);
        newError.setErrorId(errorID);
        newError.setTimestamp(LocalDateTime.now());

        return newError; 
    }


    public void clearError(int errorId) {

        foreach (ErrorMessage messageEntry in errorRepo.findAll()) {
            if (messageEntry.getErrorId() == errorId) {

            }
        }
    }


    public ArrayList<String> getErrorsForEntry(int entryId) {

        ArrayList<String> allErrorsPerEntry = new ArrayList<String>();

        foreach (ErrorMessage messageEntry in errorRepo.findAll()) {
            if (messageEntry.getErrorId() == entryId) {
                allErrorsPerEntry.add(messageEntry.getMessage());
            }
        }

        return allErrorsPerEntry;
    }
}