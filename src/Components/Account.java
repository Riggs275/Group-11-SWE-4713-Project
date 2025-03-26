import java.time.LocalDateTime;

public class Account {
    
    // Account variables
    private String accountName; // Account name the user can identitify
    private int accountNumber; // Must have correct starting values
    private String accountDescription;
    private Char normalSide;
    private String accountCategory; // e.g., Assets, Liabilities, Equity, Income, Expenses, etc.
    private String accountSubcategory; // Smaller categories within account categories
    private double initialBalance;
    private double debit;
    private double credit;
    private double balance;
    private LocalDateTime dateTimeAccountAdded;
    private String userId; // ID of the user who created the account
    private int orderField; // e.g., cash can be 01
    private String statement; // e.g., IS (income statement), BS (balance sheet), RE (Retained Earnings statement)
    private String comment;
    private boolean active = true;

    // Getters and Setters
    public String getAccountName() {
        return accountName;
    };
    public void setAccountName(String newName) {
        accountName = newName;
    };
    public int getAccountNumber() {
        return accountNumber;
    };
    public void setAccountNumber(String newNum) {
        accountNumber = newNum;
    };
    public String getAccountDescription() {
        return accountDescription;
    };
    public void setAccountDescription(String newDes) {
        accountDescription = newDes;
    };
    public char getNormalSide() {
        return normalSide;
    };
    public void setNormalSide(String newNorm) {
        normalSide = newNorm;
    };
    public String getAccountCategory() {
        return accountCategory;
    };
    public void setAccountCategory(String newCategory) {
        accountCategory = newCategory;
    };
    public String getAccountSubcategory() {
        return accountSubcategory;
    };
    public void setAccountSubcategory(String newSubcategory) {
        accountSubcategory = newSubcategory;
    };
    public double getInitialBalance() {
        return initialBalance;
    };
    public void setInitialBalance(BigDecimal initial) {
        initialBalance = initial;
    };
    public double getDebit() {
        return debit;
    };
    public void setDebit(BigDecimal newDebit) {
        debit = newDebit;
    };
    public double getCredit() {
        return credit;
    };
    public void setCredit(BigDecimal newCredit) {
        credit = newCredit;
    };
    public double getBalance() {
        return balance;
    };
    public void setBalance(BigDecimal newBalance) {
        balance = newBalance;
    };
    public LocalDateTime getDateTimeAccountAdded() {
        return dateTimeAccountAdded;
    };
    public void setDateTimeAccountAdded() {
        dateTimeAccountAdded = LocalDateTime.now();
    };
    public String getUserId() {
        return userId;
    };
    public void setUserId(String newUserId) {
        userId = newUserId
    };
    public int getOrderField() {
        return orderField;
    };
    public void setOrderField(String newOrderField) {
        orderField = newOrderField;
    };
    public String getStatement() {
        return statement;
    };
    public void setStatement(String newStatement) {
        statement = newStatement;
    };
    public String getComment() {
        return comment;
    };
    public void setComment(String newComment) {
        comment = newComment;
    };
    public boolean isActive() {
        return isActive;
    };
    public void setActive(boolean active) {
        isActive = active;
    };
}
