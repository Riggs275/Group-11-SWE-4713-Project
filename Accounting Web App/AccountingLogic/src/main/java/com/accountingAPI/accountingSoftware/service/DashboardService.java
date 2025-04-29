package com.accountingAPI.accountingSoftware.service;

import com.accountingAPI.accountingSoftware.model.LedgerEntry;
import com.accountingAPI.accountingSoftware.model.RatioResult;
import com.accountingAPI.accountingSoftware.repository.JournalEntryRepository;
import com.accountingAPI.accountingSoftware.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    // Calculate every financial ratio and its R/Y/G status
    public ResponseEntity<Map<String, RatioResult>> computeAllRatios() {
        Map<String, RatioResult> ratios = new LinkedHashMap<>();

        ratios.put("Current Ratio",        currentRatioStatus());
        ratios.put("Quick Ratio",          quickRatioStatus());
        ratios.put("Cash Ratio",           cashRatioStatus());
        ratios.put("Debt to Equity Ratio", debtEquityRatioStatus());
        ratios.put("Debt Ratio",           debtRatioStatus());
        ratios.put("Interest Coverage",    interestCoverageStatus());
        ratios.put("Inventory Turnover",   inventoryTurnoverStatus());
        ratios.put("Receivables Turnover", receivablesTurnoverStatus());
        ratios.put("Asset Turnover",       assetTurnoverStatus());
        ratios.put("Gross Profit Margin",  grossProfitMarginStatus());
        ratios.put("Net Profit Margin",    netProfitMarginStatus());
        ratios.put("Return on Assets",     returnOnAssetsStatus());
        ratios.put("Return on Equity",     returnOnEquityStatus());

        return ResponseEntity.ok(ratios);
    }

    // Number of pending approvals for adjusting entries
    public ResponseEntity<Integer> getPendingApprovalCount() {
        int count = journalEntryRepository.countByStatus("PENDING");
        return ResponseEntity.ok(count);
    }

    // Ratio methods

    private RatioResult currentRatioStatus() {
        double currentAssets      = sumCategory("Current Assets");
        double currentLiabilities = sumCategory("Current Liabilities");
        double ratio = safeDivide(currentAssets, currentLiabilities);
        String status = (ratio > 1.5) ? "GREEN" : (ratio >= 1.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult quickRatioStatus() {
        double currentAssets      = sumCategory("Current Assets");
        double inventories        = sumCategory("Inventory");
        double currentLiabilities = sumCategory("Current Liabilities");
        double ratio = safeDivide(currentAssets - inventories, currentLiabilities);
        String status = (ratio > 1.0) ? "GREEN" : (ratio >= 0.8) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult cashRatioStatus() {
        double cash                = sumCategory("Cash and Cash Equivalents");
        double currentLiabilities  = sumCategory("Current Liabilities");
        double ratio = safeDivide(cash, currentLiabilities);
        String status = (ratio > 0.5) ? "GREEN" : (ratio >= 0.2) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult debtEquityRatioStatus() {
        double totalLiabilities    = sumCategory("Total Liabilities");
        double equity              = sumCategory("Shareholders' Equity");
        double ratio = safeDivide(totalLiabilities, equity);
        String status = (ratio < 1.0) ? "GREEN" : (ratio <= 2.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult debtRatioStatus() {
        double totalLiabilities = sumCategory("Total Liabilities");
        double totalAssets      = sumCategory("Total Assets");
        double ratio = safeDivide(totalLiabilities, totalAssets);
        String status = (ratio < 0.5) ? "GREEN" : (ratio <= 0.7) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult interestCoverageStatus() {
        double ebit            = sumCategory("EBIT");
        double interestExpense = sumCategory("Interest Expense");
        double ratio = safeDivide(ebit, interestExpense);
        String status = (ratio > 5.0) ? "GREEN" : (ratio >= 2.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult inventoryTurnoverStatus() {
        double cogs             = sumCategory("Cost of Goods Sold");
        double avgInventory     = sumCategory("Average Inventory");
        double ratio = safeDivide(cogs, avgInventory);
        // Higher is better—no strict thresholds, so we mark > 8 as green, 4–8 as yellow, <4 as red
        String status = (ratio > 8) ? "GREEN" : (ratio >= 4) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult receivablesTurnoverStatus() {
        double netCreditSales       = sumCategory("Net Credit Sales");
        double avgAccountsReceivable= sumCategory("Average Accounts Receivable");
        double ratio = safeDivide(netCreditSales, avgAccountsReceivable);
        String status = (ratio > 10) ? "GREEN" : (ratio >= 5) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult assetTurnoverStatus() {
        double netSales    = sumCategory("Net Sales");
        double avgAssets   = sumCategory("Average Total Assets");
        double ratio = safeDivide(netSales, avgAssets);
        String status = (ratio > 1.5) ? "GREEN" : (ratio >= 1.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult grossProfitMarginStatus() {
        double grossProfit = sumCategory("Gross Profit");
        double netSales    = sumCategory("Net Sales");
        double ratio = safeDivide(grossProfit, netSales);
        String status = (ratio > 0.4) ? "GREEN" : (ratio >= 0.2) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult netProfitMarginStatus() {
        double netIncome = sumCategory("Net Income");
        double netSales  = sumCategory("Net Sales");
        double ratio = safeDivide(netIncome, netSales);
        String status = (ratio > 0.15) ? "GREEN" : (ratio >= 0.05) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult returnOnAssetsStatus() {
        double netIncome = sumCategory("Net Income");
        double avgAssets = sumCategory("Average Total Assets");
        double ratio = safeDivide(netIncome, avgAssets);
        String status = (ratio > 0.05) ? "GREEN" : (ratio >= 0.01) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    private RatioResult returnOnEquityStatus() {
        double netIncome   = sumCategory("Net Income");
        double avgEquity   = sumCategory("Average Shareholders' Equity");
        double ratio = safeDivide(netIncome, avgEquity);
        String status = (ratio > 0.15) ? "GREEN" : (ratio >= 0.10) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Division method for error reduction
    private double safeDivide(double a, double b) {
        return (b == 0) ? 0 : (a / b);
    }

    // Sum ledger entries for a given account or category name
    private double sumCategory(String accountName) {
        List<LedgerEntry> entries = ledgerRepository.findByAccountName(accountName);
        return entries.stream()
                      .mapToDouble(e -> e.getDebit() - e.getCredit())
                      .sum();
    }
}
