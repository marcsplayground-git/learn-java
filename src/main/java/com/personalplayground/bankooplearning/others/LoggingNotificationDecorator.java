package com.personalplayground.bankooplearning.others;

import com.personalplayground.bankooplearning.notification.NotificationChannel;

public class LoggingNotificationDecorator implements NotificationChannel {

    private final NotificationChannel inner;

    public LoggingNotificationDecorator(NotificationChannel inner) {
        this.inner = inner;
    }

    @Override
    public void send(String message) {
        System.out.println("[LOG] Sending notification: " + message);
        inner.send(message); // forward the call (composition)
    }
}
