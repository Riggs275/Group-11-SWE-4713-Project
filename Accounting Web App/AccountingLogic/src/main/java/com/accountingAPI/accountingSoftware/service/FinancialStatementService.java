package com.accountingAPI.accountingSoftware.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.FinancialStatement;
import com.accountingAPI.accountingSoftware.repository.FinancialStatementRepository;

@Service
public class FinancialStatementService {

    @Autowired
    private FinancialStatementRepository stmtRepo;

    public ResponseEntity<?> generateTrialBalance(Map<String, String> data) {
        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Trial Balance");
        financialstatement.setFromDate(LocalDate.parse(data.get("fromDate")));
        financialstatement.setToDate(LocalDate.parse(data.get("toDate")));
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        //
        // financialstatement.setFileData(generatePdf(...));
        //
        stmtRepo.save(financialstatement);
        return ResponseEntity.ok(financialstatement);
    }
    public ResponseEntity<?> generateIncomeStatement(Map<String, String> data) {
        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Income Statement");
        financialstatement.setFromDate(LocalDate.parse(data.get("fromDate")));
        financialstatement.setToDate(LocalDate.parse(data.get("toDate")));
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        //
        // compute totals, populate financialstatement.fileData
        //
        stmtRepo.save(financialstatement);
        return ResponseEntity.ok(financialstatement);
    }
    public ResponseEntity<?> generateBalanceSheet(Map<String, String> data) {
        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Balance Sheet");
        financialstatement.setFromDate(LocalDate.parse(data.get("fromDate")));
        financialstatement.setToDate(LocalDate.parse(data.get("toDate")));
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        //
        // build assets vs liabilities
        //
        stmtRepo.save(financialstatement);
        return ResponseEntity.ok(financialstatement);
    }
    public ResponseEntity<?> generateRetainedEarnings(Map<String, String> data) {
        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Retained Earnings");
        financialstatement.setFromDate(LocalDate.parse(data.get("fromDate")));
        financialstatement.setToDate(LocalDate.parse(data.get("toDate")));
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        //
        // income less dividends
        //
        stmtRepo.save(financialstatement);
        return ResponseEntity.ok(financialstatement);
    }

    public ResponseEntity<?> getStatementByDateRange(Map<String, String> data) {
        LocalDate from = LocalDate.parse(data.get("fromDate"));
        LocalDate to   = LocalDate.parse(data.get("toDate"));
        List<FinancialStatement> list = stmtRepo.findByFromDateGreaterThanEqualAndToDateLessThanEqual(from, to);
        return ResponseEntity.ok(list);
    }

    public ResponseEntity<?> emailStatement(Map<String, String> data) {
        int id = Integer.parseInt(data.get("statementId"));
        var opt = stmtRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Statement not found");
        FinancialStatement financialstatement = opt.get();
        //
        // send financialstatement.fileData via EmailService
        //
        return ResponseEntity.ok("Statement emailed");
    }
    public ResponseEntity<?> downloadStatement(Map<String, String> data) {
        int id = Integer.parseInt(data.get("statementId"));
        var opt = stmtRepo.findById(id);
        if (opt.isEmpty()) return ResponseEntity.badRequest().body("Statement not found");
        FinancialStatement financialstatement = opt.get();
        return ResponseEntity.ok()
            .header("Content-Disposition", "attachment; filename=" + financialstatement.getType() + ".pdf")
            .body(financialstatement.getFileData());
    }
}
