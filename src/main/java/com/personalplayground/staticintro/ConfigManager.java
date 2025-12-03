package com.personalplayground.staticintro;

public final class ConfigManager {

    // the single instance
    private static final ConfigManager INSTANCE = new ConfigManager();

    // private constructor prevents creation
    private ConfigManager() {
        System.out.println("ConfigManager initialized.");
    }

    // getter for the instance
    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    // example method
    public String getConfigValue() {
        return "Demo-Config-Value";
    }
}
