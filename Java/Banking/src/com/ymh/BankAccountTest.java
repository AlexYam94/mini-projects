package com.ymh;

import org.junit.runners.JUnit4;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class BankAccountTest {
    private BankAccount bankAccount;

    @org.junit.BeforeClass
    public static void BeforeClass(){
        System.out.println("This executes before any test cases");
    }


    @org.junit.Before
    public void setup(){
        bankAccount = new BankAccount("Alex","Yam",1000.00,BankAccount.CHECKING);
    }


    @org.junit.Test
    public void deposit() {
        double balance = bankAccount.deposit(200.00,true);
        assertEquals(1200.00,balance,0);
    }


    @org.junit.Test
    public void withdraw_branch() throws Exception{
        double balance = bankAccount.withdraw(600.00,true);
        assertEquals(400.00,balance,0);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void withdraw_notBranch() throws Exception{
        double balance = bankAccount.withdraw(600.00,false);
        assertEquals(400.00,balance,0);
    }

    @org.junit.Test
    public void getBalance_deposit() {
        double balance = bankAccount.deposit(200.00,true);
        assertEquals(1200.00,bankAccount.getBalance(),0);
    }

    @org.junit.Test
    public void getBalance_withdraw() {
        double balance = bankAccount.withdraw(200.00,true);
        assertEquals(800.00,bankAccount.getBalance(),0);
    }
    @org.junit.Test
    public void isChecking_true(){
        BankAccount bankAccount = new BankAccount("Alex","Yam",1000.00,BankAccount.CHECKING);
        assertTrue(bankAccount.isChecking());
    }

    @org.junit.AfterClass
    public static void afterClass(){
        System.out.println("This execute after any test cases");
    }
}