@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // Endpoint for ratio data
    @GetMapping("/ratios")
    public ResponseEntity<?> getRatios(@RequestParam String userId) {
        return ResponseEntity.ok(dashboardService.computeAllRatios());
    }

    // Count of pending messages
    @GetMapping("/notifications/count")
    public ResponseEntity<?> getNotificationCount(@RequestParam String userId) {
        return ResponseEntity.ok(dashboardService.getPendingApprovalCount(userId));
    }
}
