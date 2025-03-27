package com.accountingAPI.accountingSoftware.components;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private LogRepository logRepository;

    // Add new account
    public ResponseEntity<?> addAccount(Map<String, String> accountData) throws Exception {
        try {
            String accountName = accountData.get("accountName");
            int accountNumber = accountData.get("accountNumber");
            String accountDescription = accountData.get("accountDescription");
            Char normalSide = accountData.get("normalSide");
            String accountCategory = accountData.get("accountCategory");
            String accountSubcategory = accountData.get("accountSubcategory");
            double initialBalance = new double(accountData.get("initialBalance"));
            double debit = new double(accountData.get("debit"));
            double credit = new double(accountData.get("credit"));
            double balance = new double(accountData.get("balance"));
            int orderField = accountData.get("order");
            String statement = accountData.get("statement");
            String comment = accountData.get("comment");
            String userId = accountData.get("userId");
        
            // Do not allow duplicate account names or numbers
            if (accountRepository.findByAccountName(accountName).isPresent() || accountRepository.findByAccountNumber(accountNumber).isPresent()) {
                throw new Exception("Duplicate account name or account number.");
            }
        
            // Validate account number format (no decimals or alphanumeric characters) and other fields
            if (!accountNumber.matches("^[0-9]+$")) {
                throw new Exception("Invalid account number format. Only numeric digits are allowed.");
            }
        
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
            account.setDateTimeAccountAdded(LocalDateTime.now());
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
            Optional<Account> currentAccountOpt = accountRepository.findById(account.getId());
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
                currentAccount.setNormalSide(accountData.get("normalSide"));
            }
            if (accountData.containsKey("accountCategory")) {
                currentAccount.setAccountCategory(accountData.get("accountCategory"));
            }
            if (accountData.containsKey("accountSubcategory")) {
                currentAccount.setAccountSubcategory(accountData.get("accountSubcategory"));
            }
            if (accountData.containsKey("orderField")) {
                currentAccount.setOrderField(accountData.get("orderField"));
            }
            if (accountData.containsKey("statement")) {
                currentAccount.setStatement(accountData.get("statement"));
            }
            if (accountData.containsKey("comment")) {
                currentAccount.setComment(accountData.get("comment"));
            }
        
            Account updatedAccount = accountRepository.save(account);
        
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
            Long accountId = Long.parseLong(accessorData.get("id"));
            Optional<Account> accountOpt = accountRepository.findById(accountId);
            if (accountOpt.isPresent()) {
                return ResponseEntity.ok(accountOpt.get());
            }
            else {
                return ResponseEntity.badRequest().body("Account not found.");
            }
        }
        catch (Exception e) {
            throw new Exception("Error retrieving account" + e.getMessage());
        }
    }

    // Deactivate account
    public ResponseEntity<?> deactivateAccount(Map<String, String> accountData) throws Exception {
        try {
            Optional<Account> accountOpt = accountRepository.findById(accountId);
            // Prevent deactivation if account cannot be found
            if (!accountOpt.isPresent()) {
                throw new Exception("Account not found.");
            }
            Account account = accountOpt.get();
        
            // Prevent deactivation if account balance is greater than zero
            if (account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                throw new Exception("Cannot deactivate account with balance greater than zero.");
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
            throw new Exception("Error Deactivating account" + e.getMessage());
        }
    }

    // Set active status
    public ResponseEntity<?> setActive(Map<String, String> accountData) throws Exception {
        try {
            Optional<Account> accountOpt = accountRepository.findById(accountId);
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
            throw new Exception("Error setting active status" + e.getMessage());
        }
    }
    
    // Retrieve the ledger for a specific account
    public ResponseEntity<?> getLedger(Map<String, String> accountData) {
        try {
            Long accountId = Long.parseLong(accountData.get("id"));
            Object ledger = accountRepository.getLedgerByAccountId(accountId); // Ledger query goes here
            if (ledger == null) {
                return ResponseEntity.badRequest().body("Ledger not found.");
            }
            return ResponseEntity.ok(ledger);
        }
        catch (Exception e) {
            throw new Exception("Error retrieving ledger" + e.getMessage());
        }
    }
/*
    // Retrieve all accounts (chart of accounts)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Search for accounts by name or number
    public List<Account> searchAccounts(String query) {
        // Implement search logic based on accountName and accountNumber
        return null; // null placeholder
    }
*/
}
