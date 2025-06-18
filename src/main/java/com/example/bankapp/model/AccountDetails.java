package com.example.bankapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class AccountDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accNo;

    private String name;

    private double balance;
}
