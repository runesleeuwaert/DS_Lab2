import java.util.concurrent.atomic.AtomicLong;

public class BankAccount {
    private final long accountId;
    private final AtomicLong balance;

    public BankAccount(long accountId, long initialBalance) {
        this.accountId = accountId;
        this.balance = new AtomicLong(initialBalance);
    }

    public long getBalance() {
        return balance.get();
    }

    public void deposit(long amount) {
        balance.addAndGet(amount);
    }

    public boolean withdraw(long amount) {
        long currentBalance;
        do {
            currentBalance = balance.get();
            if (currentBalance < amount) {
                return false;
            }
        } while (!balance.compareAndSet(currentBalance, currentBalance - amount));
        return true;
    }
}
