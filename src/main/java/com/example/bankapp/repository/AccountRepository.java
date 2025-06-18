package com.example.bankapp.repository;

import com.example.bankapp.model.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<AccountDetails, Integer> {
}
