package com.personalplayground.generics.basics;

/**
 * Generic Box<T>:
 * - T is a TYPE PARAMETER (a placeholder for a real type).
 * - Box<String> or Box<Integer> use TYPE ARGUMENTS (String, Integer).
 */
public class Box<T> {  // <-- Generic type with type parameter T

    private T value;   // This can be String, Integer, Person, etc., depending on T

    public Box() {
    }

    public Box(T value) {
        this.value = value;
    }

    /**
     * Setter uses T: caller decides what T is.
     */
    public void set(T value) {
        this.value = value;
    }

    /**
     * Getter returns T: compiler knows the return type (no cast needed).
     */
    public T get() {
        return value;
    }

    @Override
    public String toString() {
        return "Box{" +
                "value=" + value +
                '}';
    }
}
