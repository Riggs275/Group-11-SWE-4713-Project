package com.accountingAPI.accountingSoftware.service;

import java.util.Map;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.accountingAPI.accountingSoftware.repository.LogRepository;
import com.accountingAPI.accountingSoftware.repository.AccountRepository;
import com.accountingAPI.accountingSoftware.model.JournalLine;
import com.accountingAPI.accountingSoftware.model.Log;
import com.accountingAPI.accountingSoftware.repository.JournalEntryRepository;
import com.accountingAPI.accountingSoftware.model.Account;
import com.accountingAPI.accountingSoftware.model.JournalEntry;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private JournalEntryRepository journalLineRepository;


    // Add new account
    public ResponseEntity<?> addAccount(Map<String, String> accountData) throws Exception {
        try {
            String accountName = accountData.get("accountName");
            int accountNumber = Integer.parseInt(accountData.get("accountNumber"));
            String accountDescription = accountData.get("accountDescription");
            char normalSide = accountData.get("normalSide").charAt(0);
            String accountCategory = accountData.get("accountCategory");
            String accountSubcategory = accountData.get("accountSubcategory");
            double initialBalance = Double.parseDouble(accountData.get("initialBalance"));
            double debit = Double.parseDouble(accountData.get("debit"));
            double credit = Double.parseDouble(accountData.get("credit"));
            double balance = Double.parseDouble(accountData.get("balance"));
            int orderField = Integer.parseInt(accountData.get("order"));
            String statement = accountData.get("statement");
            String comment = accountData.get("comment");
            String userId = accountData.get("userId");
        
            // Do not allow duplicate account names or numbers
            if (accountRepository.findByAccountName(accountName).isPresent() || accountRepository.findByAccountID(accountNumber).isPresent()) {
                throw new Exception("Duplicate account name or account number.");
            }
        
            // Validate account number format (no decimals or alphanumeric characters) and other fields
         
            // Create and populate an Account object
            Account account = new Account();
            account.setAccountName(accountName);
            account.setAccountNumber(accountNumber);
            account.setAccountDescription(accountDescription);
            account.setNormalSide(normalSide);
            account.setAccountCategory(accountCategory);
            account.setAccountSubcategory(accountSubcategory);
            account.setInitialBalance(initialBalance);
            account.setDebit(debit);
            account.setCredit(credit);
            account.setBalance(balance);
            account.setDateTimeAccountAdded();
            account.setUserId(userId);
            account.setOrderField(orderField);
            account.setStatement(statement);
            account.setComment(comment);
            account.setActive(true);

            // Save account to repository if name and number are valid
            Account savedAccount = accountRepository.save(account);

            // Log the account creation event
            Log logEntry = new Log("ADD", "", savedAccount.toString(), userId, LocalDateTime.now());
            logRepository.save(logEntry);

            return ResponseEntity.ok(savedAccount);
        }
        catch (Exception e) {
            throw new Exception("Error adding account" + e.getMessage());
        }
    }

    // Edit account details
    public ResponseEntity<?> editAccount(Map<String, String> accountData) throws Exception {
        try {
            String accountName = accountData.get("accountName");

            Optional<Account> currentAccountOpt = accountRepository.findByAccountName(accountName);
            if (!currentAccountOpt.isPresent()) {
                throw new Exception("Account not found.");
            }
            Account currentAccount = currentAccountOpt.get();
        
            // Log the before image for event log
            String beforeImage = currentAccount.toString();
        
            // Update fields with new values and perform validations
            if (accountData.containsKey("accountName")) {
                currentAccount.setAccountName(accountData.get("accountName"));
            }
            if (accountData.containsKey("accountDescription")) {
                currentAccount.setAccountDescription(accountData.get("accountDescription"));
            }
            if (accountData.containsKey("normalSide")) {
                currentAccount.setNormalSide(accountData.get("normalSide").charAt(0));
            }
            if (accountData.containsKey("accountCategory")) {
                currentAccount.setAccountCategory(accountData.get("accountCategory"));
            }
            if (accountData.containsKey("accountSubcategory")) {
                currentAccount.setAccountSubcategory(accountData.get("accountSubcategory"));
            }
            if (accountData.containsKey("orderField")) {
                currentAccount.setOrderField(Integer.parseInt(accountData.get("orderField")));
            }
            if (accountData.containsKey("statement")) {
                currentAccount.setStatement(accountData.get("statement"));
            }
            if (accountData.containsKey("comment")) {
                currentAccount.setComment(accountData.get("comment"));
            }
        
            Account updatedAccount = accountRepository.save(currentAccount);
        
            // Log event with before and after images
            String makerId = accountData.get("makerId");
            Log logEntry = new Log("EDIT", beforeImage, updatedAccount.toString(), makerId, LocalDateTime.now());
            logRepository.save(logEntry);
        
            return ResponseEntity.ok(updatedAccount);
        }
        catch (Exception e) {
            throw new Exception("Error editing account" + e.getMessage());
        }
    }

    // Retrieve individual account
    public ResponseEntity<?> viewAccount(Map<String, String> accessorData) {
        try {
            String accountName = accessorData.get("accountName");
            
            Optional<Account> accountOpt = accountRepository.findByAccountName(accountName);
            if (accountOpt.isPresent()) {
                return ResponseEntity.ok(accountOpt.get());
            }
            else {
                return ResponseEntity.badRequest().body("Account not found.");
            }
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error retrieving account" + e.getMessage()));
        }
    }

    // Deactivate account
    public ResponseEntity<?> deactivateAccount(Map<String, String> accountData) throws Exception {
        try {
            String accountName = accountData.get("accountName");

            Optional<Account> accountOpt = accountRepository.findByAccountName(accountName);
            // Prevent deactivation if account cannot be found
            if (!accountOpt.isPresent()) {
                return ResponseEntity.badRequest().body(Map.of("error","Account not found."));
            }
            Account account = accountOpt.get();
        
            // Prevent deactivation if account balance is greater than zero
            if (account.getBalance() > 0) {
                return ResponseEntity.badRequest().body(Map.of("error","Cannot deactivate account with balance greater than zero."));
            }
        
            // Set bool active to false to deactivate account
            account.setActive(false);
            Account updatedAccount = accountRepository.save(account);
        
            // Log deactivation
            String makerId = accountData.get("makerId");
            Log logEntry = new Log("DEACTIVATE", account.toString(), updatedAccount.toString(), makerId, LocalDateTime.now());
            logRepository.save(logEntry);

            return ResponseEntity.ok(updatedAccount);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error","Error Deactivating account" + e.getMessage()));
        }
    }

    // Set active status
    public ResponseEntity<?> setActive(Map<String, String> accountData) throws Exception {
        try {
            String accountName = accountData.get("accountName");

            Optional<Account> accountOpt = accountRepository.findByAccountName(accountName);
            // Throw error if account cannot be found
            if (!accountOpt.isPresent()) {
                throw new Exception("Account not found.");
            }
            Account account = accountOpt.get();
        
            // Set bool active to either true or false
            boolean active = Boolean.parseBoolean(accountData.get("active"));
            account.setActive(active);
            Account updatedAccount = accountRepository.save(account);
        
            // Log active status change
            String makerId = accountData.get("makerId");
            Log logEntry = new Log("SET_ACTIVE", "", updatedAccount.toString(), makerId, LocalDateTime.now());
            logRepository.save(logEntry);

            return ResponseEntity.ok(updatedAccount);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error","Error setting active status" + e.getMessage()));
        }
    }
    
    // Retrieve the ledger for a specific account

    public ResponseEntity<?> getLedger(Map<String, String> accountData) {
        try {
            String accountName = accountData.get("accountName");
            if (accountName == null || accountName.isEmpty()) {
                return ResponseEntity.badRequest().body("Account name is required.");
            }

            List<JournalEntry> lines = journalLineRepository.findByLineAccountName(accountName);

            if (lines.isEmpty()) {
                return ResponseEntity.ok("No ledger entries found for account: " + accountName);
            }

            return ResponseEntity.ok(lines);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Error retrieving ledger: " + e.getMessage()));
        }
    }

    public Object getAllAccounts() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllAccounts'");
    }
}
