package com.personalplayground.bankooplearning.account;

import com.personalplayground.bankooplearning.strategy.InterestStrategy;
import com.personalplayground.bankooplearning.notification.NotificationChannel;

public class SavingsAccount extends BankAccount {

    private InterestStrategy interestStrategy; // Composition

    public SavingsAccount(String id, String ownerName, double initialBalance, InterestStrategy interestStrategy, NotificationChannel channel) {
        super(id, ownerName, initialBalance, channel);
        this.interestStrategy = interestStrategy;
    }

    public void setInterestStrategy(InterestStrategy interestStrategy) {
        this.interestStrategy = interestStrategy;
    }

    @Override
    public void applyMonthlyUpdate() {
        balance = interestStrategy.calculateNewBalance(balance);
    }
}
