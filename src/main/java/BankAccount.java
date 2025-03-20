import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
    private final long accountId;
    private AtomicLong balance;

    public BankAccount(long accountId, long initialBalance) {
        this.accountId = accountId;
        this.balance = new AtomicLong(initialBalance);
    }

    public long getAccountId() {
        return accountId;
    }

    public long getBalance() {
        return balance.get();
    }

    public void deposit(long amount) {
        balance.addAndGet(amount);
    }

    public boolean withdraw(long amount) {
        long currentBalance = balance.get();
        if (currentBalance >= amount) {
            balance.addAndGet(-amount);
            return true;
        }
        return false;
    }
}
