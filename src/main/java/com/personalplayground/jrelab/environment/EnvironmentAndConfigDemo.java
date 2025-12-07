package com.personalplayground.jrelab.environment;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

public class EnvironmentAndConfigDemo {

    // We’ll let main() pass the program arguments into this demo.
    private static String[] programArgs = new String[0];

    public static void setProgramArgs(String[] args) {
        if (args == null) {
            programArgs = new String[0];
        } else {
            programArgs = args.clone(); // defensive copy
        }
    }

    public static void run() {
        System.out.println("=== Environment & Configuration Demo ===");

        overview();
        commandLineArgsDemo();
        propertiesDemo();
        systemAndEnvVarsDemo();
    }

    // 1) High-level overview
    private static void overview() {
        System.out.println("\n-- Overview --");
        System.out.println("Java apps are influenced by the environment around them:");
        System.out.println("  * Command-line arguments   -> String[] args to main()");
        System.out.println("  * Properties files         -> persistent key/value config");
        System.out.println("  * System properties        -> JVM + OS info (user, home, Java version, classpath)");
        System.out.println("  * Environment variables    -> OS-level key/value pairs");
        System.out.println("We’ll look at each of these in turn.");
    }

    // 2) Command-line args
    private static void commandLineArgsDemo() {
        System.out.println("\n-- Command-line arguments --");

        System.out.println("Number of args: " + programArgs.length);
        if (programArgs.length == 0) {
            System.out.println("No arguments were passed to main(String[] args).");
            System.out.println("In IntelliJ, you can set 'Program arguments' in the Run Configuration");
            System.out.println("to see them show up here (e.g.,: data/sample.txt --verbose).");
        } else {
            for (int i = 0; i < programArgs.length; i++) {
                System.out.println("  args[" + i + "] = " + programArgs[i]);
            }
        }

        System.out.println("Typical use: paths, mode flags (e.g., --debug), config file locations, etc.");
    }

    // 3) Properties: defaults + user overrides stored in a file
    private static void propertiesDemo() {
        System.out.println("\n-- Properties with defaults + user overrides --");

        Path dataDir = Paths.get("data");
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.out.println("Could not create data directory: " + e.getMessage());
            return;
        }

        Path userPropsFile = dataDir.resolve("user-settings.properties");

        // Default settings for the app
        Properties defaults = new Properties();
        defaults.setProperty("app.name", "JRE Learning Lab");
        defaults.setProperty("app.language", "en");
        defaults.setProperty("editor.theme", "light");      // default theme
        defaults.setProperty("editor.fontSize", "14");      // default font size

        // User properties that overlay defaults
        Properties userProps = new Properties(defaults);

        // Load existing user properties if the file exists
        if (Files.exists(userPropsFile)) {
            System.out.println("Loading user settings from: " + userPropsFile.toAbsolutePath());
            try (Reader reader = Files.newBufferedReader(userPropsFile, StandardCharsets.UTF_8)) {
                userProps.load(reader);
            } catch (IOException e) {
                System.out.println("Could not read user settings: " + e.getMessage());
            }
        } else {
            System.out.println("No user-settings.properties found yet.");
            System.out.println("We will create one with a couple of overrides.");

            // First-time “user” overrides
            userProps.setProperty("editor.theme", "dark");
            userProps.setProperty("editor.fontSize", "16");

            try (Writer writer = Files.newBufferedWriter(userPropsFile, StandardCharsets.UTF_8)) {
                userProps.store(writer, "User-specific settings for JRE Learning Lab");
                System.out.println("Created user settings file at: " + userPropsFile.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Could not write user settings: " + e.getMessage());
            }
        }

        System.out.println("\nEffective settings (user overrides fall back to defaults):");
        String appName = userProps.getProperty("app.name");
        String language = userProps.getProperty("app.language");
        String theme = userProps.getProperty("editor.theme");
        String fontSize = userProps.getProperty("editor.fontSize");
        String nonExisting = userProps.getProperty("non.existing.key", "<default-value-if-missing>");

        System.out.println("  app.name         = " + appName);
        System.out.println("  app.language     = " + language);
        System.out.println("  editor.theme     = " + theme);
        System.out.println("  editor.fontSize  = " + fontSize);
        System.out.println("  non.existing.key = " + nonExisting);
        System.out.println();
        System.out.println("Notice:");
        System.out.println("  * We used a Properties object with defaults + user overrides.");
        System.out.println("  * getProperty(key, defaultValue) returns the provided default if the key is missing.");
    }

    // 4) System properties and environment variables
    private static void systemAndEnvVarsDemo() {
        System.out.println("\n-- System properties & environment variables --");

        System.out.println("Some common system properties:");
        printSystemProperty("user.name");
        printSystemProperty("user.home");
        printSystemProperty("os.name");
        printSystemProperty("os.arch");
        printSystemProperty("java.version");
        printSystemProperty("java.vendor");

        System.out.println();
        System.out.println("Classpath (shortened for display):");
        String classpath = System.getProperty("java.class.path");
        if (classpath == null) {
            System.out.println("  <no classpath property>");
        } else {
            // Print at most first 200 chars just to avoid huge output
            if (classpath.length() > 200) {
                System.out.println("  " + classpath.substring(0, 200) + "...");
            } else {
                System.out.println("  " + classpath);
            }
        }

        System.out.println();
        System.out.println("Some environment variables (from the OS):");
        Map<String, String> env = System.getenv();

        // Try a few common names, but handle null if they don't exist
        printEnvVar(env, "PATH");
        printEnvVar(env, "HOME");
        printEnvVar(env, "USER");
        printEnvVar(env, "USERNAME");

        System.out.println();
        System.out.println("You can see all environment variables by iterating over env.entrySet(),");
        System.out.println("but be careful not to log sensitive values in real applications.");
    }

    private static void printSystemProperty(String name) {
        String value = System.getProperty(name);
        System.out.println("  " + name + " = " + value);
    }

    private static void printEnvVar(Map<String, String> env, String name) {
        String value = env.get(name);
        if (value == null) {
            System.out.println("  " + name + " is not set in this environment.");
        } else {
            System.out.println("  " + name + " = " + value);
        }
    }
}
