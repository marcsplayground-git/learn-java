package com.personalplayground.bankooplearning.factory;

import com.personalplayground.bankooplearning.account.BankAccount;
import com.personalplayground.bankooplearning.account.CheckingAccount;
import com.personalplayground.bankooplearning.account.SavingsAccount;
import com.personalplayground.bankooplearning.notification.EmailNotificationChannel;
import com.personalplayground.bankooplearning.notification.SmsNotificationChannel;
import com.personalplayground.bankooplearning.strategy.FixedRateInterestStrategy;
import com.personalplayground.bankooplearning.strategy.MonthlyFeeStrategy;

public class AccountFactory {

    public static BankAccount createBasicSavings(String id, String owner) {
        return new SavingsAccount(
                id,
                owner,
                0,
                new FixedRateInterestStrategy(0.01),
                new EmailNotificationChannel(owner.toLowerCase() + "@example.com")
        );
    }

    public static BankAccount createBasicChecking(String id, String owner) {
        return new CheckingAccount(
                id,
                owner,
                0,
                new MonthlyFeeStrategy(10),
                new SmsNotificationChannel("+1000" + id)
        );
    }
}
