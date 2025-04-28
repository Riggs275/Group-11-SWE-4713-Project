package com.accountingAPI.accountingSoftware.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.model.FinancialStatement;
import com.accountingAPI.accountingSoftware.repository.FinancialStatementRepository;
import com.accountingAPI.accountingSoftware.model.LedgerEntry;
import com.accountingAPI.accountingSoftware.repository.LedgerRepository;
import com.accountingAPI.accountingSoftware.service.PdfGeneratorService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Service
public class FinancialStatementService {

    @Autowired
    private FinancialStatementRepository statementRepository;

    @Autowired
    private LedgerRepository ledgerRepository;

    @Autowired
    private PdfGeneratorService pdfGenerator;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> generateTrialBalance(Map<String, String> data) {
        LocalDate from = LocalDate.parse(data.get("fromDate"));
        LocalDate to   = LocalDate.parse(data.get("toDate"));
        List<LedgerEntry> entries = ledgerRepository.findByAccountName(data.get("accountName"));
        double debits  = entries.stream().mapToDouble(LedgerEntry::getDebit).sum();
        double credits = entries.stream().mapToDouble(LedgerEntry::getCredit).sum();

        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Trial Balance");
        financialstatement.setFromDate(from);
        financialstatement.setToDate(to);
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        
        financialstatement.setFileData(pdfGenerator.generateTrialBalance(from, to, debits, credits));
        statementRepository.save(financialstatement);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=trial_balance.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(financialstatement.getFileData());
    }
    public ResponseEntity<?> generateIncomeStatement(Map<String, String> data) {
        LocalDate from = LocalDate.parse(data.get("fromDate"));
        LocalDate to   = LocalDate.parse(data.get("toDate"));
        double revenue  = ledgerRepository.findByAccountName("Revenue").stream().mapToDouble(LedgerEntry::getCredit).sum();
        double expenses = ledgerRepository.findByAccountName("Expenses").stream().mapToDouble(LedgerEntry::getDebit).sum();

        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Income Statement");
        financialstatement.setFromDate(from);
        financialstatement.setToDate(to);
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        financialstatement.setFileData(pdfGenerator.generateIncomeStatement(from, to, revenue, expenses));
        statementRepository.save(financialstatement);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=income_statement.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(financialstatement.getFileData());
    }
    public ResponseEntity<?> generateBalanceSheet(Map<String, String> data) {
        LocalDate to = LocalDate.parse(data.get("toDate"));
        double assets      = ledgerRepository.findByAccountName("Assets").stream().mapToDouble(LedgerEntry::getDebit).sum();
        double liabilities = ledgerRepository.findByAccountName("Liabilities").stream().mapToDouble(LedgerEntry::getCredit).sum();

        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Balance Sheet");
        financialstatement.setFromDate(LocalDate.parse(data.get("fromDate")));
        financialstatement.setToDate(to);
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        financialstatement.setFileData(pdfGenerator.generateBalanceSheet(to, assets, liabilities));
        statementRepository.save(financialstatement);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=balance_sheet.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(financialstatement.getFileData());
    }
    public ResponseEntity<?> generateRetainedEarnings(Map<String, String> data) {
        LocalDate from = LocalDate.parse(data.get("fromDate"));
        LocalDate to   = LocalDate.parse(data.get("toDate"));
        double revenue  = ledgerRepository.findByAccountName("Revenue").stream().mapToDouble(LedgerEntry::getCredit).sum();
        double expenses = ledgerRepository.findByAccountName("Expenses").stream().mapToDouble(LedgerEntry::getDebit).sum();
        double dividends= ledgerRepository.findByAccountName("Dividends").stream().mapToDouble(LedgerEntry::getDebit).sum();
        double netIncome = revenue - expenses;
        double retainedEarnings = netIncome - dividends;

        FinancialStatement financialstatement = new FinancialStatement();
        financialstatement.setType("Retained Earnings");
        financialstatement.setFromDate(from);
        financialstatement.setToDate(to);
        financialstatement.setGeneratedBy(data.get("generatedBy"));
        financialstatement.setFileData(pdfGenerator.generateRetainedEarnings(from, to, netIncome, dividends, retainedEarnings));
        statementRepository.save(financialstatement);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=retained_earnings.pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(financialstatement.getFileData());
    }

    public ResponseEntity<?> getStatementByDateRange(Map<String, String> data) {
        LocalDate from = LocalDate.parse(data.get("fromDate"));
        LocalDate to   = LocalDate.parse(data.get("toDate"));
        List<FinancialStatement> list = statementRepository.findByFromDateGreaterThanEqualAndToDateLessThanEqual(from, to);
        return ResponseEntity.ok(list);
    }

    public BodyBuilder emailStatement(Map<String, String> data) throws Exception {
        int id = Integer.parseInt(data.get("statementId"));
        String type = statementRepository.getType(id);
        byte[] fileData = statementRepository.getFileData(id);
        emailService.sendEmailWithAttachment(
            data.get("recipientEmail"),
            "Your " + type,
            "Please find attached your " + type,
            fileData,
            type.toLowerCase().replace(" ", "_") + ".pdf"
        );
        return ResponseEntity.ok();
    }
    public ResponseEntity<?> downloadStatement(Map<String, String> data) {
        int id = Integer.parseInt(data.get("statementId"));
        String type = statementRepository.getType(id);
        byte[] fileData = statementRepository.getFileData(id);
        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=" + type.toLowerCase().replace(" ", "_") + ".pdf")
            .contentType(MediaType.APPLICATION_PDF)
            .body(fileData);
    }
}
