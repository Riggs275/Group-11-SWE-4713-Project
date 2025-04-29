package com.accountingAPI.accountingSoftware.service;

import com.accountingAPI.accountingSoftware.model.LedgerEntry;
import com.accountingAPI.accountingSoftware.model.RatioResult;
import com.accountingAPI.accountingSoftware.repository.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatioService {

    @Autowired
    private LedgerRepository ledgerRepository;

    // Current Ratio
    public RatioResult currentRatioStatus() {
        double assets = sumCategory("Current Assets");
        double liabilities = sumCategory("Current Liabilities");
        double ratio = safeDivide(assets, liabilities);
        String status = (ratio > 1.5) ? "GREEN" : (ratio >= 1.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Quick Ratio
    public RatioResult quickRatioStatus() {
        double assets = sumCategory("Current Assets");
        double inventory = sumCategory("Inventory");
        double liabilities = sumCategory("Current Liabilities");
        double ratio = safeDivide(assets - inventory, liabilities);
        String status = (ratio > 1.0) ? "GREEN" : (ratio >= 0.8) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Cash Ratio
    public RatioResult cashRatioStatus() {
        double cash = sumCategory("Cash and Cash Equivalents");
        double liabilities = sumCategory("Current Liabilities");
        double ratio = safeDivide(cash, liabilities);
        String status = (ratio > 0.5) ? "GREEN" : (ratio >= 0.2) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Debt-to-Equity
    public RatioResult debtEquityStatus() {
        double debt = sumCategory("Total Liabilities");
        double equity = sumCategory("Shareholders' Equity");
        double ratio = safeDivide(debt, equity);
        String status = (ratio < 1.0) ? "GREEN" : (ratio <= 2.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Debt Ratio
    public RatioResult debtRatioStatus() {
        double debt = sumCategory("Total Liabilities");
        double assets = sumCategory("Total Assets");
        double ratio = safeDivide(debt, assets);
        String status = (ratio < 0.5) ? "GREEN" : (ratio <= 0.7) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Interest Coverage
    public RatioResult interestCoverageStatus() {
        double ebit = sumCategory("EBIT");
        double interest = sumCategory("Interest Expense");
        double ratio = safeDivide(ebit, interest);
        String status = (ratio > 5.0) ? "GREEN" : (ratio >= 2.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Inventory Turnover
    public RatioResult inventoryTurnoverStatus() {
        double cogs = sumCategory("Cost of Goods Sold");
        double avgInv = sumCategory("Average Inventory");
        double ratio = safeDivide(cogs, avgInv);
        String status = (ratio > 8.0) ? "GREEN" : (ratio >= 4.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Receivables Turnover
    public RatioResult receivablesTurnoverStatus() {
        double sales = sumCategory("Net Credit Sales");
        double avgAR = sumCategory("Average Accounts Receivable");
        double ratio = safeDivide(sales, avgAR);
        String status = (ratio > 10.0) ? "GREEN" : (ratio >= 5.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Asset Turnover
    public RatioResult assetTurnoverStatus() {
        double sales = sumCategory("Net Sales");
        double avgAssets = sumCategory("Average Total Assets");
        double ratio = safeDivide(sales, avgAssets);
        String status = (ratio > 1.5) ? "GREEN" : (ratio >= 1.0) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Gross Profit Margin
    public RatioResult grossProfitMarginStatus() {
        double grossProfit = sumCategory("Gross Profit");
        double sales = sumCategory("Net Sales");
        double ratio = safeDivide(grossProfit, sales);
        String status = (ratio > 0.40) ? "GREEN" : (ratio >= 0.20) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Net Profit Margin/
    public RatioResult netProfitMarginStatus() {
        double netIncome = sumCategory("Net Income");
        double sales = sumCategory("Net Sales");
        double ratio = safeDivide(netIncome, sales);
        String status = (ratio > 0.15) ? "GREEN" : (ratio >= 0.05) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Return on Assets
    public RatioResult returnOnAssetsStatus() {
        double netIncome = sumCategory("Net Income");
        double avgAssets = sumCategory("Average Total Assets");
        double ratio = safeDivide(netIncome, avgAssets);
        String status = (ratio > 0.05) ? "GREEN" : (ratio >= 0.01) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Return on Equity
    public RatioResult returnOnEquityStatus() {
        double netIncome = sumCategory("Net Income");
        double avgEquity = sumCategory("Average Shareholders' Equity");
        double ratio = safeDivide(netIncome, avgEquity);
        String status = (ratio > 0.15) ? "GREEN" : (ratio >= 0.10) ? "YELLOW" : "RED";
        return new RatioResult(ratio, status);
    }

    // Division method for error reduction
    private double safeDivide(double a, double b) {
        return (b == 0.0) ? 0.0 : (a / b);
    }

    // Sum (Debit – Credit) for all ledger entries matching account name or category
    private double sumCategory(String accountName) {
        List<LedgerEntry> entries = ledgerRepository.findByAccountName(accountName);
        return entries.stream()
                      .mapToDouble(e -> e.getDebit() - e.getCredit())
                      .sum();
    }
}
