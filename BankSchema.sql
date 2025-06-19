-- Create the database
CREATE DATABASE IF NOT EXISTS bank;
USE bank;

-- Create the account_details table first
CREATE TABLE account_details (
    acc_no INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    balance DOUBLE DEFAULT 0
);

-- Create the user_credentials table with foreign key to account_details
CREATE TABLE user_credentials (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    acc_no INT,
    FOREIGN KEY (acc_no) REFERENCES account_details(acc_no)
);
