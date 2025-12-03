package com.personalplayground.enumlabs.enumintro;

public enum UserRole {

    GUEST(false),
    USER(true),
    ADMIN(true);

    private final boolean canAccessDashboard;

    UserRole(boolean canAccessDashboard) {
        this.canAccessDashboard = canAccessDashboard;
    }

    public boolean canAccessDashboard() {
        return canAccessDashboard;
    }
}
