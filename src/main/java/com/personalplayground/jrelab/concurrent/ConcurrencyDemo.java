package com.personalplayground.jrelab.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrencyDemo {

    public static void run() {
        System.out.println("=== Multithreading & Concurrency Demo ===");

        explainCoreConcepts();
        simpleThreadDemo();
        threadPoolDemo();
        sharedStateRaceDemo();
    }

    // 1) Core ideas: process, thread, single vs multi-threaded
    private static void explainCoreConcepts() {
        System.out.println("\n-- Core concepts: process, thread, concurrency --");
        System.out.println("Process  = a running program with its own memory and resources.");
        System.out.println("Thread   = a sequence of instructions inside a process.");
        System.out.println("A Java program usually starts as a single-threaded app (just main).");
        System.out.println("Multithreading = using multiple threads in the same process to do work concurrently.");
        System.out.println("It can increase responsiveness (UI/server) and throughput (CPU or I/O).");
    }

    // 2) Basic Thread + Runnable example
    private static void simpleThreadDemo() {
        System.out.println("\n-- Simple Thread + Runnable demo --");

        Runnable worker = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Worker running on thread: " + threadName);

            for (int i = 1; i <= 3; i++) {
                System.out.println("  [" + threadName + "] step " + i);
                try {
                    Thread.sleep(300); // simulate some work
                } catch (InterruptedException e) {
                    System.out.println("  [" + threadName + "] was interrupted.");
                    Thread.currentThread().interrupt(); // restore interrupt flag
                    return;
                }
            }

            System.out.println("Worker finished on thread: " + threadName);
        };

        System.out.println("Creating a new Thread with the worker Runnable...");
        Thread t = new Thread(worker, "SimpleWorker-1");

        System.out.println("Starting thread...");
        t.start();

        System.out.println("Back in main thread: " + Thread.currentThread().getName());
        System.out.println("Waiting for worker thread to finish using join()...");

        try {
            System.out.println("Before join...");
            t.join(); // wait for worker to finish
            System.out.println("After join...");
        } catch (InterruptedException e) {
            System.out.println("Main thread was interrupted while waiting.");
            Thread.currentThread().interrupt();
        }

        System.out.println("Worker thread has finished.");
    }

    // 3) ExecutorService thread pool demo
    private static void threadPoolDemo() {
        System.out.println("\n-- Thread pool demo (ExecutorService) --");

        System.out.println("Creating a fixed thread pool with 3 worker threads...");
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Submit 5 tasks to the pool
        for (int i = 1; i <= 5; i++) {
            int taskId = i; // effectively final for lambda
            pool.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println("Task " + taskId + " started on " + threadName);
                try {
                    Thread.sleep(500); // simulate some work
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskId + " was interrupted.");
                    Thread.currentThread().interrupt();
                    return;
                }
                System.out.println("Task " + taskId + " finished on " + threadName);
            });
        }

        System.out.println("All tasks submitted. Shutting down pool...");

        // No more new tasks; finish existing ones
        pool.shutdown();
        try {
            // Wait up to 5 seconds for tasks to complete
            if (!pool.awaitTermination(5, TimeUnit.SECONDS)) {
                System.out.println("Some tasks did not finish within the timeout.");
            } else {
                System.out.println("All tasks in the pool finished.");
            }
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted while waiting for pool.");
            Thread.currentThread().interrupt();
        }
    }

    // Helper type to show race condition (no synchronization)
    private static class UnsafeCounter {
        private int value = 0;

        void increment() {
            // Not atomic: read-modify-write with no synchronization
            value = value + 1;
        }

        int getValue() {
            return value;
        }
    }

    // 4) Shared state race condition + AtomicInteger fix
    private static void sharedStateRaceDemo() {
        System.out.println("\n-- Shared state & race condition demo --");
        System.out.println("We will increment a shared counter from many tasks in parallel.");

        int incrementsPerThread = 5_000;
        int threadCount = 4;
        int expectedTotal = incrementsPerThread * threadCount;

        // 4.1 Naive shared counter (unsafe)
        UnsafeCounter unsafeCounter = new UnsafeCounter();
        ExecutorService pool1 = Executors.newFixedThreadPool(threadCount);

        System.out.println("Starting UNSAFE increments (no synchronization)...");
        for (int i = 0; i < threadCount; i++) {
            pool1.submit(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    unsafeCounter.increment();
                }
            });
        }

        pool1.shutdown();
        try {
            pool1.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while awaiting unsafeCounter tasks.");
            Thread.currentThread().interrupt();
        }

        int unsafeResult = unsafeCounter.getValue();
        System.out.println("Expected total increments: " + expectedTotal);
        System.out.println("UNSAFE counter result:     " + unsafeResult);
        System.out.println("Because increments are not synchronized, result is often less than expected.");
        System.out.println("If it happens to match on a run, try running the demo again.");

        // 4.2 AtomicInteger fix
        System.out.println("\nNow we fix the problem using AtomicInteger...");

        AtomicInteger atomicCounter = new AtomicInteger(0);
        ExecutorService pool2 = Executors.newFixedThreadPool(threadCount);

        for (int i = 0; i < threadCount; i++) {
            pool2.submit(() -> {
                for (int j = 0; j < incrementsPerThread; j++) {
                    atomicCounter.incrementAndGet(); // atomic increment
                }
            });
        }

        pool2.shutdown();
        try {
            pool2.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Interrupted while awaiting atomicCounter tasks.");
            Thread.currentThread().interrupt();
        }

        int atomicResult = atomicCounter.get();
        System.out.println("Expected total increments: " + expectedTotal);
        System.out.println("ATOMIC counter result:     " + atomicResult);
        System.out.println("AtomicInteger uses atomic operations so the count is reliable.");
    }
}
