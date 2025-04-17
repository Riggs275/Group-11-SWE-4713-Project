package com.accountingAPI.accountingSoftware.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.ErrorMessage;
import com.accountingAPI.accountingSoftware.repository.ErrorRepository;

@Service
public class ErrorService {

    @Autowired
    private ErrorRepository errRepo;

    public ResponseEntity<?> createError(Map<String, String> data) {
        ErrorMessage error = new ErrorMessage();
        error.setMessage(data.get("message"));
        error.setTimestamp(LocalDateTime.now());
        errRepo.save(error);
        return ResponseEntity.ok(error);
    }
    public ResponseEntity<?> clearError(Map<String, String> data) {
        int id = Integer.parseInt(data.get("errorId"));
        if (!errRepo.existsById(id)) {
            return ResponseEntity.badRequest().body("Error not found");
        }
        errRepo.deleteById(id);
        return ResponseEntity.ok("Error cleared");
    }
    public ResponseEntity<?> getErrorsForEntry(Map<String, String> data) {
        List<ErrorMessage> list = errRepo.findByErrorId(Integer.parseInt(data.get("entryId")));
        return ResponseEntity.ok(list);
    }
}
