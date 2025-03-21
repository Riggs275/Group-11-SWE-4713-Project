import java.util.List;
import java.util.Optional;

public class AccountService {
    
    // Add new account
    public Account addAccount(Account account, String makerId) throws Exception {
        // Do not allow duplicate account names or numbers
        if (accountRepository.findByAccountName(account.getAccountName()).isPresent() ||
            accountRepository.findByAccountNumber(account.getAccountNumber()).isPresent()) {
            throw new Exception("Duplicate account name or account number.");
        }
        
        // Validate account number format (no decimals or alphanumeric characters) and other fields
        if (!accountNumber.matches("^[0-9]+$")) {
            throw new Exception("Invalid account number format. Only numeric digits are allowed.");
        }
        
        // Save account to repository if name and number are valid
        Account savedAccount = accountRepository.save(account);
        
        return savedAccount;
    }

    // Retrieve individual account
    public Optional<Account> getAccount(Long accountId) {
        return accountRepository.findById(accountId);
    }

    // Retrieve all accounts (chart of accounts)
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Edit account details
    public Account editAccount(Account account, String makerId) throws Exception {
        Optional<Account> currentAccountOpt = accountRepository.findById(account.getId());
        if (!currentAccountOpt.isPresent()) {
            throw new Exception("Account not found.");
        }
        Account currentAccount = currentAccountOpt.get();
        
        // Log the before image for event log
        
        // Update fields with new values and perform validations
        
        Account updatedAccount = accountRepository.save(account);
        
        // Log event with before and after images
        
        return updatedAccount;
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