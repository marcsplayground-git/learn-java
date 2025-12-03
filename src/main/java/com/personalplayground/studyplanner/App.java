package com.personalplayground.studyplanner;

import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        System.out.println("StudyPlanner booting...");

        // 1) Create a study plan
        StudyPlan plan = new StudyPlan("Java 17 Mastery");

        // 2) Create some tasks
        StudyTask t1 = new StudyTask(
                "Read nested classes chapter",
                45,
                LocalDateTime.now().plusDays(1)
        );

        StudyTask t2 = new StudyTask(
                "Code inner class example",
                60,
                LocalDateTime.now().plusDays(2)
        );

        StudyTask t3 = new StudyTask(
                "Practice anonymous classes",
                30,
                LocalDateTime.now().plusHours(4)
        );

        // 3) Add tasks to the plan
        plan.addTask(t1);
        plan.addTask(t2);
        plan.addTask(t3);

        // Example: wrap some tasks with priorities
        StudyPlan.Priority high = StudyPlan.Priority.HIGH;
        StudyPlan.Priority medium = StudyPlan.Priority.MEDIUM;

        StudyPlan.PrioritizedTask pt1 = new StudyPlan.PrioritizedTask(t1, high);
        StudyPlan.PrioritizedTask pt2 = new StudyPlan.PrioritizedTask(t2, medium);

        System.out.println("\n=== Prioritized Tasks (using static nested classes) ===");
        System.out.println(pt1);
        System.out.println(pt2);


        // 4) Mark one task as completed
        t1.markCompleted();

        // 5) Print all tasks
        System.out.println("=== All Tasks in Plan: " + plan.getName() + " ===");
        for (StudyTask task : plan) {
            System.out.println(task);
        }

        System.out.println("\n=== All Tasks (using StudyPlan iterator) ===");
        for (StudyTask task : plan) {
            System.out.println(task);
        }

        System.out.println("\n=== Pending Tasks (using inner class PendingTaskIterator) ===");
        for (StudyTask task : plan.pendingTasks()) {
            System.out.println(task);
        }

        System.out.println("\n=== Tasks due today (anonymous class) ===");

        var dueToday = plan.filterTasks(new StudyPlan.TaskFilter() {
            @Override
            public boolean include(StudyTask task) {
                return task.getDueAt().toLocalDate()
                        .equals(java.time.LocalDate.now());
            }
        });

        dueToday.forEach(System.out::println);

        System.out.println("\n=== Short tasks (<= 40 min) (lambda) ===");

        var shortTasks = plan.filterTasks(
                task -> task.getEstimatedMinutes() <= 40
        );

        shortTasks.forEach(System.out::println);

        System.out.println("\n=== Tasks sorted by estimatedMinutes (anonymous Comparator) ===");

        // Create a new list to sort, so we don't modify the original
        var tasksToSort = new java.util.ArrayList<>(plan.getTasks());

        tasksToSort.sort(new java.util.Comparator<StudyTask>() {
            @Override
            public int compare(StudyTask a, StudyTask b) {
                // ascending order by estimatedMinutes
                return Integer.compare(a.getEstimatedMinutes(), b.getEstimatedMinutes());
            }
        });

        for (StudyTask task : tasksToSort) {
            System.out.println(task);
        }


        printSection("Tasks sorted by title (lambda Comparator)");

        var tasksByTitle = new java.util.ArrayList<>(plan.getTasks());

        tasksByTitle.sort(
                java.util.Comparator.comparing(StudyTask::getTitle)
        );

        for (StudyTask task : tasksByTitle) {
            System.out.println(task);
        }

        System.out.println("\n=== Validation Tests ===");

        try {
            // invalid: empty title
            StudyTask bad = new StudyTask("", 30, java.time.LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        try {
            // invalid: negative minutes
            StudyTask bad2 = new StudyTask("Invalid", -5, java.time.LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

        try {
            // invalid task index
            plan.completeTask(999);
        } catch (Exception e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }

    }

    private static void printSection(String title) {
        System.out.println("\n=== " + title + " ===");
    }

}
