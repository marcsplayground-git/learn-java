package com.personalplayground.exceptionsplayground;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ExceptionPlaygroundApp {

    public static void main(String[] args) {
        System.out.println("=== Exception Playground ===");
        System.out.println("Milestone 8: Do's & don'ts, review, and quiz\n");

        String fileName = "statements.txt";

        // 1) Run the file processing demo (local vs central handling)
        processFileOfStatements(fileName);

        // 2) Run the interactive quiz
        runActiveRecallQuiz();

        System.out.println("=== End of Milestone 8 demo ===");
    }


    private static double processStatement(String statement) throws InvalidStatementException {
        // 1) Basic null/empty validation
        if (statement == null || statement.isBlank()) {
            throw new InvalidStatementException("Statement cannot be empty.");
        }

        // 2) Split into parts
        String[] parts = statement.trim().split("\\s+");
        if (parts.length != 3) {
            throw new InvalidStatementException(
                    "Incorrect number of parts. Expected: <operation> <left> <right> but got: " + parts.length
            );
        }

        String op = parts[0].toLowerCase();
        String leftText = parts[1];
        String rightText = parts[2];

        double left;
        double right;

        // 3) Convert numbers, chaining NumberFormatException if it happens
        try {
            left = Double.parseDouble(leftText);
            right = Double.parseDouble(rightText);
        } catch (NumberFormatException ex) {
            // Wrap the lower-level exception inside our custom one (chaining)
            throw new InvalidStatementException(
                    "Non-numeric data in statement: \"" + statement + "\"",
                    ex
            );
        }

        // 4) Perform the operation, possibly throwing custom errors
        switch (op) {
            case "add":
                return left + right;
            case "subtract":
                return left - right;
            case "multiply":
                return left * right;
            case "divide":
                if (right == 0) {
                    // We can create a cause manually if we want
                    ArithmeticException cause = new ArithmeticException("Division by zero");
                    throw new InvalidStatementException(
                            "Cannot divide by zero in statement: \"" + statement + "\"",
                            cause
                    );
                }
                return left / right;
            default:
                // Unknown operation
                throw new InvalidStatementException(
                        "Unknown operation: \"" + op + "\" in statement: \"" + statement + "\""
                );
        }
    }

    private static void processFileOfStatements(String fileName) {
        System.out.println("Opening file: " + fileName);

        // Centralized, file-level try-with-resources
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNumber = 0;

            System.out.println("Starting to process statements from file...\n");

            // Local per-line processing loop
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = line.trim();

                if (trimmed.isEmpty()) {
                    System.out.println("Line " + lineNumber + ": [SKIP] Empty line");
                    continue;
                }

                System.out.println("Line " + lineNumber + ": \"" + trimmed + "\"");

                // Localized try/catch: one bad line should not stop the whole file
                try {
                    double result = processStatement(trimmed);
                    System.out.println("  User-facing: Result = " + result);
                } catch (InvalidStatementException e) {
                    // User-friendly error
                    System.out.println("  User-facing error: " + e.getMessage());

                    // Developer details
                    Throwable cause = e.getCause();
                    if (cause != null) {
                        System.out.println("  Dev cause type: " + cause.getClass().getSimpleName());
                        System.out.println("  Dev cause message: " + cause.getMessage());
                        // In a real app, you might log stack trace here:
                        // e.printStackTrace();
                    } else {
                        System.out.println("  Dev note: No underlying cause (cause is null).");
                    }
                }

                System.out.println(); // blank line between statements
            }

            System.out.println("Finished processing file.\n");

        } catch (IOException e) {
            // Centralized catch: file-level problems
            System.out.println("Could not open or read file: " + fileName);
            System.out.println("IOException message: " + e.getMessage());
            // Real app: log e.printStackTrace() for debugging
        }
    }

    private static void runActiveRecallQuiz() {
        System.out.println("=== Active Recall Quiz: Exceptions ===");
        System.out.println("You can type an answer or just press Enter to see the sample answer.\n");

        try (Scanner scanner = new Scanner(System.in)) {

            // Question 1
            System.out.println("Q1) What is the difference between a checked and an unchecked exception?");
            System.out.print("Your answer: ");
            scanner.nextLine();
            System.out.println("Sample answer:");
            System.out.println("  - Checked: compiler forces you to handle or declare it with 'throws'.");
            System.out.println("  - Unchecked: subclass of RuntimeException; compiler does not enforce handling.");
            System.out.println();

            // Question 2
            System.out.println("Q2) Why must more specific exception types come before general ones in multiple catch blocks?");
            System.out.print("Your answer: ");
            scanner.nextLine();
            System.out.println("Sample answer:");
            System.out.println("  - Because the first compatible catch wins.");
            System.out.println("  - If you put Exception first, it would catch everything and make specific catches unreachable.");
            System.out.println();

            // Question 3
            System.out.println("Q3) When should you use try-with-resources instead of a finally block?");
            System.out.print("Your answer: ");
            scanner.nextLine();
            System.out.println("Sample answer:");
            System.out.println("  - When using resources that implement AutoCloseable/Closeable");
            System.out.println("    (files, DB connections, sockets) so Java can auto-close them.");
            System.out.println();

            // Question 4
            System.out.println("Q4) How do you create a custom checked exception?");
            System.out.print("Your answer: ");
            scanner.nextLine();
            System.out.println("Sample answer:");
            System.out.println("  - Create a class that extends Exception (or another checked exception).");
            System.out.println("  - Provide constructors like (String message) and optionally (String message, Throwable cause).");
            System.out.println();

            // Question 5
            System.out.println("Q5) Why is handling errors per item (for example, per line in a file) often better than only in main?");
            System.out.print("Your answer: ");
            scanner.nextLine();
            System.out.println("Sample answer:");
            System.out.println("  - Because one bad item does not stop the whole process.");
            System.out.println("  - You can log or report the bad item and still process the rest.");
            System.out.println();

            System.out.println("End of quiz. Review any question where your answer felt uncertain.\n");
        }
    }

}