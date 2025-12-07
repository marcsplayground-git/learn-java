package com.personalplayground.jrelab.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class CollectionsDemo {

    public static void run() {
        System.out.println("=== Collections & Generics Demo ===");

        basicListDemo();
        equalsAndContainsDemo();
        arraysAndCollectionsBridgeDemo();
    }

    // 1) Basic List<String> usage with generics
    private static void basicListDemo() {
        System.out.println("\n-- Basic List<String> demo --");

        // List<String> = "a growable list that only accepts Strings"
        List<String> topics = new ArrayList<>();

        // Dynamic sizing: we can keep adding items
        topics.add("Streams & Files");
        topics.add("Logging");
        topics.add("Multithreading");
        topics.add("Serialization");

        System.out.println("We created a List<String> and added " + topics.size() + " items.");
        System.out.println("Current topics: " + topics);

        // Iterate using enhanced for-loop
        System.out.println("Iterating over topics:");
        for (String topic : topics) {
            System.out.println(" * " + topic);
        }

        // Remove an item (by value, uses equals internally)
        System.out.println("Removing \"Logging\" from the list...");
        topics.remove("Logging");
        System.out.println("After removal: " + topics);
    }

    // Small helper type to show how equals() affects collection behavior
    private static class Topic {
        private final String name;

        Topic(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Topic{" + name + "}";
        }

        // equals() so that two Topic objects with same name are treated as "equal"
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Topic)) return false;
            Topic other = (Topic) o;
            return Objects.equals(this.name, other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

    // 2) Show that contains() uses equals(), not memory identity
    private static void equalsAndContainsDemo() {
        System.out.println("\n-- equals() and contains() demo --");

        List<Topic> topics = new ArrayList<>();
        topics.add(new Topic("Collections"));
        topics.add(new Topic("Streams"));

        System.out.println("Topic list: " + topics);

        Topic search = new Topic("Collections");

        System.out.println("Searching list for new Topic(\"Collections\") using contains() ...");
        boolean found = topics.contains(search);

        System.out.println("Result of contains(search) = " + found);
        System.out.println("Because Topic overrides equals(), the list can match by name,");
        System.out.println("even though 'search' is a different object in memory.");
    }

    // 3) Show conversion between arrays and collections
    private static void arraysAndCollectionsBridgeDemo() {
        System.out.println("\n-- Arrays <-> Collections bridge --");

        String[] array = {"Collections", "Streams", "Logging"};
        System.out.println("Start with String[] array: " + Arrays.toString(array));

        // Arrays.asList gives a fixed-size list backed by the array
        List<String> fixedList = Arrays.asList(array);
        System.out.println("Arrays.asList(array) gives: " + fixedList);

        System.out.println("Trying to add to fixedList would throw an exception,");
        System.out.println("so we copy it into a new ArrayList to make it resizable.");

        List<String> resizableList = new ArrayList<>(fixedList);
        resizableList.add("Concurrency");
        System.out.println("Resizable list after add: " + resizableList);

        // Back to array with toArray(new String[0])
        String[] backToArray = resizableList.toArray(new String[0]);
        System.out.println("Back to String[] via toArray(new String[0]): "
                + Arrays.toString(backToArray));
    }
}
