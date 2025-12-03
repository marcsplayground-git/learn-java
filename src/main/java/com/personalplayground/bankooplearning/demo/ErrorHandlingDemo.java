package com.personalplayground.bankooplearning.demo;

import com.personalplayground.bankooplearning.exception.InsufficientFundsException;
import com.personalplayground.bankooplearning.exception.InvalidAmountException;
import com.personalplayground.bankooplearning.account.BankAccount;
import com.personalplayground.bankooplearning.factory.AccountFactory;

public class ErrorHandlingDemo {

    public static void main(String[] args) {

        BankAccount account = AccountFactory.createBasicSavings("ERR-1", "Alice");

        try {
            System.out.println("Initial balance: " + account.getBalance());

            System.out.println("\nDepositing 100...");
            account.deposit(100);
            System.out.println("Balance after deposit: " + account.getBalance());

            System.out.println("\nTrying to deposit -50 (invalid)...");
            account.deposit(-50); // should trigger InvalidAmountException

        } catch (InvalidAmountException ex) {
            System.out.println("[InvalidAmountException caught] " + ex.getMessage());
        }

        try {
            System.out.println("\nTrying to withdraw 1000 (more than balance)...");
            account.withdraw(1000); // should trigger InsufficientFundsException

        } catch (InsufficientFundsException ex) {
            System.out.println("[InsufficientFundsException caught] " + ex.getMessage());
        }

        System.out.println("\nFinal balance: " + account.getBalance());
    }
}
