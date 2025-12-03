package com.personalplayground.simplemapplayground;

import java.util.*;

public class MapPlayground {

    static class Box {
        int id;

        Box(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Box(" + id + ")";
        }
    }

    static class Box2 {
        int id;

        Box2(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Box2)) return false;
            Box2 other = (Box2) o;
            return this.id == other.id;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(id);
        }

        @Override
        public String toString() {
            return "Box2(" + id + ")";
        }
    }

    static class MutableKey {
        int value;

        MutableKey(int value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MutableKey)) return false;
            MutableKey other = (MutableKey) o;
            return value == other.value;
        }

        @Override
        public int hashCode() {
            return Integer.hashCode(value);
        }

        @Override
        public String toString() {
            return "MutableKey(" + value + ")";
        }
    }

    static class User {
        private final int id;
        String name;

        User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }

    static class Book {
        int id;
        String title;
        boolean isBorrowed;

        Book(int id, String title) {
            this.id = id;
            this.title = title;
            this.isBorrowed = false;
        }

        @Override
        public String toString() {
            return "Book{id=" + id + ", title='" + title + "', borrowed=" + isBorrowed + "}";
        }
    }

    static class User2 {
        int id;
        String name;

        User2(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{id=" + id + ", name='" + name + "'}";
        }
    }



    public static void main(String[] args) {

        // =======================
        // Milestone 2 Experiments
        // =======================
        /*
            // 1. Create a map
            Map<String, Integer> scores = new HashMap<>();

            // 2. Add entries
            scores.put("Alice", 90);
            scores.put("Bob", 75);
            scores.put("Charlie", 88);

            // 3. Lookup
            System.out.println("Alice score: " + scores.get("Alice"));

            // --- Experiment 1: Replacing values ---
            scores.put("Alice", 95); // replaces Alice's old score (90)
            System.out.println("After replacing Alice: " + scores);

            // --- Experiment 2: Duplicate key ---
            scores.put("Bob", 500); // overwrites Bob's old score (75)
            System.out.println("After updating Bob: " + scores);

            // --- Experiment 3: Remove through keySet ---
            scores.keySet().remove("Alice");
            System.out.println("After removing Alice via keySet: " + scores);

            // --- Experiment 4: Remove via values ---
            scores.values().remove(88); // removes entry whose value is 88 (Charlie)
            System.out.println("After removing value 88 via values(): " + scores);

            // --- Experiment 5: Mutating entries in entrySet ---
            for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                if (entry.getKey().equals("Bob")) {
                    entry.setValue(entry.getValue() + 10); // increase Bob's score
                }
            }
            System.out.println("After modifying entrySet(): " + scores);

            // 4. Contains
            System.out.println("Contains Bob? " + scores.containsKey("Bob"));

            // 5. Remove
            scores.remove("Charlie");

            // 6. Print map
            System.out.println("All entries: " + scores);

            // 7. Views
            System.out.println("Keys: " + scores.keySet());
            System.out.println("Values: " + scores.values());
            System.out.println("Entries: " + scores.entrySet());

         */

        // =======================
        // Milestone 3 Experiments
        // =======================
        /*
        Map<String, Integer> items = new HashMap<>();
        items.put("apple", 2);
        items.put("banana", 5);


        // --- computeIfAbsent ---
        items.computeIfAbsent("orange", key -> 10); // new entry created
        items.computeIfAbsent("apple", key -> 999); // apple exists → not changed

        System.out.println("After computeIfAbsent: " + items);


        // --- getOrDefault ---
        int appleCount = items.getOrDefault("apple", 0);
        int orangeCount = items.getOrDefault("orange", 0);

        System.out.println("Apple count: " + appleCount);   // 2
        System.out.println("Orange count: " + orangeCount); // 0 (default)


        // --- merge ---
        items.merge("banana", 3, Integer::sum); // add 3
        items.merge("grape", 4, Integer::sum); // grape not present → 4

        System.out.println("After merge: " + items);


        // --- compute ---
        items.compute("banana", (key, oldVal) -> oldVal == null ? 1 : oldVal * 2);
        System.out.println("After compute: " + items);

        items.compute("banana", (k, v) -> null);
        System.out.println("After removing banana via compute: " + items);
         */

        // =======================
        // Milestone 4 Part 1: Counting frequencies
        // =======================
        /*
            String[] fruits = {"apple", "banana", "apple", "orange", "banana", "apple"};

            Map<String, Integer> counts = new HashMap<>();

            for (String fruit : fruits) {
                counts.merge(fruit, 1, (oldVal, newVal) -> oldVal + 1);
            }

            System.out.println("Fruit counts: " + counts);

            // =======================
            // Milestone 4 Part 2: Grouping
            // =======================

            Map<Character, List<String>> groups = new HashMap<>();

            for (String fruit : fruits) {
                char firstLetter = fruit.charAt(0);

                groups.computeIfAbsent(firstLetter, k -> new ArrayList<>())
                        .add(fruit);
            }

            System.out.println("Grouped fruits: " + groups);

            // =======================
            // Milestone 4 Part 3: Group + Count
            // =======================

            String[] animals = {"dog", "cat", "cow", "dog", "cat", "dog"};

            Map<String, Integer> animalCounts = new HashMap<>();

            for (String animal : animals) {
                animalCounts.merge(animal, 1, Integer::sum);
            }

            System.out.println("Animal counts: " + animalCounts);
        */

        // =======================
        // Milestone 5 Part 1: equals() and key uniqueness
        // =======================
        /*
        Map<Box, String> boxMap = new HashMap<>();

        Box b1 = new Box(1);
        Box b2 = new Box(1);

        boxMap.put(b1, "First");
        boxMap.put(b2, "Second");

        System.out.println("boxMap size: " + boxMap.size());
        System.out.println("Map content: " + boxMap);

        // =======================
        // Milestone 5 Part 2: Proper equals() and hashCode()
        // =======================

        Map<Box2, String> boxMap2 = new HashMap<>();

        Box2 c1 = new Box2(1);
        Box2 c2 = new Box2(1);

        boxMap2.put(c1, "One");
        boxMap2.put(c2, "Two");

        System.out.println("boxMap2 size: " + boxMap2.size());
        System.out.println("boxMap2 content: " + boxMap2);

        // =======================
        // Milestone 5 Part 3: Mutating keys breaks HashMap
        // =======================

        Map<MutableKey, String> dangerMap = new HashMap<>();

        MutableKey mk = new MutableKey(10);

        dangerMap.put(mk, "Original");

        // Mutate the key
        mk.value = 99;

        System.out.println("Trying to get using mutated key: " + dangerMap.get(mk));
        System.out.println("Full map: " + dangerMap);
        */

        // =======================
        // Milestone 6: Iterating over Maps
        // =======================
        /*
        Map<String, Integer> ages = new HashMap<>();
        ages.put("Alice", 21);
        ages.put("Bob", 30);
        ages.put("Charlie", 25);

        // --- keySet() iteration ---
        System.out.println("\nIterate using keySet():");
        for (String name : ages.keySet()) {
            System.out.println(name + " is " + ages.get(name));
        }

        // --- entrySet() iteration ---
        System.out.println("\nIterate using entrySet():");
        for (Map.Entry<String, Integer> entry : ages.entrySet()) {
            System.out.println(entry.getKey() + " is " + entry.getValue());
        }

        // --- forEach lambda ---
        System.out.println("\nIterate using forEach():");
        ages.forEach((name, age) -> {
            System.out.println(name + " is " + age);
        });

        // --- values() iteration ---
        System.out.println("\nIterate values():");
        for (int age : ages.values()) {
            System.out.println("Age: " + age);
        }

        // --- keySet() only ---
        System.out.println("\nIterate keys():");
        for (String name : ages.keySet()) {
            System.out.println("Name: " + name);
        }

        // --- Stream-based iteration ---
        System.out.println("\nIterate using streams:");
        ages.entrySet().stream()
                .filter(e -> e.getValue() > 22)
                .forEach(e -> System.out.println(e.getKey() + " is older than 22"));
         */

        // =======================
        // Milestone 7: HashMap vs LinkedHashMap vs TreeMap
        // =======================
        /*
        System.out.println("\n--- HashMap Order ---");

        Map<String, Integer> hMap = new HashMap<>();
        hMap.put("banana", 1);
        hMap.put("apple", 2);
        hMap.put("cherry", 3);

        System.out.println(hMap);

        System.out.println("\n--- LinkedHashMap Order ---");

        Map<String, Integer> lhMap = new LinkedHashMap<>();
        lhMap.put("banana", 1);
        lhMap.put("apple", 2);
        lhMap.put("cherry", 3);

        System.out.println(lhMap);

        System.out.println("\n--- TreeMap Order (Sorted) ---");

        Map<String, Integer> tMap = new TreeMap<>();
        tMap.put("banana", 1);
        tMap.put("apple", 2);
        tMap.put("cherry", 3);

        System.out.println(tMap);

        System.out.println("\n--- TreeMap Range Operations ---");

        TreeMap<Integer, String> grades = new TreeMap<>();
        grades.put(90, "A");
        grades.put(70, "C");
        grades.put(80, "B");
        grades.put(60, "D");

        // Keys auto-sorted: 60,70,80,90

        System.out.println("All: " + grades);
        System.out.println("HeadMap(<80): " + grades.headMap(80)); // <80
        System.out.println("TailMap(80+): " + grades.tailMap(80)); // >=80
        System.out.println("SubMap(70 to 90): " + grades.subMap(70, 90)); // 70..89
        */

        // =======================
        // Milestone 8: CRUD with Map
        // =======================
        /*
        Map<Integer, User> userDb = new HashMap<>();

        System.out.println("\n--- CREATE ---");

        userDb.put(1, new User(1, "Alice"));
        userDb.put(2, new User(2, "Bob"));
        userDb.put(3, new User(3, "Charlie"));

        System.out.println(userDb);

        System.out.println("\n--- READ Existing ---");
        User u1 = userDb.get(1);
        System.out.println("Found: " + u1);

        System.out.println("\n--- READ Missing (safe) ---");
        User missing = userDb.getOrDefault(99, null);
        System.out.println("Result: " + missing);


        System.out.println("\n--- UPDATE ---");

        // Update Bob's name
        User bob = userDb.get(2);
        if (bob != null) {
            bob.name = "Bobby";
        }

        System.out.println(userDb);

        System.out.println("\n--- DELETE ---");

        userDb.remove(3); // remove Charlie

        System.out.println(userDb);

        System.out.println("\n--- SAFE DELETE (conditional) ---");

        userDb.remove(1, new User(1, "Alice")); // will NOT remove (different instances)
        System.out.println("After wrong delete attempt: " + userDb);

        // Correct remove:
        userDb.remove(1); // actual removal
        System.out.println("After correct delete: " + userDb);

        System.out.println("\n--- LIST USERS ---");

        for (User user : userDb.values()) {
            System.out.println(user);
        }
        */

        // =======================
        // Milestone 9: Library System
        // =======================

        // Books: id -> Book
        Map<Integer, Book> bookDb = new HashMap<>();

        // Users: id -> User2
        Map<Integer, User2> userDb2 = new HashMap<>();

        // Borrowed books: userId -> List of borrowed Book IDs
        Map<Integer, List<Integer>> borrowed = new HashMap<>();

        // --- Seed Data ---
        userDb2.put(1, new User2(1, "Alice"));
        userDb2.put(2, new User2(2, "Bob"));

        bookDb.put(101, new Book(101, "The Hobbit"));
        bookDb.put(102, new Book(102, "1984"));
        bookDb.put(103, new Book(103, "Dune"));

        System.out.println("\n--- Borrow Operation ---");

        int userId = 1;
        int bookId = 101;

        Book book = bookDb.get(bookId);
        if (book != null && !book.isBorrowed) {
            book.isBorrowed = true;
            borrowed.computeIfAbsent(userId, k -> new ArrayList<>()).add(bookId);
            System.out.println("User " + userId + " borrowed book " + bookId);
        } else {
            System.out.println("Book not available.");
        }

        System.out.println("\n--- Borrow Same Book Again ---");

        if (book != null && !book.isBorrowed) {
            book.isBorrowed = true;
            borrowed.computeIfAbsent(userId, k -> new ArrayList<>()).add(bookId);
        } else {
            System.out.println("Book 101 is ALREADY borrowed.");
        }

        System.out.println("\n--- Return Operation ---");

        if (borrowed.containsKey(userId) && borrowed.get(userId).contains(bookId)) {
            book.isBorrowed = false;
            borrowed.get(userId).remove((Integer) bookId);
            System.out.println("User " + userId + " returned book " + bookId);
        } else {
            System.out.println("User never borrowed this book.");
        }

        System.out.println("\n--- List Borrowed Books ---");

        List<Integer> userBooks = borrowed.getOrDefault(userId, List.of());
        System.out.println("User " + userId + " has borrowed: " + userBooks);

        System.out.println("\n--- List All Books ---");

        for (Book b : bookDb.values()) {
            System.out.println(b);
        }

        System.out.println("\n--- Borrow History Log ---");

        Map<Integer, Integer> borrowCount = new HashMap<>();

        // Count every borrow attempt
        borrowCount.merge(userId, 1, Integer::sum);
        borrowCount.merge(userId, 1, Integer::sum);
        borrowCount.merge(2, 1, Integer::sum);

        System.out.println("Borrow history counts: " + borrowCount);








    }
}
