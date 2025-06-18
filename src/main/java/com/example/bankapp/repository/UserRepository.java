package com.example.bankapp.repository;

import com.example.bankapp.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCredentials, Integer> {
    Optional<UserCredentials> findByUsernameAndPassword(String username, String password);
}