package com.personalplayground.bankooplearning.notification;

public class EmailNotificationChannel implements NotificationChannel {

    private final String emailAddress;

    public EmailNotificationChannel(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public void send(String message) {
        System.out.println("[EMAIL to " + emailAddress + "] " + message);
    }
}
