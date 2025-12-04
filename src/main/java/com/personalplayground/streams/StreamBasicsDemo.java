package com.personalplayground.streams;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamBasicsDemo {
    private static List<Person> createSamplePeople() {
        return Arrays.asList(
                new Person("Alice", 17, "Manila"),
                new Person("Bob", 25, "Cebu"),
                new Person("Charlie", 30, "Manila"),
                new Person("Diana", 22, "Davao"),
                new Person("Eve", 16, "Cebu"),
                new Person("Frank", 40, "Cebu")
        );
    }

    public static void main(String[] args) {
        System.out.println("=== Java Streams - Basic Demo ===");
        runFirstStreamDemo();
        demoStreamCreation();
        demoBasicOperations();
        demoPeopleStreams();
        demoCollectorsAndGrouping();
        demoFlatMapAndSearch();
    }

    private static void demoFlatMapAndSearch() {
        System.out.println("\n--- demoFlatMapAndSearch() ---");

        demoFlatMapWithHobbies();
        demoFindFirstWithOptional();
    }

    private static void demoFindFirstWithOptional() {
        System.out.println("\n>>> demoFindFirstWithOptional()");

        List<Person> people = createSamplePeople();

        System.out.println("All people:");
        people.forEach(System.out::println);

        // Find the first person from Cebu who is at least 18
        Optional<Person> firstAdultFromCebu = people.stream()
                .filter(p -> "Cebu".equals(p.getCity()))
                .filter(p -> p.getAge() >= 18)
                .findFirst();  // Terminal: Optional<Person>

        if (firstAdultFromCebu.isPresent()) {
            Person found = firstAdultFromCebu.get();
            System.out.println("\nFirst adult from Cebu: " + found.getName() +
                    " (" + found.getAge() + ")");
        } else {
            System.out.println("\nNo adult found from Cebu.");
        }

        // Cleaner way using ifPresent
        firstAdultFromCebu.ifPresent(p ->
                System.out.println("ifPresent -> Found (again): " + p.getName())
        );
    }


    private static void demoFlatMapWithHobbies() {
        System.out.println("\n>>> demoFlatMapWithHobbies()");

        List<PersonWithHobbies> people = Arrays.asList(
                new PersonWithHobbies("Alice", List.of(new String[]{"reading", "gaming"})),
                new PersonWithHobbies("Bob", Arrays.asList("gaming", "cooking")),
                new PersonWithHobbies("Charlie", Arrays.asList("sports", "reading"))
        );

        System.out.println("People with hobbies:");
        people.forEach(System.out::println);

        // 1) Using map: Stream<List<String>>
        Stream<List<String>> hobbiesListStream = people.stream()
                .map(PersonWithHobbies::getHobbies);   // PersonWithHobbies -> List<String>

        System.out.println("\nUsing map(PersonWithHobbies::getHobbies):");
        hobbiesListStream.forEach(list -> System.out.println("Hobbies list: " + list ));

        // 2) Using flatMap: Stream<String>
        List<String> allDistinctHobbies = people.stream()
                .flatMap(p -> p.getHobbies().stream())  // PersonWithHobbies -> Stream<String>, then flattened
                .distinct()
                .sorted()
                .toList();

        System.out.println("\nAll distinct hobbies (flatMap): " + allDistinctHobbies);
    }



    private static void demoCollectorsAndGrouping() {
        System.out.println("\n--- demoCollectorsAndGrouping() ---");

        List<Person> people = createSamplePeople();

        System.out.println("All people:");
        people.forEach(System.out::println);

        // 1) Group people by city: Map<String, List<Person>>
        Map<String, List<Person>> peopleByCity = people.stream()
                .collect(Collectors.groupingBy(Person::getCity,Collectors.toList()));

        System.out.println("peopleByCity "+peopleByCity);

        System.out.println("\nPeople grouped by city:");
        peopleByCity.forEach((city, personsInCity) -> {
            System.out.println("- " + city + ":");
            personsInCity.forEach(p -> System.out.println("    " + p.getName() + " (" + p.getAge() + ")"));
        });

        // 2) Count how many people per city: Map<String, Long>
        Map<String, Long> countByCity = people.stream()
                .collect(Collectors.groupingBy(
                        Person::getCity,
                        Collectors.counting()
                ));

        System.out.println("\nCount of people by city:");
        countByCity.forEach((city, count) ->
                System.out.println(city + " -> " + count)
        );

        // 3) Build a comma-separated string of adult names (age >= 18)
        String adultsJoined = people.stream()
                .filter(p -> p.getAge() >= 18)
                .map(Person::getName)
                .sorted()
                .collect(Collectors.joining(", "));   // TERMINAL: single String

        System.out.println("\nAdult names (joined): " + adultsJoined);

        // 4) Get age statistics: min, max, avg, sum, count
        IntSummaryStatistics ageStats = people.stream()
                .collect(Collectors.summarizingInt(Person::getAge));

        System.out.println("\nAge statistics:");
        System.out.println("Count = " + ageStats.getCount());
        System.out.println("Min   = " + ageStats.getMin());
        System.out.println("Max   = " + ageStats.getMax());
        System.out.println("Sum   = " + ageStats.getSum());
        System.out.println("Avg   = " + ageStats.getAverage());
    }
    
    private static void demoPeopleStreams() {
        System.out.println("\n--- demoPeopleStreams() ---");

        // 1) Create a small "database" of people
        List<Person> people = createSamplePeople();

        System.out.println("All people:");
        people.forEach(System.out::println);

        // 2) Example: Get names of adults (age >= 18), sorted
        List<String> adultNames = people.stream()                   // SOURCE: Stream<Person>
                .filter(p -> p.getAge() >= 18)                      // INTERMEDIATE: only adults
                .map(Person::getName)                               // INTERMEDIATE: Person -> String (name)
                .sorted()                                           // INTERMEDIATE: natural sort (alphabetical)
                .toList();                                          // TERMINAL: List<String>

        System.out.println("\nAdult names (sorted): " + adultNames);

        // 3) Example: Distinct cities where people live
        List<String> distinctCities = people.stream()
                .map(Person::getCity)                               // Person -> city name
                .distinct()                                         // remove duplicates
                .sorted()
                .toList();

        System.out.println("Distinct cities: " + distinctCities);

        // 4) Example: Any minors? All adults? None over 100?
        boolean anyMinor = people.stream()
                .anyMatch(p -> p.getAge() < 18);

        boolean allAdults = people.stream()
                .allMatch(p -> p.getAge() >= 18);

        boolean noneVeryOld = people.stream()
                .noneMatch(p -> p.getAge() > 100);

        System.out.println("Any minor? " + anyMinor);
        System.out.println("All adults? " + allAdults);
        System.out.println("No one older than 100? " + noneVeryOld);
    }

    private static void demoStreamCreation() {
        System.out.println("\n--- demoStreamCreation() ---");

        // 1) From a collection (List)
        List<String> namesList = new ArrayList<>();

        namesList.add("Alice");
        namesList.add("Bob");
        namesList.add("Charlie");

        Stream<String> fromList = namesList.stream();

        // 2) From an array
        String[] namesArray = {"Dave", "Eve", "Frank"};
        Stream<String> fromArray = Arrays.stream(namesArray);

        // 3) From values directly
        Stream<String> fromValues = Stream.of("X", "Y", "Z");

        // Simple terminal ops just to show they work:
        System.out.println("From List");
        fromList.forEach(name -> System.out.println("Name: " + name));

        System.out.println("From Array:");
        fromArray.forEach(System.out::println);

        System.out.println("From Values:");
        fromValues.forEach(System.out::println);

    }

    private static void demoBasicOperations() {
        System.out.println("\n--- demoBasicOperations() ---");

        // Example 1: Filter -> Map -> Distinct -> Sorted -> Collect
        List<String> names = Arrays.asList("Ana", "Bob", "David", "Charlie", "Ana");

        List<String> processedNames = names.stream()                     // SOURCE: List -> Stream<String>
                .filter(name -> name.length() > 3)                       // INTERMEDIATE: keep names longer than 3 letters
                .map(String::toUpperCase)                                // INTERMEDIATE: transform to upper case
                .distinct()                                              // INTERMEDIATE: remove duplicates
                .sorted()                                                // INTERMEDIATE: natural order
                .toList();                                               // TERMINAL: build a new List

        System.out.println("Original names:  " + names);
        System.out.println("Processed names: " + processedNames);

        // Example 2: Distinct -> Sorted -> Skip -> Limit -> Count
        List<Integer> numbers = Arrays.asList(5, 3, 1, 2, 2, 10, 4, 7);

        long count = numbers.stream()                                    // SOURCE
                .distinct()                                              // INTERMEDIATE: remove duplicates
                .sorted()                                                // INTERMEDIATE: sort ascending
                .skip(1)                                                 // INTERMEDIATE: skip first element
                .limit(3)                                                // INTERMEDIATE: take next 3
                .count();                                               // TERMINAL: how many elements left?

        System.out.println("Original numbers: " + numbers);
        System.out.println("Count after distinct/sorted/skip/limit = " + count);
    }

    private static void runFirstStreamDemo() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);

        // 1) Classic Loop: sum of even squares
        int loopSum = 0;
        for (int n : numbers) {
            if (n % 2 == 0) {           //keep even numbers
            int squared = n * 2;        // pretend "square": here just doubling for simplicity
                loopSum += squared;
            }
        }

        // 2) Stream pipeline: same logic
        int streamSum = numbers.stream()                // source: List -> Stream<Integer>
                .filter(n -> n % 2 == 0)        // intermediate: keep even numbers
                .map(n -> n * n)                // intermediate: "transform" each n
                .reduce(0, Integer::sum);       // TERMINAL: produces a single result (int)

        System.out.println("Numbers: " + numbers);
        System.out.println("Loop sum of even*2 = " + loopSum);
        System.out.println("Stream sum of even*2 = " + streamSum);
    }
}
