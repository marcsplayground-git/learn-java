package com.personalplayground.studyplanner;

import java.util.ArrayList;
import java.util.List;

public class StudyPlan {
    private final String name;
    private final List<StudyTask> tasks = new ArrayList<>();

    public StudyPlan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTask(StudyTask task) {
        tasks.add(task);
    }

    public List<StudyTask> getTasks() {
        return List.copyOf(tasks); // read-only copy
    }
}
