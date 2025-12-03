package com.personalplayground.staticmasteryproject.staticintro;

public class StaticPatternsDemo {

    public static void main(String[] args) {

        System.out.println("=== Static Patterns Demo ===\n");

        // Utility class
        System.out.println("Capitalized: " + StringUtils.capitalize("plane"));
        System.out.println("Repeated: " + StringUtils.repeat("=", 5));

        // Constant holder
        System.out.println("API Version: " + AppConstants.API_VERSION);
        System.out.println("Company: " + AppConstants.COMPANY_NAME);

        // Static factory
        Plane p1 = PlaneFactory.createStandardPlane();
        Plane p2 = PlaneFactory.createSmallPlane();

        // Singleton
        ConfigManager cfg = ConfigManager.getInstance();
        System.out.println("Config value: " + cfg.getConfigValue());
    }
}
