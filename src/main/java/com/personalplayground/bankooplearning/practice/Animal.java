package com.personalplayground.bankooplearning.practice;

public abstract class Animal {

    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    // All animals speak, but differently → abstract method
    public abstract void speak();

    // All animals have a name → shared behavior
    public String getName() {
        return name;
    }

    // Common behavior shared by all animals
    public void eat() {
        System.out.println(name + " is eating...");
    }
}
