package com.ymh;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

class BankAccount {
    private double balance;
    private String accountNumber;
    private ReentrantLock lock;

    public BankAccount(double balance, String accountNumber) {
        this.balance = balance;
        this.accountNumber = accountNumber;
        this.lock = new ReentrantLock();
    }

//    public void deposit(double amount){
////        synchronized (this) {
////            balance += amount;
////        }
////    }
////
////    public void withdraw(double amount){
////        synchronized (this) {
////            balance -= amount;
////        }
////    }

    public void deposit(double amount){
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance += amount;
                    status = true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Lock is bussy");
            }
        } catch (InterruptedException e){

        }
        System.out.println("Transaction status = "+ status);
    }


    public void withdraw(double amount){
        boolean status = false;
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    balance -= amount;
                    status=true;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("Lock is bussy");
            }
        } catch (InterruptedException e){

        }
        System.out.println("Transaction status = "+ status);
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void printAccountNumber(){
        System.out.println("Account number = " + this.getAccountNumber());
    }
}
