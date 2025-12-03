package com.personalplayground.bankooplearning.demo;

import com.personalplayground.bankooplearning.account.BankAccount;
import com.personalplayground.bankooplearning.factory.AccountFactory;

public class DipDemo {

    public static void main(String[] args) {

        BankAccount a1 = AccountFactory.createBasicSavings("DIP-1", "Alice");
        BankAccount a2 = AccountFactory.createBasicChecking("DIP-2", "Bob");

        a1.deposit(300);
        a2.deposit(200);

        a1.applyMonthlyUpdate();
        a2.applyMonthlyUpdate();

        System.out.println(a1.getBalance());
        System.out.println(a2.getBalance());
    }
}
