package com.accountingAPI.accountingSoftware.controller;

import com.accountingAPI.accountingSoftware.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // Endpoint for ratio data
    @GetMapping("/ratios")
    public ResponseEntity<?> getRatios(@RequestParam String userId) {
        return ResponseEntity.ok(dashboardService.computeAllRatios(userId));
    }

    // Count of pending messages
    @GetMapping("/notifications/count")
    public ResponseEntity<?> getNotificationCount(@RequestParam String userId) {
        return ResponseEntity.ok(dashboardService.getPendingApprovalCount(userId));
    }
}
