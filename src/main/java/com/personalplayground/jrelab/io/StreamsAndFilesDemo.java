package com.personalplayground.jrelab.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class StreamsAndFilesDemo {

    public static void run() {
        System.out.println("=== Streams & Files Demo ===");

        explainStreamConcept();
        basicByteStreamDemo();
        textFileWriteAndReadDemo();
        bufferedCharArrayReadDemo();
        inputStreamReaderBridgeDemo();
    }

    // 1) Explain the idea of a stream
    private static void explainStreamConcept() {
        System.out.println("\n-- What is a stream? --");
        System.out.println("A stream is an ordered, one-way flow of data.");
        System.out.println("You either read from it (input) or write to it (output), not both at once.");
        System.out.println("There are two big families:");
        System.out.println("  * Byte streams  (binary data)  -> InputStream / OutputStream");
        System.out.println("  * Text streams  (characters)   -> Reader / Writer");
    }

    // 2) Show a simple byte stream and read() returning -1 at end
    private static void basicByteStreamDemo() {
        System.out.println("\n-- Byte stream demo (ByteArrayInputStream) --");

        // 65, 66, 67 are ASCII codes for 'A', 'B', 'C'
        byte[] data = {65, 66, 67};

        // Treat the byte[] as an InputStream
        try (InputStream in = new ByteArrayInputStream(data)) {
            int value;
            while ((value = in.read()) != -1) {
                char asChar = (char) value;
                System.out.println("Read byte: " + value + " as char: " + asChar);
            }
            System.out.println("read() returned -1 -> reached end of stream.");
        } catch (IOException e) {
            System.out.println("Unexpected I/O error in basicByteStreamDemo: " + e.getMessage());
        }
    }

    // 3) Write & read a text file using java.nio.file + BufferedReader/Writer + try-with-resources
    private static void textFileWriteAndReadDemo() {
        System.out.println("\n-- Text file write/read with java.nio.file + buffering --");

        // Create a 'data' directory next to where you run the app
        Path dataDir = Paths.get("data");
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.out.println("Could not create data directory: " + e.getMessage());
            return;
        }

        Path file = dataDir.resolve("sample-notes.txt");

        // Write some lines to the file
        // try-with-resources: writer will be automatically closed
        try (BufferedWriter writer = Files.newBufferedWriter(file, StandardCharsets.UTF_8)) {
            writer.write("First line of text.");
            writer.newLine(); // platform-correct line break
            writer.write("Second line using BufferedWriter.");
            writer.newLine();
            writer.write("Third line. Try-with-resources will close this file automatically.");
            System.out.println("Wrote some lines to " + file.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
            return;
        }

        // Now read the lines back with a BufferedReader
        System.out.println("Now reading lines back with BufferedReader:");
        try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                System.out.println("Line " + lineNumber + ": " + line);
                lineNumber++;
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // 4) Show read(char[]) with count and using only 0..count-1
    private static void bufferedCharArrayReadDemo() {
        System.out.println("\n-- read(char[]) with count demo --");

        Path file = Paths.get("data", "sample-notes.txt");
        if (!Files.exists(file)) {
            System.out.println("Sample file not found. Run the textFileWriteAndReadDemo() first.");
            return;
        }

        try (Reader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) {
            char[] buffer = new char[16]; // small buffer on purpose
            int charsRead;

            while ((charsRead = reader.read(buffer)) != -1) {
                // IMPORTANT: use only indices 0..charsRead-1
                String chunk = new String(buffer, 0, charsRead);
                System.out.println("Read chunk (" + charsRead + " chars): [" + chunk + "]");
            }

            System.out.println("read(buffer) returned -1 -> end of stream.");
        } catch (IOException e) {
            System.out.println("Error reading file with char[]: " + e.getMessage());
        }
    }

    // 5) Bridge from byte streams to text streams with InputStreamReader
    private static void inputStreamReaderBridgeDemo() {
        System.out.println("\n-- Binary -> text bridge demo (InputStreamReader) --");

        String text = "Hello via bytes!";
        byte[] bytes = text.getBytes(StandardCharsets.UTF_8);

        // Multiple resources in try-with-resources:
        // byteStream (InputStream) wrapped by InputStreamReader (Reader)
        try (InputStream byteStream = new ByteArrayInputStream(bytes);
             Reader reader = new InputStreamReader(byteStream, StandardCharsets.UTF_8)) {

            char[] buffer = new char[8];
            int charsRead;
            while ((charsRead = reader.read(buffer)) != -1) {
                String chunk = new String(buffer, 0, charsRead);
                System.out.println("Chunk: [" + chunk + "]");
            }

            System.out.println("InputStreamReader lets us treat raw bytes as characters.");
        } catch (IOException e) {
            System.out.println("Error in inputStreamReaderBridgeDemo: " + e.getMessage());
        }
    }
}
