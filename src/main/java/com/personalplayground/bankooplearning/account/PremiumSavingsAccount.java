package com.personalplayground.bankooplearning.account;

import com.personalplayground.bankooplearning.strategy.InterestStrategy;
import com.personalplayground.bankooplearning.notification.NotificationChannel;

public class PremiumSavingsAccount extends SavingsAccount {

    public PremiumSavingsAccount(String id, String ownerName, double initialBalance, InterestStrategy interestStrategy, NotificationChannel channel) {
        super(id, ownerName, initialBalance, interestStrategy, channel);
    }

    @Override
    public void applyMonthlyUpdate() {
        super.applyMonthlyUpdate();

        // Premium bonus: +$5 monthly
        balance += 5;
    }
}
