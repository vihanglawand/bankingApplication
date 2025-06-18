package com.example.bankapp.service;

import com.example.bankapp.model.AccountDetails;
import com.example.bankapp.model.UserCredentials;
import com.example.bankapp.repository.AccountRepository;
import com.example.bankapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BankService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private UserRepository userRepo;

    public String signup(UserCredentials user) {
        AccountDetails account = new AccountDetails();
        account.setName(user.getUsername());
        account.setBalance(0.0);
        AccountDetails saved = accountRepo.save(account);

        user.setAccNo(saved.getAccNo());
        userRepo.save(user);

        return "Account created with Account No: " + saved.getAccNo();
    }

    public Optional<UserCredentials> login(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    public Optional<Double> checkBalance(int accNo) {
        return accountRepo.findById(accNo).map(AccountDetails::getBalance);
    }

    public boolean deposit(int accNo, double amount) {
        Optional<AccountDetails> opt = accountRepo.findById(accNo);
        if (opt.isPresent()) {
            AccountDetails acc = opt.get();
            acc.setBalance(acc.getBalance() + amount);
            accountRepo.save(acc);
            return true;
        }
        return false;
    }

    public boolean withdraw(int accNo, double amount) {
        Optional<AccountDetails> opt = accountRepo.findById(accNo);
        if (opt.isPresent()) {
            AccountDetails acc = opt.get();
            if (acc.getBalance() >= amount) {
                acc.setBalance(acc.getBalance() - amount);
                accountRepo.save(acc);
                return true;
            }
        }
        return false;
    }

    public Optional<AccountDetails> getAccountDetails(int accNo) {
        return accountRepo.findById(accNo);
    }
}
