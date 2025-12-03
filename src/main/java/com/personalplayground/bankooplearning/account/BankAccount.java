package com.personalplayground.bankooplearning.account;

import com.personalplayground.bankooplearning.notification.NotificationChannel;
import com.personalplayground.bankooplearning.util.ValidationUtils;

public abstract class BankAccount {

    // ENCAPSULATION: fields are private
    private final String id;
    private final String ownerName;
    protected double balance; // protected: visible to subclasses
    private NotificationChannel notificationChannel;

    // Constructor = how to create an instance (object) of BankAccount
    public BankAccount(String id, String ownerName, double initialBalance, NotificationChannel notificationChannel) {
        this.id = id;
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.notificationChannel = notificationChannel;
    }

    // Behavior (methods)

    public void setNotificationChannel(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    public void deposit(double amount) {
        ValidationUtils.requirePositiveAmount(amount);
        balance += amount;
        notify("Deposited " + amount + ", new balance = " + balance);
    }

    public void withdraw(double amount) {
        ValidationUtils.requirePositiveAmount(amount);
        ValidationUtils.requireSufficientFunds(balance, amount);
        balance -= amount;
        notify("Withdrew " + amount + ", new balance = " + balance);
    }

    protected void notify(String msg) {
        if (notificationChannel != null) {
            notificationChannel.send("[" + id + "] " + msg);
        }
    }

    public double getBalance() {
        return balance; // Note: No public setBalance()
    }

    public String getId() {
        return id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    // ABSTRACTION: each account type will implement this differently
    public abstract void applyMonthlyUpdate();
}
