package com.personalplayground.jrelab.strings;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringsAndRegexDemo {

    public static void run() {
        System.out.println("=== Strings, Formatting & Regex Demo ===");

        stringJoinerDemo();
        stringFormatDemo();
        formatterToFileDemo();
        regexDemo();
    }

    // 1) StringJoiner: building delimited strings
    private static void stringJoinerDemo() {
        System.out.println("\n-- StringJoiner demo --");
        System.out.println("StringJoiner builds a sequence with a separator,");
        System.out.println("and optional prefix/suffix (like [a, b, c]).");

        StringJoiner topicsJoiner = new StringJoiner(", ", "[", "]");
        topicsJoiner.add("Collections");
        topicsJoiner.add("Streams");
        topicsJoiner.add("Logging");
        topicsJoiner.add("Concurrency");

        String joined = topicsJoiner.toString();
        System.out.println("Joined topics: " + joined);

        // Demonstrate empty value
        StringJoiner emptyJoiner = new StringJoiner(", ", "{", "}");
        emptyJoiner.setEmptyValue("<no values>");
        System.out.println("Empty joiner.toString() = " + emptyJoiner);
        emptyJoiner.add("First");
        System.out.println("After adding one value: " + emptyJoiner);
    }

    // 2) String.format / printf and format specifiers
    private static void stringFormatDemo() {
        System.out.println("\n-- String.format / printf demo --");
        System.out.println("Formatting lets you describe the final result in a single format string.");

        String user = "Alice";
        int messages = 5;
        double ratio = 1.0 * messages / 3;

        String msg = String.format("User %s has %d new messages.", user, messages);
        System.out.println(msg);

        System.out.println("\nNumeric formatting examples:");
        System.out.printf("ratio as default %%f: %f%n", ratio);
        System.out.printf("ratio with 2 decimals (%%.2f): %.2f%n", ratio);
        System.out.printf("padded to width 10 (%%10.2f): %10.2f%n", ratio);

        System.out.println("\nReusing arguments with explicit indexes:");
        // %1$s -> first arg as String, %2$d -> second arg as int
        String reordered = String.format("Messages: %2$d, User: %1$s", user, messages);
        System.out.println(reordered);

        System.out.println("\nReminder:");
        System.out.println("  * %s -> string, %d -> integer, %f -> floating-point.");
        System.out.println("  * %.2f -> 2 decimal places, %n -> platform line break.");
    }

    // 3) Formatter writing formatted text to a file
    private static void formatterToFileDemo() {
        System.out.println("\n-- Formatter to file demo --");

        Path reportsDir = Paths.get("data");
        try {
            Files.createDirectories(reportsDir);
        } catch (IOException e) {
            System.out.println("Could not create data directory: " + e.getMessage());
            return;
        }

        Path reportFile = reportsDir.resolve("formatted-report.txt");

        // Formatter can write to any Appendable (here: a Writer from Files)
        try (Formatter formatter = new Formatter(
                Files.newBufferedWriter(reportFile, StandardCharsets.UTF_8))) {

            formatter.format("=== Learning Progress Report ===%n");
            formatter.format("Module %-25s Status%n", "Name");
            formatter.format("-----------------------------------------%n");
            formatter.format("%-25s %s%n", "Collections", "DONE");
            formatter.format("%-25s %s%n", "Streams & Files", "DONE");
            formatter.format("%-25s %s%n", "Logging", "DONE");
            formatter.format("%-25s %s%n", "Concurrency", "DONE");
            formatter.format("%-25s %s%n", "Serialization", "DONE");
            formatter.format("%-25s %s%n", "Reflection & Annotations", "DONE");
            formatter.format("%-25s %s%n", "Environment & Config", "DONE");

            System.out.println("Wrote formatted report to: " + reportFile.toAbsolutePath());
            System.out.println("Open it in a text editor to see aligned columns.");
        } catch (IOException e) {
            System.out.println("Error writing formatted report: " + e.getMessage());
        }
    }

    // 4) Regex with String and Pattern/Matcher
    private static void regexDemo() {
        System.out.println("\n-- Regex demo (String + Pattern/Matcher) --");

        String text = "User alice123 logged in from IP 192.168.0.42 at 2025-12-07.";
        System.out.println("Source text:");
        System.out.println("  " + text);

        // Simple String.replaceAll example: hide digits
        String masked = text.replaceAll("\\d", "X");
        System.out.println("\nreplaceAll(\"\\\\d\", \"X\") ->");
        System.out.println("  " + masked);

        // Split on non-word characters to get words
        String[] words = text.split("\\W+");
        System.out.println("\nSplit on non-word chars (\\\\W+):");
        for (String w : words) {
            if (!w.isEmpty()) {
                System.out.println("  word: " + w);
            }
        }

        // Use Pattern/Matcher to find all numbers
        Pattern numberPattern = Pattern.compile("\\d+");
        Matcher matcher = numberPattern.matcher(text);

        System.out.println("\nNumbers found with Pattern/Matcher (\\\\d+):");
        while (matcher.find()) {
            String value = matcher.group();
            int start = matcher.start();
            int end = matcher.end();
            System.out.println("  found \"" + value + "\" at [" + start + ", " + end + ")");
        }

        System.out.println("\nRemember:");
        System.out.println("  * In Java strings, backslash must be escaped: \"\\\\d\", \"\\\\w+\".");
        System.out.println("  * String has basic regex methods: matches, split, replaceAll, replaceFirst.");
        System.out.println("  * Pattern/Matcher is better when you reuse the same regex many times.");
    }
}
