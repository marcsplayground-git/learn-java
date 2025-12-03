package com.personalplayground.staticintro.bad;

public class BadStaticInitializer {

    static {
        System.out.println("Loading huge data...");
        try {
            Thread.sleep(3000); // ❌ long-running logic
        } catch (InterruptedException e) {}
    }

    public static void doSomething() {
        System.out.println("Method executed");
    }
}
