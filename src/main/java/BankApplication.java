import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.atomic.AtomicLong;

@SpringBootApplication
public class BankApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankApplication.class, args);
    }
}

@RestController
@RequestMapping("/bank")
class BankController {
    private final BankService bankService = new BankService();

    @GetMapping("/balance")
    public double getBalance() {
        return bankService.getBalance();
    }

    @PostMapping("/deposit/{amount}")
    public String deposit(@PathVariable double amount) {
        bankService.deposit(amount);
        return "Deposited " + amount;
    }

    @PostMapping("/withdraw/{amount}")
    public String withdraw(@PathVariable double amount) {
        return bankService.withdraw(amount);
    }
}

class BankService {
    private final AtomicLong balance = new AtomicLong(1000);

    public double getBalance() {
        return balance.get();
    }

    public void deposit(double amount) {
        balance.addAndGet((long) amount);
    }

    public String withdraw(double amount) {
        long currentBalance = balance.get();
        if (currentBalance >= amount) {
            balance.addAndGet((long) -amount);
            return "Withdrawn " + amount;
        } else {
            return "Insufficient funds";
        }
    }
}
