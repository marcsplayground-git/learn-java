package com.personalplayground.bankooplearning.demo;

import com.personalplayground.bankooplearning.notification.EmailNotificationChannel;
import com.personalplayground.bankooplearning.strategy.FixedRateInterestStrategy;
import com.personalplayground.bankooplearning.notification.NotificationChannel;
import com.personalplayground.bankooplearning.account.BankAccount;
import com.personalplayground.bankooplearning.account.PremiumSavingsAccount;
import com.personalplayground.bankooplearning.account.SavingsAccount;
import com.personalplayground.bankooplearning.strategy.InterestStrategy;
import com.personalplayground.bankooplearning.strategy.TieredInterestStrategy;

import java.util.ArrayList;
import java.util.List;

public class OcpDemo {

    public static void main(String[] args) {

        // Strategies
        InterestStrategy tiered = new TieredInterestStrategy();
        InterestStrategy fixed  = new FixedRateInterestStrategy(0.01);

        // Notification channels
        NotificationChannel email = new EmailNotificationChannel("ocp@example.com");

        // Accounts
        BankAccount basicSavings = new SavingsAccount(
                "OCP-1",
                "Alice",
                2000,
                fixed,
                email
        );

        BankAccount premiumSavings = new PremiumSavingsAccount(
                "OCP-2",
                "Bob",
                6000,
                tiered,
                email
        );

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(basicSavings);
        accounts.add(premiumSavings);

        for (BankAccount acc : accounts) {
            acc.deposit(100);
            acc.applyMonthlyUpdate();
            System.out.println(
                    acc.getClass().getSimpleName() +
                            " | Balance: " + acc.getBalance()
            );
        }
    }
}
