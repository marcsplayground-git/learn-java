package com.personalplayground.bankooplearning.practice;

public class InterfaceVsAbstractDemo {

    public static void main(String[] args) {

        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Mittens");

        // Abstract class polymorphism
        dog.speak();
        cat.speak();

        dog.eat();
        cat.eat();

        // Interface polymorphism
        Walkable walkerDog = (Walkable) dog;
        Walkable walkerCat = (Walkable) cat;

        walkerDog.walk();
        walkerCat.walk();

        Swimmable swimmerCat = (Swimmable) cat;
        swimmerCat.swim();
    }
}
