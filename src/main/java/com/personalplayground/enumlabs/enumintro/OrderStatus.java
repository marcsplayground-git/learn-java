package com.personalplayground.enumlabs.enumintro;

public enum OrderStatus {
    NEW,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED;

    public boolean isFinalState() {
        return this == DELIVERED || this == CANCELLED;
    }
}
