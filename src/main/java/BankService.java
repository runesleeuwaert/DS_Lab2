import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final ConcurrentHashMap<Long, BankAccount> accounts = new ConcurrentHashMap<>();

    public void createAccount(long accountId, long initialBalance) {
        accounts.putIfAbsent(accountId, new BankAccount(accountId, initialBalance));
    }

    public Long getBalance(long accountId) {
        BankAccount account = accounts.get(accountId);
        return (account != null) ? account.getBalance() : null;
    }

    public boolean deposit(long accountId, long amount) {
        BankAccount account = accounts.get(accountId);
        if (account != null) {
            account.deposit(amount);
            return true;
        }
        return false;
    }

    public String withdraw(long accountId, long amount) {
        BankAccount account = accounts.get(accountId);
        if (account == null) return "Account not found";
        return account.withdraw(amount) ? "Withdrawn " + amount : "Insufficient funds";
    }
}
