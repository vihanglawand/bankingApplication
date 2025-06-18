package com.example.bankapp.controller;

import com.example.bankapp.model.AccountDetails;
import com.example.bankapp.model.UserCredentials;
import com.example.bankapp.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankService service;

    private int loggedAccNo = -1;

    @PostMapping("/signup")
    public String signUp(@RequestBody UserCredentials user) {
        return service.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredentials user) {
        Optional<UserCredentials> result = service.login(user.getUsername(), user.getPassword());
        if (result.isPresent()) {
            loggedAccNo = result.get().getAccNo();
            return "Login successful. Account No: " + loggedAccNo;
        } else {
            return "Invalid username or password.";
        }
    }

    @GetMapping("/balance")
    public String getBalance() {
        return service.checkBalance(loggedAccNo)
                .map(bal -> "Balance: ₹" + bal)
                .orElse("Account not found.");
    }

    @PostMapping("/deposit")
    public String deposit(@RequestParam double amount) {
        return service.deposit(loggedAccNo, amount) ? "Deposit successful." : "Failed to deposit.";
    }

    @PostMapping("/withdraw")
    public String withdraw(@RequestParam double amount) {
        return service.withdraw(loggedAccNo, amount) ? "Withdrawal successful." : "Insufficient balance or account not found.";
    }

    @GetMapping("/account")
    public Optional<AccountDetails> getAccountDetails() {
        return service.getAccountDetails(loggedAccNo);
    }
}
