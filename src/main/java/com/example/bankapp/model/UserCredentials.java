package com.example.bankapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class UserCredentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;

    @Column(unique = true)
    private String username;

    private String password;

    private int accNo;
}