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
            String accountNumber = accountData.get("accountNumber");
            String accountDescription = accountData.get("accountDescription");
            String normalSide = accountData.get("normalSide");
            String accountCategory = accountData.get("accountCategory");
            String accountSubcategory = accountData.get("accountSubcategory");
            double initialBalance = new double(accountData.get("initialBalance"));
            double debit = new double(accountData.get("debit"));
            double credit = new double(accountData.get("credit"));
            double balance = new double(accountData.get("balance"));
            String orderField = accountData.get("order");
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
                currentAccount.setAccountDescription(accountData.get("normalSide"));
            }
            // ... Add the rest that will be updatable ...
        
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
    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }

    // Retrieve all accounts (chart of accounts)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Deactivate account
    public Account deactivateAccount(Long accountId, String makerId) throws Exception {
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
        
        return updatedAccount;
    }

    // Search for accounts by name or number
    public List<Account> searchAccounts(String query) {
        // Implement search logic based on accountName and accountNumber
        return null; // null placeholder
    }
}
