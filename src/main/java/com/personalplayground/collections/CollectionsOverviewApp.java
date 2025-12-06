package com.personalplayground.collections;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.TimeUnit;


public class CollectionsOverviewApp {
    // Simple helper type for grouping examples
    private static class Person {
        private final String name;
        private final int age;
        private final String city;

        Person(String name, int age, String city) {
            this.name = name;
            this.age = age;
            this.city = city;
        }

        String getName() {
            return name;
        }

        int getAge() {
            return age;
        }

        String getCity() {
            return city;
        }

        @Override
        public String toString() {
            return "Person{name='" + name + "', age=" + age + ", city='" + city + "'}";
        }
    }

    // Helper class WITHOUT equals/hashCode overridden
    private static class Customer {
        private final String id;
        private final String name;

        Customer(String id, String name) {
            this.id = id;
            this.name = name;
        }

        String getId() {
            return id;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Customer{id='" + id + "', name='" + name + "'}";
        }
    }

    // Helper class WITH equals/hashCode based on id
    private static class CustomerWithId {
        private final String id;
        private final String name;

        CustomerWithId(String id, String name) {
            this.id = id;
            this.name = name;
        }

        String getId() {
            return id;
        }

        String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "CustomerWithId{id='" + id + "', name='" + name + "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;                    // same object
            if (o == null || getClass() != o.getClass()) return false;
            CustomerWithId that = (CustomerWithId) o;
            // define equality by id only
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            // must use the same field(s) as equals
            return Objects.hash(id);
        }
    }

    // Helper class with MUTABLE field used in equals/hashCode
    private static class MutableKey {
        private String code;

        MutableKey(String code) {
            this.code = code;
        }

        void setCode(String code) {
            this.code = code;
        }

        String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return "MutableKey{code='" + code + "'}";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof MutableKey)) return false;
            MutableKey that = (MutableKey) o;
            return Objects.equals(code, that.code);
        }

        @Override
        public int hashCode() {
            return Objects.hash(code);
        }
    }


    public static void main(String[] args) {
        System.out.println("=== Java Collections Lab ===");
        System.out.println("This project will help me practice Lists, Sets, Maps, Streams, and more.");

        System.out.println("\n--- Demo 1: Basic List with ArrayList ---");
        demoBasicArrayList();

        System.out.println("\n--- Demo 2: ArrayList vs LinkedList (concept) ---");
        demoArrayListVsLinkedList();

        System.out.println("\n--- Demo 3: Different ways to iterate a List ---");
        demoListIteration();

        System.out.println("\n--- Demo 4: Basic Set with HashSet (uniqueness) ---");
        demoBasicHashSet();  // NEW

        System.out.println("\n--- Demo 5: LinkedHashSet vs TreeSet (ordering) ---");
        demoLinkedHashSetAndTreeSet();  // NEW

        System.out.println("\n--- Demo 6: Basic Map with HashMap (username → score) ---");
        demoBasicHashMap();   // NEW

        System.out.println("\n--- Demo 7: LinkedHashMap vs TreeMap (key ordering) ---");
        demoLinkedHashMapAndTreeMap();  // NEW

        System.out.println("\n--- Demo 8: Immutable collections & factories ---");
        demoImmutableCollections();

        System.out.println("\n--- Demo 9: Collections & Arrays utility methods ---");
        demoCollectionUtilities();    // NEW

        System.out.println("\n--- Demo 10: Streams over Collections (basic pipelines) ---");
        demoStreamsOverCollections();   // NEW

        System.out.println("\n--- Demo 11: Advanced collectors (grouping & stats) ---");
        demoAdvancedCollectors();   // NEW

        System.out.println("\n--- Demo 12: equals() & hashCode() with Sets and Maps ---");
        demoEqualsAndHashCode();   // NEW

        System.out.println("\n--- Demo 13: Performance & design best practices ---");
        demoPerformanceAndDesignTips();   // NEW

        int v = binarySearch(new int[]{1,2,3,4,5,6,7,8,9,10}, 1);
        System.out.println("target is: " + v);

    }

    private static int binarySearch(int[] arr, int target){
            int low = 0;
            int high = arr.length - 1;

            while (low <= high) {
                int mid = (low + high) / 2;

                if (arr[mid] == target) {
                    return mid; // found
                } else if (arr[mid] < target) {
                    low = mid + 1;   // search right half
                } else {
                    high = mid - 1;  // search left half
                }
            }

            return -1; // not found
    }

    private static void demoPerformanceAndDesignTips() {
        System.out.println("\n[Performance & design best practices]");

        demoListVsSetLookupPerformance();
        demoHashMapVsTreeMapLookupPerformance();
        demoDesignApiExample();
    }

    private static void demoDesignApiExample() {
        System.out.println("\n>>> demoDesignApiExample() – returning collections safely");

        // Imagine this is some internal list of tags in a class
        List<String> internalTags = new ArrayList<>();
        internalTags.add("java");
        internalTags.add("collections");
        internalTags.add("streams");

        // BAD: returning the internal list directly
        List<String> exposedList = internalTags; // pretend this is a getter result
        System.out.println("internalTags before external modification: " + internalTags);

        exposedList.add("BAD-MUTATION");
        System.out.println("internalTags after external modification via exposedList: " + internalTags);

        // BETTER: return an UNMODIFIABLE VIEW or an IMMUTABLE COPY

        // 1) Unmodifiable view – reflects internal changes, but caller can't modify through the view
        List<String> unmodifiableView = Collections.unmodifiableList(internalTags);

        // 2) Immutable copy – snapshot that never changes, even if internal changes
        List<String> immutableCopy = List.copyOf(internalTags);

        System.out.println("\nunmodifiableView: " + unmodifiableView);
        System.out.println("immutableCopy:    " + immutableCopy);

        // Try modifying via unmodifiableView – will throw exception
        try {
            System.out.println("Trying unmodifiableView.add(\"oops\") ...");
            unmodifiableView.add("oops");
        } catch (UnsupportedOperationException ex) {
            System.out.println("  -> Caught: " + ex);
        }

        // Simulate internal change later
        internalTags.add("new-internal-tag");
        System.out.println("\nAfter internalTags.add(\"new-internal-tag\"):");
        System.out.println("internalTags:     " + internalTags);
        System.out.println("unmodifiableView (reflects internal): " + unmodifiableView);
        System.out.println("immutableCopy (still snapshot):       " + immutableCopy);

        // DESIGN GUIDELINES (as comments):
        // - For APIs (public methods):
        //   * Prefer returning immutable or unmodifiable collections.
        //   * Avoid returning direct references to internal mutable collections.
        // - Very typical pattern:
        //     private final List<String> tags = new ArrayList<>();
        //
        //     public List<String> getTags() {
        //         return Collections.unmodifiableList(tags);
        //     }
        //
        // - This protects your internal state from accidental external modification.
    }

    private static void demoHashMapVsTreeMapLookupPerformance() {
        System.out.println("\n>>> demoHashMapVsTreeMapLookupPerformance() – HashMap vs TreeMap get()");

        int size = 50_000;
        Map<Integer, String> hashMap = new HashMap<>();
        Map<Integer, String> treeMap = new TreeMap<>();

        for (int i = 0; i < size; i++) {
            String value = "Value-" + i;
            hashMap.put(i, value);
            treeMap.put(i, value);
        }

        int targetKey = size - 1; // existing key

        // Warm-up
        for (int i = 0; i < 1_000; i++) {
            hashMap.get(targetKey);
            treeMap.get(targetKey);
        }

        long startHash = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            hashMap.get(targetKey);
        }
        long endHash = System.nanoTime();

        long startTree = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            treeMap.get(targetKey);
        }
        long endTree = System.nanoTime();

        long hashDuration = endHash - startHash;
        long treeDuration = endTree - startTree;

        System.out.println("HashMap.get (10,000 lookups) took: " + hashDuration + " ns");
        System.out.println("TreeMap.get (10,000 lookups) took: " + treeDuration + " ns");

        if (treeDuration > 0) {
            double ratio = (double) treeDuration / hashDuration;
            System.out.printf("Approx ratio (TreeMap / HashMap): %.2f%n", ratio);
        }

        // THEORY:
        // - HashMap.get: O(1) average (hash bucket lookup).
        // - TreeMap.get: O(log n) (tree traversal).
        // TreeMap is slower but gives you sorted keys and range operations.
    }

    private static void demoListVsSetLookupPerformance() {
        System.out.println("\n>>> demoListVsSetLookupPerformance() – List.contains vs Set.contains");

        int size = 50_000; // you can tweak this
        List<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < size; i++) {
            list.add(i);
            set.add(i);
        }

        int target = size - 1; // element that DOES exist

        // Warm-up loop (helps avoid some first-call overhead)
        for (int i = 0; i < 1_000; i++) {
            list.contains(target);
            set.contains(target);
        }

        long startList = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            list.contains(target);
        }
        long endList = System.nanoTime();

        long startSet = System.nanoTime();
        for (int i = 0; i < 10_000; i++) {
            set.contains(target);
        }
        long endSet = System.nanoTime();

        long listDurationNanos = endList - startList;
        long setDurationNanos = endSet - startSet;

        System.out.println("List.contains (10,000 lookups) took   : " + listDurationNanos + " ns");
        System.out.println("Set.contains  (10,000 lookups) took   : " + setDurationNanos + " ns");

        if (setDurationNanos > 0) {
            double ratio = (double) listDurationNanos / setDurationNanos;
            System.out.printf("Approx ratio (List / Set): %.2f%n", ratio);
        }

        // THEORY:
        // - List.contains: O(n) – scans elements one by one.
        // - HashSet.contains: O(1) on average – jumps near the bucket via hashCode.
    }

    private static void demoEqualsAndHashCode() {
        System.out.println("\n[equals() & hashCode() impact on Sets and Maps]");

        demoWithoutEqualsHashCode();
        demoWithEqualsHashCode();
        demoMutableKeyProblem();
    }

    private static void demoMutableKeyProblem() {
        System.out.println("\n>>> demoMutableKeyProblem() – why mutable keys are dangerous");

        MutableKey key = new MutableKey("ABC");
        Map<MutableKey, String> map = new HashMap<>();

        map.put(key, "Initial value");

        System.out.println("Map after put: " + map);
        System.out.println("Contains key before mutation? " + map.containsKey(key));

        // Now mutate the key's code AFTER inserting into the map
        key.setCode("XYZ");

        System.out.println("Key after mutation: " + key);
        System.out.println("Contains key after mutation? " + map.containsKey(key));
        System.out.println("Getting value with mutated key: " + map.get(key));

        // In many cases:
        // - containsKey(key) may now return false
        // - get(key) may return null
        // because the key's hashCode/equality changed after insertion.
        //
        // This is why keys (and Set elements) should be IMMUTABLE or at least
        // fields used in equals/hashCode should never change once inserted.
    }

    private static void demoWithEqualsHashCode() {
        System.out.println("\n>>> demoWithEqualsHashCode() – using CustomerWithId (with equals/hashCode)");

        CustomerWithId c1 = new CustomerWithId("C999", "Bob");
        CustomerWithId c2 = new CustomerWithId("C999", "Bob"); // same logical id

        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c1 == c2 ? " + (c1 == c2 ));
        System.out.println("c1.equals(c2)? " + c1.equals(c2));

        // 1) HashSet with proper equals/hashCode
        Set<CustomerWithId> customerSet = new HashSet<>();
        customerSet.add(c1);
        customerSet.add(c2); // should be considered duplicate

        System.out.println("HashSet with CustomerWithId (proper equals/hashCode):");
        System.out.println("  Set size = " + customerSet.size());
        System.out.println("  Contents: " + customerSet);

        // 2) HashMap with proper equals/hashCode
        Map<CustomerWithId, Integer> customerPoints = new HashMap<>();
        customerPoints.put(c1, 100);
        customerPoints.put(c2, 200); // should overwrite the same key

        System.out.println("HashMap with CustomerWithId (proper equals/hashCode):");
        System.out.println("  Map size = " + customerPoints.size());
        System.out.println("  Entries: = " + customerPoints);
        System.out.println("  Point for c1 key: " + customerPoints.get(c1));
        System.out.println("  Point for c2 key: " + customerPoints.get(c2));

        // Now:
        // - Set size will be 1 (duplicates merged).
        // - Map size will be 1, last put wins (value 200).
    }

    private static void demoWithoutEqualsHashCode() {
        System.out.println("\n>>> demoWithoutEqualsHashCode() – using Customer (NO equals/hashCode)");

        Customer c1 = new Customer("C123", "Alice");
        Customer c2 = new Customer("C123", "Alice"); // logically the same data

        System.out.println("c1: " + c1);
        System.out.println("c2: " + c2);
        System.out.println("c1 == c2 ? " + (c1 == c2 ));
        System.out.println("c1.equals(c2)? " + c1.equals(c2));

        // 1) HashSet - should behave list a "set of unique customers"
        Set<Customer> customerSet = new HashSet<>();
        customerSet.add(c1);
        customerSet.add(c2); // we might expect this to be ignored

        System.out.println("HashSet with Customer (no equals/hashCode):");
        System.out.println("  Set size = " + customerSet.size());
        System.out.println("  Contents: " + customerSet);

        // 2) HashMap - using Customer as key
        Map<Customer, Integer> customerPoints = new HashMap<>();
        customerPoints.put(c1, 100);
        customerPoints.put(c2, 200); // we might expect this to "update" same customer

        System.out.print("HashMap with Customer (no equals/hashCode:");
        System.out.println("  Map size = " + customerPoints.size());
        System.out.println("  Entries: = " + customerPoints);
        System.out.println("  Point for c1 key: " + customerPoints.get(c1));
        System.out.println("  Point for c2 key: " + customerPoints.get(c2));

        // Even though c1 and c2 look the same logically, the Set treats them as different,
        // and the Map treats them as separate keys.
    }

    private static void demoAdvancedCollectors() {
        System.out.println("\n[Advanced collectors: grouping, counting, joining, summarizing]");

        List<Person> people = createSamplePeopleForCollectors();

        System.out.println("People:");
        people.forEach(p-> System.out.println(p));

        demoGroupingByCity(people);
        demoCountByCity(people);
        demoJoinedNamesPerCity(people);
        demoAgeStatistics(people);
        demoPartitionAdults(people);
    }

    private static void demoPartitionAdults(List<Person> people) {
        System.out.println("\n>>> Partition into adults vs minors (partitioningBy)");

        Map<Boolean, List<Person>> partitioned = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));

        System.out.println("partitioned: " + partitioned);

        List<Person> adults = partitioned.get(true);
        List<Person> minors = partitioned.get(false);

        System.out.println("Adults:");
        adults.forEach(p -> System.out.println(" - " + p.getName() + " (" + p.getAge() + ")"));

        System.out.println("Minors:");
        minors.forEach(p -> System.out.println(" - " + p.getName() + " (" + p.getAge() + ")"));
    }

    private static void demoAgeStatistics(List<Person> people) {
        System.out.println("\n>>> Age statistics (summarizingInt)");

        IntSummaryStatistics ageStats = people.stream()
                .collect(Collectors.summarizingInt(p -> p.getAge()));

        System.out.println("Count = " + ageStats.getCount());
        System.out.println("Min   = " + ageStats.getMin());
        System.out.println("Max   = " + ageStats.getMax());
        System.out.println("Sum   = " + ageStats.getSum());
        System.out.println("Avg   = " + ageStats.getAverage());
    }

    private static void demoJoinedNamesPerCity(List<Person> people) {
        System.out.println("\n>>> Names per city (groupingBy + mapping + joining)");

        Map<String, String> namesByCity = people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCity(),
                        Collectors.mapping(
                                p -> p.getName(),
                                Collectors.joining(", ")
                        )
                ));

        namesByCity.forEach((city, names) ->
                System.out.println(city + " -> " + names)
        );

        // TYPE: Map<String, String>
        // Example value: "Manila -> Alice, Charlie"
    }

    private static void demoCountByCity(List<Person> people) {
        System.out.println("\n>>> Counting people per city (groupingBy + counting)");

        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCity(),
                        Collectors.counting()
                ));

        countByCity.forEach((city, count) ->
                System.out.println(city + " -> " + count)
        );

        // TYPE: Map<String, Long>
        // VALUE is now the count, not a list.
    }

    private static void demoGroupingByCity(List<Person> people) {
        System.out.println("\n>>> Grouping people by city (groupingBy)");

        Map<String, List<Person>> peopleByCity = people.stream()
                .collect(Collectors.groupingBy(
                        p -> p.getCity(),
                        Collectors.toList()
                ));

        // Pretty-print the result
        peopleByCity.forEach((city, personsInCity) -> {
            System.out.println("-" + city + ":");
            personsInCity.forEach(p ->
                    System.out.println("  " + p.getName()  +" (" + p.getAge() + ")")
            );
        });

        // TYPE: Map<String, List<Person>>
        // KEY   = city (String)
        // VALUE = list of people in that city
    }

    private static List<Person> createSamplePeopleForCollectors() {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 17, "Manila"));
        people.add(new Person("Bob", 25, "Cebu"));
        people.add(new Person("Charlie", 30, "Manila"));
        people.add(new Person("Diana", 22, "Davao"));
        people.add(new Person("Eve", 16, "Cebu"));
        people.add(new Person("Frank", 40, "Cebu"));
        return people;
    }

    private static void demoStreamsOverCollections() {
        System.out.println("\n[Streams over Collections – basic pipelines]");

        demoStreamOnList();
        demoStreamOnSet();
        demoStreamOnMap();
    }

    private static void demoStreamOnMap() {
        System.out.println("\n>>> demoStreamOnMap()");

        Map<String, Integer> scores = new HashMap<>();
        scores.put("alice", 1200);
        scores.put("bob", 900);
        scores.put("charlie", 1500);
        scores.put("diana", 1100);

        System.out.println("Original scores map: " + scores);

        // 1) Get a sorted List of usernames by name
        List<String> sortedUsernames = scores.keySet().stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted usernames (from keySet stream): " + sortedUsernames);

        // 2) Get users with score >= 1100, sorted by score descending
        List<String> topPlayers = scores.entrySet().stream()
                .filter(entry -> entry.getValue() >= 1100)
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Top players (score >= 1100, desc): " + topPlayers);

        // 3) Build a new Map: username -> "username (score)"
        Map<String, String> labelMap = scores.entrySet().stream()
                .collect(Collectors.toMap(
                        e -> e.getKey(),
                        e -> e.getKey() + " (" + e.getValue() +")"
                ));

        System.out.println("Label map (username -> 'username (score)'): " + labelMap);

        // 4) Find the first user with score > 1000 (using stream + findFirst)
        //    We sort first to make "first" deterministic.
        scores.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey()))
                .filter(e -> e.getValue() > 1000)
                .findFirst()
                .ifPresent(e ->
                        System.out.println("First user (alphabetically) with score > 1000: "
                                + e.getKey() + " => " + e.getValue())
                );
    }

    private static void demoStreamOnSet() {
        System.out.println("\n>>> demoStreamOnSet()");

        Set<String> tags = new HashSet<>();
        tags.add("java");
        tags.add("colections");
        tags.add("streams");
        tags.add("maps");
        tags.add("java"); //duplicate ignored by Set

        System.out.println("Original tag set: " + tags);

        // Turn Set into sorted List using streams
        List<String> sortedTags = tags.stream()
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Sorted tags (List from Set stream): " + sortedTags);

        // Filter tags that contains the Letter 'a' and join them into a single String
        String joinedTagsWithA = tags.stream()
                .filter(tag -> tag.contains("a"))
                .sorted()
                .collect(Collectors.joining(", "));

        System.out.println("Tags containing 'a', joined: " + joinedTagsWithA);
    }

    private static void demoStreamOnList() {
        System.out.println("\n>>> demoStreamOnList()");

        // Example 1: numbers – filter even, square, sort, collect
        List<Integer> numbers = Arrays.asList(5, 1, 9, 3, 4, 6);

        System.out.println("Original numbers: " + numbers);

        List<Integer> processed = numbers.stream()
                .filter(n -> n % 2 == 0 )
                .map(n -> n * n)
                .sorted()
                .collect(Collectors.toList());

        System.out.println("Even squares sorted (stream result): " + processed);

        // Example 2: words – filter, map to uppercase, collect to List & Set
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana");

        System.out.println("\nOriginal words: " + words);

        List<String> upperWords = words.stream()
                .map(w -> w.toUpperCase())
                .collect(Collectors.toList());

        Set<String> distinctWords = words.stream()
                .map(w -> w.toUpperCase())
                .collect(Collectors.toSet());

        System.out.println("Uppercase words (List, duplicates kept):   " + upperWords);
        System.out.println("Uppercase words (Set, distinct only):      " + distinctWords);

        long countBanana = words.stream()
                .filter(w -> w.equals("banana"))
                .count();                                   // TERMINAL: counts elements

        System.out.println("Count of 'banana' using stream: " + countBanana);
    }

    private static void demoCollectionUtilities() {
        System.out.println("\n[Collections & Array utility methods]");

        // 1) Sorting a List
        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(1);
        numbers.add(9);
        numbers.add(3);

        System.out.println("Original numbers list: " + numbers);

        Collections.sort(numbers); // sort ascending
        System.out.println("After Collection.sort (ascending): " + numbers);

        // 2) Reverse and shuffle
        Collections.reverse(numbers); // sort ascending
        System.out.println("After Collection.reverse: " + numbers);

        Collections.shuffle(numbers); // random order
        System.out.println("After Collections.shuffle (random): " + numbers);

        // 3) max, min, frequency
        // Let's work with a list that has duplicates
        List<String> words = new ArrayList<>();
        words.add("apple");
        words.add("banana");
        words.add("apple");
        words.add("orange");
        words.add("banana");
        words.add("banana");

        System.out.println("\nWords list: " + words);

        String maxWords = Collections.max(words); // lexicographically largest
        String minWords = Collections.min(words); // lexicographically smallest

        int bananaCount = Collections.frequency(words, "banana");
        int appleCount = Collections.frequency(words, "apple");

        System.out.println("Max (lexicographically): " + maxWords);
        System.out.println("Min (lexicographically): " + minWords);
        System.out.println("Frequency of 'banana': " + bananaCount);
        System.out.println("Frequency of 'apple': " + appleCount);

        // 4) addAll - bulk adding elements
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");

        List<String> list2 = new ArrayList<>();
        list2.add("C");
        list2.add("D");

        System.out.println("\nlist1 before addAll: " + list1);
        System.out.println("list2: "               + list2);

        Collections.addAll(list1, "X", "Y"); // add multiple individual elements
        System.out.println("list1 after addAll(list1, \"X\", \"Y\": " + list1);

        list1.addAll(list2); // add all elements from list2
        System.out.println("list1 after list1.addAll(list2): " + list1);

        // 5) copy and fill (for List of fixed size)
        // NOTE: Collections.copy requires that destination list has AT LEAST the same size.
        List<String> source = Arrays.asList("one", "two", "three");
        List<String> dest = new ArrayList<>(Arrays.asList("?", "?", "?", "?"));

        dest.add("?");

        System.out.println("\nsource: " + source);
        System.out.println("dest before copy: " + dest);

        Collections.copy(dest, source); // copy source into dest
        System.out.println("dest after Collections.copy(dest, source): " + dest);

        // fill: replace ALL elements with the same value
        Collections.fill(dest, "filled");
        System.out.println("dest after Collections.fill(dest, \"filled\": " + dest);

        // 6) Arrays utility methods (for raw arrays)
        int[] numArray = {5, 1, 9, 3};
        System.out.println("\nRaw array before sort: " + Arrays.toString(numArray));

        Arrays.sort(numArray); // sort in-place
        System.out.println("Raw array after Arrays.sort: " + Arrays.toString(numArray));

        int indexOf3 = Arrays.binarySearch(numArray, 3);
        System.out.println("Index of 3 (using Arrays.binarySearch): " + indexOf3);

        // NOTE:
        // - binarySearch requires the array to be sorted first.
        // - If element is not found, it returns a negative value.

    }

    private static void demoImmutableCollections() {
        System.out.println("\n[Immutable Collections & Factories]");

        // 1) Immutable List using List.of(...)
        List<String> immutableList = List.of("A", "B", "C");
        System.out.println("immutableList: " + immutableList);

        // Trying to modify it will throw UnsupportedOperationException at runtime
        // We'll show it in a try/catch so the program doesn't crash.
        try {
            System.out.println("Trying immutableList.add(\"D\") ...");
            immutableList.add("D"); // will fail
        } catch (UnsupportedOperationException ex) {
            System.out.println(" -> Caught exception: " + ex);
        }

        // 2) Immutable Set using Set.of(...)
        // Note: Set.of(...) does NOT allow duplicates; it will throw at creation time.
        try {
            Set<String> immutableSet = Set.of("X", "Y", "Z");
            System.out.println("immutableSet: " + immutableSet);

            System.out.println("Trying immutableSet.add(\"X\") ...");
            immutableSet.add("X"); // will fail
        } catch (UnsupportedOperationException ex) {
            System.out.println(" -> Caught exception (Set): " + ex);
        }

        // Uncommenting this would throw an IllegalArgumentException:
        // Set<String> badSet = Set.of("X", "X"); // duplicate not allowed

        // 3) Immutable Map using Map.of(...)
        Map<String, Integer> immutableMap = Map.of(
                "apple", 2,
                "banana", 5,
                "orange", 3
        );
        System.out.println("immutableMap: " + immutableMap);

        try {
            System.out.println("Trying immutableMap.put(\"kiwi\", 1) ...");
            immutableMap.put("kiwi", 1); // will fail
        } catch (UnsupportedOperationException ex) {
            System.out.println(" -> Caught exception (Map): " + ex);
        }

        // 4) copyOf(...) - creating an immutable copy of a mutable collection
        List<String> mutableList = new ArrayList<>();
        mutableList.add("original1");
        mutableList.add("original2");

        List<String> immutableCopy = List.copyOf(mutableList); // immutable snapshot
        System.out.println("\nmutableList:   " + mutableList);
        System.out.println("immutableCopy: " + immutableCopy);

        // Change the original
        mutableList.add("original3");
        System.out.println("After mutableList.add(\"original3\"):");
        System.out.println("mutableList:   " + mutableList);
        System.out.println("immutableCopy (unchanged): " + immutableCopy);

        // 5) Unmodifiable view vs immutable copy
        List<String> base = new ArrayList<>();
        base.add("base1");
        base.add("base2");

        List<String> unmodifiableView = Collections.unmodifiableList(base);
        List<String> immutableCopy2 = List.copyOf(base);

        System.out.println("\nbase:             " + base);
        System.out.println("unmodifiableView: " + unmodifiableView);
        System.out.println("immutableCopy2:   " + immutableCopy2);

        // Modify the base list
        base.add("base3");
        System.out.println("After base.add(\"base3\"):");
        System.out.println("base:             " + base);
        System.out.println("unmodifiableView (reflects base): " + unmodifiableView);
        System.out.println("immutableCopy2 (does NOT change): " + immutableCopy2);

        // Try modifying via the unmodifiableView
        try {
            System.out.println("Trying unmodifiableView.add(\"oops\") ...");
            unmodifiableView.add("oops");  // will fail
        } catch (UnsupportedOperationException ex) {
            System.out.println("  -> Caught exception (unmodifiableView): " + ex);
        }

        // SUMMARY (in comments):
        // - List.of / Set.of / Map.of:
        //   * Create truly immutable collections (no add/remove/put allowed).
        //   * Often used for constants or read-only config data.
        //
        // - copyOf(...):
        //   * Takes a snapshot of an existing collection and wraps it as immutable.
        //   * Future changes to the original DO NOT affect the copy.
        //
        // - Collections.unmodifiableList(...):
        //   * Creates a read-only VIEW of the original collection.
        //   * You cannot modify it via the view, but if the original changes,
        //     the view reflects those changes.

    }

    private static void demoLinkedHashMapAndTreeMap() {
        // We'll use three map types to compare behavior
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        Map<String, Integer> treeMap = new TreeMap<>();

        // Same insert order for all
        String[] fruits = {"banana", "apple", "orange", "kiwi"};
        int[] fruitQuantity = {2, 5, 3, 1};

        for (int i = 0; i < fruits.length; i++) {
            hashMap.put(fruits[i], fruitQuantity[i]);
            linkedHashMap.put(fruits[i], fruitQuantity[i]);
            treeMap.put(fruits[i],fruitQuantity[i]);
        }

        System.out.println("Insertion order of keys: [banana, apple, orange, kiwi]");

        System.out.println("HashMap (no guaranteed order):       " + hashMap);
        System.out.println("LinkedHashMap (insertion order):     " + linkedHashMap);
        System.out.println("TreeMap (sorted by key, ascending):  " + treeMap);

        // NOTES:
        // - All three have the same key–value pairs.
        //
        // - HashMap:
        //   * Fastest general-purpose implementation.
        //   * Does NOT guarantee any iteration order.
        //
        // - LinkedHashMap:
        //   * Remembers insertion order of keys.
        //   * Useful when you want predictable order without sorting.
        //
        // - TreeMap:
        //   * Stores entries sorted by key (natural ordering by default).
        //   * Operations are O(log n).
    }

    private static void demoBasicHashMap() {
        // 1) Create a Map from username (String) to score (Integer)
        Map<String, Integer> scores = new HashMap<>();

        // 2) Put some key-value pairs (like a small scoreboard)
        scores.put("alice", null);
        scores.put("bob", 900);
        scores.put("charlie", 1500);

        // 3) Print the whole map
        System.out.println("Scores map: " + scores);

        // 4) Get a value by key
        Integer aliceScore = scores.get("alice");
        System.out.println("Score for 'alice': " + aliceScore);

        // 5) What happens if the key does NOT exist?
        Integer daveScore = scores.get("dave"); // not in map
        System.out.println("Score for 'dave': " + daveScore); // will print: null

        // 6) Update a value (put on an existing key overwrites)
        scores.put("alice", 1300); // alice's score changes from 1200 to 1300
        System.out.println("After updating alice's score: " + scores);

        // 7) Check for key / value presence
        System.out.println("Contains key 'bob'?: " + scores.containsKey("bob"));
        System.out.println("Contains value 1500?: " + scores.containsValue(1500));

        // 8) Remove an entry by key
        scores.remove("bob");
        System.out.println("After removing 'bob': " + scores);

        // 9) Iterate over entries (key-value pairs)
        for (Map.Entry<String, Integer> entry : scores.entrySet() ) {
            String username = entry.getKey();
            Integer score = entry.getValue();
            System.out.println(" - " + username + " => " + score);
        }

        // NOTES:
        // - HashMap:
        //   * Keys are unique (here: username).
        //   * Very fast lookup by key on average (O(1)).
        //   * Order of entries is NOT guaranteed.

    }

    private static void demoLinkedHashSetAndTreeSet() {
        // We'll use the same values to compare behaviors
        Set<Integer> hashSet = new HashSet<>();
        Set<Integer> linkedHashSet = new LinkedHashSet<>();
        Set<Integer> treeSet = new TreeSet<>();

        int[] numbers = {5, 3, 9, 1, 5, 3, 7};

        for (int n : numbers) {
            hashSet.add(n);
            linkedHashSet.add(n);
            treeSet.add(n);
        }

        System.out.println("Original input order: [5, 3, 9, 1, 5, 3, 7]");

        System.out.println("HashSet (no guaranteed order):      " + hashSet);
        System.out.println("LinkedHashSet (insertion order):    " + linkedHashSet);
        System.out.println("TreeSet (sorted, ascending order):  " + treeSet);

        // NOTES:
        // - All three are Sets, so:
        //   * No duplicates
        //   * "contains" checks, add, remove
        //
        // - HashSet:
        //   * Fastest general-purpose set.
        //   * Order is NOT guaranteed.
        //
        // - LinkedHashSet:
        //   * Maintains insertion order.
        //   * Slightly more overhead than HashSet.
        //
        // - TreeSet:
        //   * Stores elements in sorted order (natural ordering by default).
        //   * Operations are O(log n) instead of O(1).
    }

    private static void demoBasicHashSet() {
        // 1) Create a Set using HashSet as implementation
        Set<String> fruits = new HashSet<>();

        // 2) Add elements (including duplicates on purpose)
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple"); // duplicate
        fruits.add("Banana"); // duplicate

        // 3) Print the whole set
        System.out.println("Fruits set: " + fruits);

        // 4) Show that duplicates are removed
        System.out.println("Number of element in set: " + fruits.size());

        // 5) Check for containment
        System.out.println("Contains 'Apple'? " + fruits.contains("Apple"));
        System.out.println("Contains 'Mango'? " + fruits.contains("Mango"));

        // 6) Show that you CANNOT access by index (no fruits.get(0))
        // The following line WOULD NOT COMPILE (so we keep it as a comment);
        // String fruits = fruits.get(0); // Set has no get(int index) method

        // 7) Iterate over the set (order is NOT guaranteed)
        System.out.println("Iterating over HashSet (order NOT guaranteed)");
        for (String fruit : fruits) {
            System.out.println(" - " + fruit);
        }

        // NOTES:
        // - HashSet:
        //   * Does NOT guarantee any particular order.
        //   * Very fast for add, remove, and contains on average (O(1)).
        //   * Automatically ignores duplicates (based on equals/hashCode).
    }

    private static void demoListIteration() {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println("Original list: " + names);

        // 1) Classic for loop (using index)
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i);
            System.out.println("Index " + i + ": " + name);
        }

        // 2) Enhanced for loop (for-each)
        System.out.println("\n2) Enhanced for loop (for-each):");
        for (String name : names) {
            System.out.println("Name: " + name);
        }

        // 3) Iterator (explicit)
        System.out.println("\n3) Iterator (manual control):");
        Iterator<String> it = names.iterator();
        while (it.hasNext()) {
            String name = it.next();
            System.out.println("Iterator next: " + name);
        }

        // 4) forEach with lambda / method reference
        System.out.println("\n4) forEach with lambda:");
        names.forEach(name -> System.out.println("Lambda: " + name));

        System.out.println("\n4b) forEach with method reference:");
        names.forEach(System.out::println);

        // NOTE: These are all different WAYS to read/traverse the SAME collection.
        // Use whichever is most readable for your situation.

    }

    private static void demoArrayListVsLinkedList() {
        // Both use the same interface: List<String>
        List<String> arrayList = new ArrayList<>();
        List<String> linkedList = new LinkedList<>();

        arrayList.add("A");
        arrayList.add("B");
        arrayList.add("C");

        linkedList.add("A");
        linkedList.add("B");
        linkedList.add("C");

        System.out.println("ArrayList contents:  " + arrayList);
        System.out.println("LinkedList contents: " + linkedList);

        // Explain the main conceptual difference in comments:
        // - ArrayList: backed by a dynamic array
        //   * Fast random access: get(index) is very fast.
        //   * Slower insert/remove in the MIDDLE of a big list.
        //
        // - LinkedList: chain of nodes (each holds value + next/prev references)
        //   * Fast insert/remove in the MIDDLE if you already have a reference/iterator.
        //   * Slower random access: to get index N, it walks from the start or end.
        //
        // In beginner-level normal code, ArrayList is usually the default choice.

    }

    private static void demoBasicArrayList() {
        // 1) Create a List using ArrayList as the implementation
        List<String> names = new ArrayList<>();

        // 2) Add some elements
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        // 3) Print the whole list
        System.out.println("Names list: " + names);

        // 4) Access by index
        String firstName = names.get(0);  // index starts at 0
        System.out.println("First name (index 0): " + firstName);

        // 5) Check size
        System.out.println("Number of elements: " + names.size());

        // 6) Remove an element
        names.remove("Bob");  // removes the "Bob" element if found

        // 7) Print the list again
        System.out.println("After removing 'Bob': " + names);
    }
}
