package com.ymh;

import java.util.concurrent.locks.ReentrantLock;

public class Main {

    private static ReentrantLock lock = new ReentrantLock(true);
    public static void main(String[] args) {
	// write your code here
        final BankAccount bankAccount = new BankAccount(1000d,"123456");
//        Person first = new Person(bankAccount,true);
//        Person second = new Person(bankAccount,false);
//
//        new Thread(first).start();
//        new Thread(second).start();

        Thread firstPerson = new Thread(new Runnable() {
            @Override
            public void run() {
//                bankAccount.deposit(300.00);
//                bankAccount.withdraw(50.00);
                System.out.println("First person depositing 300");
                bankAccount.deposit(300);
                System.out.println("bank account balance after first person deposit: " + bankAccount.getBalance());
                System.out.println("First person withdrawing 50");
                bankAccount.withdraw(50);
                System.out.println("bank account balance after first person withdraw: " + bankAccount.getBalance());
                System.out.println("Transaction completed for account " + bankAccount.getAccountNumber());
            }
        });

        Thread secondPerson = new Thread(new Runnable() {
            @Override
            public void run() {
//                bankAccount.deposit(203.75);
//                bankAccount.withdraw(100.00);
                System.out.println("Second person depositing 203.75");
                bankAccount.deposit(203.75);
                System.out.println("bank account balance after second person deposit: " + bankAccount.getBalance());
                System.out.println("Second person withdrawing 100");
                bankAccount.withdraw(100);
                System.out.println("bank account balance after second person withdrawing: " + bankAccount.getBalance());
                System.out.println("Transaction completed for account " + bankAccount.getAccountNumber());
            }
        });

        firstPerson.start();
        secondPerson.start();


    }

    public static class Person implements Runnable{
        private BankAccount bankAccount;
        private boolean isFirstPerson;

        public Person(BankAccount bankAccount, boolean isFirstPerson) {
            this.bankAccount = bankAccount;
            this.isFirstPerson = isFirstPerson;
        }

        @Override
        public void run() {
            //lock.lock();
            synchronized (bankAccount) {
                if (isFirstPerson) {
                    System.out.println("First person depositing 300");
                    bankAccount.deposit(300);
                    System.out.println("bank account balance after first person deposit: " + bankAccount.getBalance());
                    System.out.println("First person withdrawing 50");
                    bankAccount.withdraw(50);
                    System.out.println("bank account balance after first person withdraw: " + bankAccount.getBalance());
                } else {
                    System.out.println("Second person depositing 203.75");
                    bankAccount.deposit(203.75);
                    System.out.println("bank account balance after second person deposit: " + bankAccount.getBalance());
                    System.out.println("Second person withdrawing 100");
                    bankAccount.withdraw(100);
                    System.out.println("bank account balance after second person withdrawing: " + bankAccount.getBalance());
                }
            }
            //lock.unlock();
        }
    }
}
