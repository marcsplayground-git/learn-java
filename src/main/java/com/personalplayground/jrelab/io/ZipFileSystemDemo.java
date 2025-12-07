package com.personalplayground.jrelab.io;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ZipFileSystemDemo {

    public static void run() {
        System.out.println("=== ZIP File System Demo ===");

        explainConcept();

        Path zipPath = prepareZipPath();
        if (zipPath == null) {
            return; // something went wrong creating data dir
        }

        createZipWithFile(zipPath);
        listZipContents(zipPath);
        readFileFromZip(zipPath);
    }

    // 1) High-level explanation
    private static void explainConcept() {
        System.out.println("\n-- What is a ZIP file system? --");
        System.out.println("java.nio.file can treat a ZIP file like its own mini file system.");
        System.out.println("You can:");
        System.out.println("  * Open a ZIP as a FileSystem");
        System.out.println("  * Use Path + Files to create/read/copy files INSIDE the ZIP");
        System.out.println("This lets you use the same APIs (Path, Files) for normal files and ZIP contents.");
    }

    // Ensure we have a data directory and decide where the ZIP file lives
    private static Path prepareZipPath() {
        Path dataDir = Paths.get("data");
        try {
            Files.createDirectories(dataDir);
        } catch (IOException e) {
            System.out.println("Could not create data directory: " + e.getMessage());
            return null;
        }

        Path zipPath = dataDir.resolve("sample-zipfs.zip");

        // To keep the demo simple, delete old ZIP if it exists
        if (Files.exists(zipPath)) {
            try {
                Files.delete(zipPath);
                System.out.println("Deleted existing ZIP: " + zipPath.toAbsolutePath());
            } catch (IOException e) {
                System.out.println("Could not delete old ZIP: " + e.getMessage());
                return null;
            }
        }

        return zipPath;
    }

    // 2) Create a ZIP and write a file inside it
    private static void createZipWithFile(Path zipPath) {
        System.out.println("\n-- Creating ZIP and writing a file inside --");

        // Environment map: "create" = "true" -> create the ZIP file if missing
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");

        // URI with jar: scheme tells FileSystems to use the ZIP/JAR provider
        URI uri = URI.create("jar:" + zipPath.toUri().toString());

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            // Path INSIDE the ZIP
            Path internalFile = zipfs.getPath("/hello-from-zip.txt");

            List<String> lines = List.of(
                    "Hello from inside a ZIP file system!",
                    "You can treat ZIP contents like normal Paths.",
                    "java.nio.file.Files works here too."
            );

            Files.write(internalFile, lines, StandardCharsets.UTF_8);
            System.out.println("Wrote hello-from-zip.txt inside " + zipPath.getFileName());
        } catch (IOException e) {
            System.out.println("Error creating ZIP or writing to it: " + e.getMessage());
        }
    }

    // 3) List the contents of the ZIP root directory
    private static void listZipContents(Path zipPath) {
        System.out.println("\n-- Listing ZIP contents --");

        Map<String, String> env = new HashMap<>();
        // no "create" here; we expect the ZIP to already exist

        URI uri = URI.create("jar:" + zipPath.toUri().toString());

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path root = zipfs.getPath("/");

            System.out.println("Entries in " + zipPath.getFileName() + ":");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(root)) {
                for (Path p : stream) {
                    System.out.println("  " + p.toString());
                }
            }
        } catch (IOException e) {
            System.out.println("Error listing ZIP contents: " + e.getMessage());
        }
    }

    // 4) Read the file back from inside the ZIP
    private static void readFileFromZip(Path zipPath) {
        System.out.println("\n-- Reading file from inside ZIP --");

        Map<String, String> env = new HashMap<>();
        URI uri = URI.create("jar:" + zipPath.toUri().toString());

        try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path internalFile = zipfs.getPath("/hello-from-zip.txt");

            if (!Files.exists(internalFile)) {
                System.out.println("Internal file not found inside ZIP.");
                return;
            }

            System.out.println("Contents of hello-from-zip.txt:");
            List<String> lines = Files.readAllLines(internalFile, StandardCharsets.UTF_8);
            for (String line : lines) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from ZIP: " + e.getMessage());
        }
    }
}
