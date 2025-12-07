package com.personalplayground.generics.basics;

public class BoxDemo {

    public static void run() {
        System.out.println("=== BoxDemo: Generic class basics ===");
        System.out.println();

        basicBoxExamples();
        System.out.println();
        compareWithObjectBox();
    }

    /**
     * Shows how a GENERIC Box<T> works with different type arguments.
     */
    private static void basicBoxExamples() {
        System.out.println("--- 1) Generic Box<T> in action ---");

        // TYPE ARGUMENT = String, so this is a PARAMETERIZED TYPE: Box<String>
        Box<String> stringBox = new Box<>();
        stringBox.set("Hello, generics!");
        String message = stringBox.get();   // No cast needed
        System.out.println("Box<String> contains: " + message);

        // TYPE ARGUMENT = Integer, parameterized type: Box<Integer>
        Box<Integer> integerBox = new Box<>(42);
        Integer number = integerBox.get();  // No cast needed
        System.out.println("Box<Integer> contains: " + number);

        System.out.println();
        System.out.println("Notice:");
        System.out.println("- Box is defined as Box<T> (T is the TYPE PARAMETER).");
        System.out.println("- Box<String> and Box<Integer> are PARAMETERIZED TYPES.");
        System.out.println("- The compiler knows get() returns String or Integer, so no cast is required.");
        System.out.println();

        System.out.println("Try this later: in your IDE, uncomment a line like:");
        System.out.println("// stringBox.set(123);");
        System.out.println("You will get a COMPILE-TIME error, proving generics give type safety.");
    }

    /**
     * Shows the difference between generic Box<T> and old-style ObjectBox.
     */
    private static void compareWithObjectBox() {
        System.out.println("--- 2) Comparing with non-generic ObjectBox ---");

        ObjectBox objectBox = new ObjectBox();
        objectBox.set("I am a String inside ObjectBox");
        System.out.println("ObjectBox currently holds: " + objectBox.get());

        // This cast is OK (the runtime value is actually a String)
        String text = (String) objectBox.get();
        System.out.println("After casting to String: " + text);

        System.out.println();
        System.out.println("Now let's create a subtle bug:");

        objectBox.set(123);  // We put an Integer in (compiler allows it)
        System.out.println("ObjectBox now holds: " + objectBox.get());

        try {
            // This compiles, but will THROW at runtime
            String wrongCast = (String) objectBox.get();
            System.out.println("This will never print: " + wrongCast);
        } catch (ClassCastException e) {
            System.out.println("Runtime problem: tried to cast an Integer to String!");
            System.out.println("Exception message: " + e);
        }

        System.out.println();
        System.out.println("Key lesson:");
        System.out.println("- ObjectBox uses Object, so ANYTHING can be stored.");
        System.out.println("- You must cast back, and casts can fail at RUNTIME (ClassCastException).");
        System.out.println("- With Box<String>, the compiler stops you from putting an Integer in,");
        System.out.println("  so the error is caught at COMPILE-TIME instead of in production.");
    }
}
