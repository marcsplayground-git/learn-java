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

        // 4) Mark one task as completed
        t1.markCompleted();

        // 5) Print all tasks
        System.out.println("=== All Tasks in Plan: " + plan.getName() + " ===");
        for (StudyTask task : plan.getTasks()) {
            System.out.println(task);
        }
    }
}
