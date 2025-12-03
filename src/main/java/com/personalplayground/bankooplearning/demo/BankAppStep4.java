package com.personalplayground.bankooplearning.demo;

import com.personalplayground.bankooplearning.account.BankAccount;
import com.personalplayground.bankooplearning.account.CheckingAccount;
import com.personalplayground.bankooplearning.account.SavingsAccount;
import com.personalplayground.bankooplearning.notification.EmailNotificationChannel;
import com.personalplayground.bankooplearning.notification.NotificationChannel;
import com.personalplayground.bankooplearning.notification.SmsNotificationChannel;
import com.personalplayground.bankooplearning.others.LoggingNotificationDecorator;
import com.personalplayground.bankooplearning.strategy.FixedRateInterestStrategy;
import com.personalplayground.bankooplearning.strategy.InterestStrategy;
import com.personalplayground.bankooplearning.strategy.MonthlyFeeStrategy;

import java.util.ArrayList;
import java.util.List;

public class BankAppStep4 {

    public static void main(String[] args) {

        NotificationChannel email = new EmailNotificationChannel("alice@example.com");
        NotificationChannel sms = new SmsNotificationChannel("+123456789");
        NotificationChannel loggedEmail = new LoggingNotificationDecorator(email);

        // Strategies
        InterestStrategy savingInterest = new FixedRateInterestStrategy(0.01);
        InterestStrategy checkingFee = new MonthlyFeeStrategy(10.0);

        // Accounts using strategies + notifications
        BankAccount checking = new CheckingAccount(
                "CHK-001",
                "Alice",
                1000.00,
                checkingFee,
                loggedEmail // Logging + eamil
        );
        BankAccount savings = new SavingsAccount(
                "SVG-001",
                "Bob",
                2000,
                savingInterest,
                sms // SMS notifications
        );

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(checking);
        accounts.add(savings);

        // Apply updates polymorphically
        for (BankAccount account : accounts) {
            account.deposit(100);
            account.applyMonthlyUpdate(); // Different strategy, same method
        }

        // Print results
        for (BankAccount account : accounts) {
            System.out.println(
                    account.getClass().getSimpleName() + " | " +
                    account.getId() + " | Owner: " + account.getOwnerName() +
                    " | Balance: " + account.getBalance()
            );
        }
    }
}
