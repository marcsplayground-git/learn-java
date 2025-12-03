package com.personalplayground.bankooplearning.practice;

public class Cat extends Animal implements Walkable, Swimmable {

    public Cat(String name) {
        super(name);
    }

    @Override
    public void speak() {
        System.out.println(name + " says: Meow!");
    }

    @Override
    public void walk() {
        System.out.println(name + " is walking.");
    }

    @Override
    public void swim() {
        System.out.println(name + " is swimming.");
    }
}
