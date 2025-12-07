package com.personalplayground.jrelab.logging;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LoggingDemo {

    // Our main application logger
    private static final Logger APP_LOGGER = Logger.getLogger("com.personalplayground.jrelab");

    // Static block = runs once when LoggingDemo class is first loaded
    static {
        configureAppLogger();
    }

    private static void configureAppLogger() {
        // Stop logs from bubbling up to root logger's default handlers
        APP_LOGGER.setUseParentHandlers(false);

        // Remove existing handlers (important if demo is run multiple times)
        for (Handler handler : APP_LOGGER.getHandlers()) {
            APP_LOGGER.removeHandler(handler);
        }

        // Capture all levels from this logger
        APP_LOGGER.setLevel(Level.ALL);

        // Send logs to console
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);              // handler accepts all levels
        consoleHandler.setFormatter(new SimpleFormatter()); // default human-readable formatter

        APP_LOGGER.addHandler(consoleHandler);
    }

    public static void run() {
        System.out.println("=== Logging (java.util.logging) Demo ===");

        explainArchitecture();
        basicConsoleLogging();
        levelFilteringDemo();
        fileLoggingDemo();
        hierarchyDemo();
    }

    // 1) Explain the main pieces
    private static void explainArchitecture() {
        System.out.println("\n-- Logging architecture overview --");

        LogManager manager = LogManager.getLogManager();
        System.out.println("LogManager is a single, JVM-wide object that stores logging configuration.");
        System.out.println("Its runtime type is: " + manager.getClass().getName());

        Logger rootLogger = Logger.getLogger("");
        System.out.println("The root logger's name is an empty string: \"" + rootLogger.getName() + "\"");

        System.out.println("Our application logger is named: " + APP_LOGGER.getName());
        System.out.println("Loggers form a name-based hierarchy.");
        System.out.println("For example, 'com.personalplayground.jrelab.io' is a child of 'com.personalplayground.jrelab'.");
        System.out.println("Each logger can have Handlers (console, file, socket, etc.),");
        System.out.println("and each Handler uses a Formatter to turn log records into text.");
    }

    // 2) Compare simple logging to System.out
    private static void basicConsoleLogging() {
        System.out.println("\n-- Basic console logging --");
        System.out.println("System.out.println is fine for quick prints,");
        System.out.println("but logging adds levels (INFO, WARNING, SEVERE, etc.) and consistent formatting.");

        APP_LOGGER.info("This is an INFO message from LoggingDemo.");
        APP_LOGGER.warning("This is a WARNING message.");
        APP_LOGGER.severe("This is a SEVERE message.");
    }

    // 3) Show how levels filter what you see
    private static void levelFilteringDemo() {
        System.out.println("\n-- Level filtering demo --");

        Logger levelLogger = Logger.getLogger("com.personalplayground.jrelab.levels");
        levelLogger.setUseParentHandlers(false); // we'll control handlers ourselves

        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter());

        // Handler only prints INFO and above
        handler.setLevel(Level.INFO);
        levelLogger.addHandler(handler);

        // Logger allows FINE and above, but handler will still hide FINE
        levelLogger.setLevel(Level.FINE);

        System.out.println("Logger level = FINE, handler level = INFO");
        System.out.println("Now logging FINE, INFO, and WARNING:");

        levelLogger.fine("FINE: detailed debug message (often hidden).");
        levelLogger.info("INFO: high-level information.");
        levelLogger.warning("WARNING: something looks suspicious, but app is still running.");

        System.out.println("Notice that FINE usually does not appear because handler level is INFO.");
        System.out.println("Now we lower the handler level to FINE so all messages show:");

        handler.setLevel(Level.FINE);
        levelLogger.fine("FINE: now visible because handler level is FINE.");
    }

    // 4) Log to a file using FileHandler
    private static void fileLoggingDemo() {
        System.out.println("\n-- File logging demo (FileHandler) --");

        Logger fileLogger = Logger.getLogger("com.personalplayground.jrelab.file");
        fileLogger.setUseParentHandlers(false);

        try {
            Path logsDir = Paths.get("logs");
            Files.createDirectories(logsDir);

            // true = append to the same file on each run
            FileHandler fileHandler = new FileHandler("logs/jre-learning-lab.log", true);
            fileHandler.setLevel(Level.INFO);
            fileHandler.setFormatter(new SimpleFormatter());

            fileLogger.addHandler(fileHandler);
            fileLogger.setLevel(Level.INFO);

            fileLogger.info("INFO: This message goes into logs/jre-learning-lab.log.");
            fileLogger.warning("WARNING: This message also goes into the log file.");

            System.out.println("Wrote a couple of log records to: " + logsDir.toAbsolutePath());
            System.out.println("Open logs/jre-learning-lab.log in a text editor to inspect the formatted entries.");

            fileHandler.close();
        } catch (IOException e) {
            System.out.println("Could not create or write to log file: " + e.getMessage());
        } catch (SecurityException se) {
            System.out.println("Security manager prevented creating a FileHandler: " + se.getMessage());
        }
    }

    // 5) Show logger hierarchy using a child logger
    private static void hierarchyDemo() {
        System.out.println("\n-- Logger hierarchy demo --");

        Logger childLogger = Logger.getLogger("com.personalplayground.jrelab.io");

        Logger parent = childLogger.getParent();
        String parentName = (parent != null ? parent.getName() : "<no parent>");

        System.out.println("Child logger name:   " + childLogger.getName());
        System.out.println("Parent logger name:  " + parentName);

        System.out.println("We will log from the child logger, but it will reuse the parent handlers.");
        childLogger.info("INFO from child logger (com.personalplayground.jrelab.io).");
        childLogger.warning("WARNING from child logger.");

        System.out.println("Because APP_LOGGER has a console handler and child uses parent handlers,");
        System.out.println("these messages appear with the same formatting as APP_LOGGER.");
    }
}
