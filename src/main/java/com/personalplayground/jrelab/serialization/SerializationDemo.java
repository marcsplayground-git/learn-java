package com.personalplayground.jrelab.serialization;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SerializationDemo {

    public static void run() {
        System.out.println("=== Serialization Demo ===");

        explainConcepts();
        simpleSerializationDemo();
    }

    // 1) High-level explanation
    private static void explainConcepts() {
        System.out.println("\n-- What is serialization? --");
        System.out.println("Serialization = converting an object graph into a stream of bytes.");
        System.out.println("Deserialization = rebuilding the object graph from those bytes.");
        System.out.println("Use cases: save app state to file, send objects over network, etc.");
        System.out.println();
        System.out.println("In Java:");
        System.out.println("  * Classes must implement java.io.Serializable (marker interface).");
        System.out.println("  * ObjectOutputStream writes objects to an OutputStream.");
        System.out.println("  * ObjectInputStream reads them back from an InputStream.");
        System.out.println("  * serialVersionUID controls version compatibility of serialized forms.");
        System.out.println("  * transient fields are NOT saved (they get default value when restored).");
    }

    // 2) Full example: write + read a serializable object
    private static void simpleSerializationDemo() {
        System.out.println("\n-- Simple save & load demo --");

        // Make sure we have a data directory (reuse same as other demos)
        Path dataDir = Paths.get("data");
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.out.println("Could not create data directory: " + e.getMessage());
            return;
        }

        Path file = dataDir.resolve("session.bin");

        // Create a sample object to serialize
        StudySession original = new StudySession(
                "Streams & Files",
                90,
                true
        );

        System.out.println("Original object before serialization:");
        System.out.println("  " + original);

        // --- Serialize: write object to file --- //
        try (ObjectOutputStream out =
                     new ObjectOutputStream(Files.newOutputStream(file))) {

            out.writeObject(original);
            System.out.println("Object written to: " + file.toAbsolutePath());

        } catch (IOException e) {
            System.out.println("Error writing object: " + e.getMessage());
            return;
        }

        // For demonstration, change the original object after saving
        original.setInProgress(false);
        System.out.println("Original object after we manually set inProgress=false:");
        System.out.println("  " + original);

        // --- Deserialize: read object from file --- //
        StudySession loaded = null;
        try (ObjectInputStream in =
                     new ObjectInputStream(Files.newInputStream(file))) {

            Object obj = in.readObject();
            loaded = (StudySession) obj; // cast back to correct type

        } catch (IOException e) {
            System.out.println("Error reading object: " + e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.out.println("Could not find class while reading object: " + e.getMessage());
            return;
        }

        System.out.println("Loaded object after deserialization:");
        System.out.println("  " + loaded);

        System.out.println();
        System.out.println("Notice:");
        System.out.println("  * subject and plannedMinutes were saved and restored.");
        System.out.println("  * inProgress is transient -> it was not in the file,");
        System.out.println("    so after deserialization it has the default value (false).");
        System.out.println("  * If we add/remove fields in StudySession later,");
        System.out.println("    serialVersionUID controls whether old data is still compatible.");
    }

    /**
     * Serializable type representing a simple study session.
     */
    private static class StudySession implements Serializable {

        // Good practice: declare a stable serialVersionUID.
        // If you change the structure in an incompatible way,
        // you can change this value to reject old data.
        private static final long serialVersionUID = 1L;

        private String subject;
        private int plannedMinutes;

        // transient = do not serialize this field
        private transient boolean inProgress;

        public StudySession(String subject, int plannedMinutes, boolean inProgress) {
            this.subject = subject;
            this.plannedMinutes = plannedMinutes;
            this.inProgress = inProgress;
        }

        public void setInProgress(boolean inProgress) {
            this.inProgress = inProgress;
        }

        @Override
        public String toString() {
            return "StudySession{" +
                    "subject='" + subject + '\'' +
                    ", plannedMinutes=" + plannedMinutes +
                    ", inProgress=" + inProgress +
                    '}';
        }
    }
}
