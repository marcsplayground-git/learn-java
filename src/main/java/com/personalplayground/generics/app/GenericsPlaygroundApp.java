package com.personalplayground.generics.app;

import java.util.Scanner;
import com.personalplayground.generics.basics.BoxDemo;
import com.personalplayground.generics.basics.CollectionsDemo;
import com.personalplayground.generics.advanced.WildcardsDemo;
import com.personalplayground.generics.advanced.AdvancedTopicsDemo;

public class GenericsPlaygroundApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("================================");
            System.out.println("     Java Generics Playground   ");
            System.out.println("================================");
            System.out.println("1. Basic generic class (Box<T>)");
            System.out.println("2. Generic collections (List, Set, Map)");
            System.out.println("3. Wildcards and PECS");
            System.out.println("4. Erasure, raw types, and sharp edges");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String input = scanner.nextLine().trim();

            if ("0".equals(input)) {
                System.out.println("Exiting Generics Playground. Goodbye!");
                break;
            }

            System.out.println(); // blank line before demo output

            switch (input) {
                case "1" -> BoxDemo.run();
                case "2" -> CollectionsDemo.run();
                case "3" -> WildcardsDemo.run();
                case "4" -> AdvancedTopicsDemo.run();
                default -> System.out.println("Unknown option. Please try again.");
            }

            System.out.println();
            System.out.println("Press Enter to return to the main menu...");
            scanner.nextLine();
        }

        scanner.close();
    }
}
