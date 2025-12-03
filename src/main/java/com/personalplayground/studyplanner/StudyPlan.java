package com.personalplayground.studyplanner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StudyPlan implements Iterable<StudyTask>{
    public static class Priority {
        private final String label;
        private final int level; // 1 = low, 3 = high

        public static final Priority LOW = new Priority("LOW", 1);
        public static final Priority MEDIUM = new Priority("MEDIUM", 2);
        public static final Priority HIGH = new Priority("HIGH", 3);

        private Priority(String label, int level) {
            this.label = label;
            this.level = level;
        }

        public String label() {
            return label;
        }

        public int level() {
            return level;
        }

        @Override
        public String toString() {
            return label;
        }
    }
    public static class PrioritizedTask {
        private final StudyTask task;
        private final Priority priority;

        public PrioritizedTask(StudyTask task, Priority priority) {
            this.task = task;
            this.priority = priority;
        }

        public StudyTask task() {
            return task;
        }

        public Priority priority() {
            return priority;
        }

        @Override
        public String toString() {
            return task + " [priority=" + priority + "]";
        }
    }
    public interface TaskFilter {
        boolean include(StudyTask task);
    }


    private final String name;
    private final List<StudyTask> tasks = new ArrayList<>();

    public StudyPlan(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addTask(StudyTask task) {
        if (task == null) {
            throw new IllegalArgumentException("Cannot add null task to StudyPlan.");
        }
        tasks.add(task);
    }

    public void completeTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException(
                    "No task exists at index: " + index
            );
        }

        tasks.get(index).markCompleted();
    }


    public List<StudyTask> getTasks() {
        return List.copyOf(tasks); // read-only copy
    }

    private class PendingTaskIterator implements java.util.Iterator<StudyTask> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            // Look for the next NOT completed task
            while (currentIndex < tasks.size()) {
                if (!tasks.get(currentIndex).isCompleted()) {
                    return true;
                }
                currentIndex++; // skip completed tasks
            }
            return false;
        }

        @Override
        public StudyTask next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return tasks.get(currentIndex++);
        }
    }

    public Iterable<StudyTask> pendingTasks() {
        return new Iterable<StudyTask>() {
            @Override
            public java.util.Iterator<StudyTask> iterator() {
                return new PendingTaskIterator();
            }
        };
    }

    public java.util.List<StudyTask> filterTasks(TaskFilter filter) {
        java.util.List<StudyTask> result = new java.util.ArrayList<>();
        for (StudyTask task : tasks) {
            if (filter.include(task)) {
                result.add(task);
            }
        }
        return result;
    }

    @Override
    public Iterator<StudyTask> iterator() {
        return tasks.iterator();
    }
}
