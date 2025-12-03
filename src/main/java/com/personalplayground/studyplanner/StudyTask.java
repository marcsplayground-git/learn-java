package com.personalplayground.studyplanner;

import java.time.LocalDateTime;

public class StudyTask {
    private final String title;
    private final int estimatedMinutes;
    private final LocalDateTime dueAt;
    private boolean completed;

    public StudyTask(String title, int estimatedMinutes, LocalDateTime dueAt) {
        this.title = title;
        this.estimatedMinutes = estimatedMinutes;
        this.dueAt = dueAt;
        this.completed = false; // default not completed
    }

    public String getTitle() {
        return title;
    }

    public int getEstimatedMinutes() {
        return estimatedMinutes;
    }

    public LocalDateTime getDueAt() {
        return dueAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    @Override
    public String toString() {
        return "[%s] %s (%d min, due %s)".formatted(
                completed ? "X" : " ",
                title,
                estimatedMinutes,
                dueAt
        );
    }
}
