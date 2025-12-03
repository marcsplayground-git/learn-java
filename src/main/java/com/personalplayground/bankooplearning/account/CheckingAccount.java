package com.personalplayground.bankooplearning.account;

import com.personalplayground.bankooplearning.strategy.InterestStrategy;
import com.personalplayground.bankooplearning.notification.NotificationChannel;

public class CheckingAccount extends BankAccount{

    private InterestStrategy feeStrategy;

    public CheckingAccount(String id, String ownerName, double initialBalance, InterestStrategy feeStrategy, NotificationChannel channel) {
        super(id, ownerName, initialBalance, channel); // constructor chaining to BankAccount
        this.feeStrategy = feeStrategy;
    }

    @Override
    public void applyMonthlyUpdate() {
        balance = feeStrategy.calculateNewBalance(balance);
    }
}
