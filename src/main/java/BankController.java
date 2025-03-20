import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    private final BankService bankService = new BankService();

    @GetMapping("/balance/{accountId}")
    public String getBalance(@PathVariable long accountId) {
        Long balance = bankService.getBalance(accountId);
        return (balance != null) ? "Balance: " + balance : "Account not found";
    }

    @PostMapping("/create/{accountId}/{initialBalance}")
    public String createAccount(@PathVariable long accountId, @PathVariable long initialBalance) {
        bankService.createAccount(accountId, initialBalance);
        return "Account created with balance " + initialBalance;
    }

    @PostMapping("/deposit/{accountId}/{amount}")
    public String deposit(@PathVariable long accountId, @PathVariable long amount) {
        boolean success = bankService.deposit(accountId, amount);
        return success ? "Deposited " + amount : "Account not found";
    }

    @PostMapping("/withdraw/{accountId}/{amount}")
    public String withdraw(@PathVariable long accountId, @PathVariable long amount) {
        return bankService.withdraw(accountId, amount);
    }
}
