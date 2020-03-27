package com.company;

public class BankAccount {
    private int accountNumber;
    private double balance;
    private String name;
    private int phone;

    public void deposit(int amount) {
        if (amount > 0){
            this.balance += amount;
            System.out.println("Deposit succeed");
            System.out.println("Balance = " + this.balance);
            return;
        }
        System.out.println("Deposit fail");
    }

    public void Withdraw(int amount){
        if(this.balance>0){
            this.balance -= amount;
            System.out.println("Withdraw succeed");
            return;
        }
        System.out.println("Withdraw fail");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
