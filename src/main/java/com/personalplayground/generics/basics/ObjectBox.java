package com.personalplayground.generics.basics;

/**
 * Pre-generics style box using Object.
 * This is what people did before generics existed.
 */
public class ObjectBox {

    private Object value;  // Can hold ANY reference type

    public ObjectBox() {
    }

    public ObjectBox(Object value) {
        this.value = value;
    }

    public void set(Object value) {
        this.value = value;
    }

    public Object get() {
        return value;
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "value=" + value +
                '}';
    }
}
