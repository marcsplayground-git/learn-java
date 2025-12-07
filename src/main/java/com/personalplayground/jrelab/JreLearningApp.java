package com.personalplayground.jrelab;

import com.personalplayground.jrelab.collections.CollectionsDemo;
import com.personalplayground.jrelab.concurrent.ConcurrencyDemo;
import com.personalplayground.jrelab.environment.EnvironmentAndConfigDemo;
import com.personalplayground.jrelab.io.StreamsAndFilesDemo;
import com.personalplayground.jrelab.io.ZipFileSystemDemo;
import com.personalplayground.jrelab.logging.LoggingDemo;
import com.personalplayground.jrelab.reflection.ReflectionAndAnnotationsDemo;
import com.personalplayground.jrelab.serialization.SerializationDemo;
import com.personalplayground.jrelab.strings.StringsAndRegexDemo;

import java.util.Scanner;

public class JreLearningApp {

    public static void main(String[] args) {
        EnvironmentAndConfigDemo.setProgramArgs(args);
        JreLearningApp app = new JreLearningApp();
        app.run();
    }

    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                printMainMenu();
                System.out.print("Choose an option: ");

                String input = scanner.nextLine().trim();

                switch (input) {
                    case "1" -> showCollectionsDemo();
                    case "2" -> showStreamsAndFilesDemo();
                    case "3" -> showLoggingDemo();
                    case "4" -> showConcurrencyDemo();
                    case "5" -> showSerializationDemo();
                    case "6" -> showReflectionAndAnnotationsDemo();
                    case "7" -> showEnvironmentInfoDemo();
                    case "8" -> showStringsAndRegexDemo();
                    case "9" -> showZipFileSystemDemo();
                    case "0" -> {
                        System.out.println("Exiting JRE Learning Lab. Goodbye!");
                        running = false;
                    }
                    default -> System.out.println("Invalid choice. Please try again.");
                }

                System.out.println(); // blank line for readability
            }
        }
    }

    private void printMainMenu() {
        System.out.println("====================================");
        System.out.println("        JRE Learning Lab");
        System.out.println("====================================");
        System.out.println("1. Collections & Generics");
        System.out.println("2. Streams & Files (I/O)");
        System.out.println("3. Logging (java.util.logging)");
        System.out.println("4. Multithreading & Concurrency");
        System.out.println("5. Serialization");
        System.out.println("6. Reflection & Annotations");
        System.out.println("7. Environment & Configuration");
        System.out.println("8. Strings, Formatting & Regex");
        System.out.println("9. ZIP File System (java.nio file)");
        System.out.println("0. Exit");
        System.out.println("====================================");
    }

    private void showZipFileSystemDemo() {
        ZipFileSystemDemo.run();
    }

    private void showStringsAndRegexDemo() {
        StringsAndRegexDemo.run();
    }

    // For now, these are just placeholders.
    private void showCollectionsDemo() {
        CollectionsDemo.run();
    }

    private void showStreamsAndFilesDemo() {
        StreamsAndFilesDemo.run();
    }

    private void showLoggingDemo() {
        LoggingDemo.run();
    }

    private void showConcurrencyDemo() {
        ConcurrencyDemo.run();
    }

    private void showSerializationDemo() {
        SerializationDemo.run();
    }

    private void showReflectionAndAnnotationsDemo() {
        ReflectionAndAnnotationsDemo.run();
    }

    private void showEnvironmentInfoDemo() {
        EnvironmentAndConfigDemo.run();
    }
}
