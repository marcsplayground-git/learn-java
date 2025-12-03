package com.personalplayground.bankooplearning.notification;

public class SmsNotificationChannel implements NotificationChannel {

    private final String phoneNumber;

    public SmsNotificationChannel(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void send(String message) {
        System.out.println("[SMS to " + phoneNumber + "] " + message);
    }
}
