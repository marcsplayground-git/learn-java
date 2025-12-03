package com.personalplayground.bankooplearning.practice;

public class Dog extends Animal implements Walkable {

    public Dog(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " says: Woof!");
    }

    @Override
    public void walk() {
        System.out.println(name + " is walking.");
    }
}
