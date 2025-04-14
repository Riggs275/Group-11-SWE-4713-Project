package com.accountingAPI.accountingSoftware.controllers;

import com.accountingAPI.accountingSoftware.services.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/error")
@CrossOrigin(origins = "http://localhost:3000")
public class ErrorController {

    @Autowired
    private ErrorService errorService;

    @PostMapping("/create")
    public ResponseEntity<?> createError(@RequestBody Map<String, String> data) {
        return errorService.createError(data);
    }

    @PostMapping("/clear")
    public ResponseEntity<?> clearError(@RequestBody Map<String, String> data) {
        return errorService.clearError(data);
    }

    @PostMapping("/get")
    public ResponseEntity<?> getErrorsForEntry(@RequestBody Map<String, String> data) {
        return errorService.getErrorsForEntry(data);
    }
}
