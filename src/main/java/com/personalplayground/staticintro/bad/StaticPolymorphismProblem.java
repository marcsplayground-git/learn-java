package com.personalplayground.staticintro.bad;

class Animal {
    public static void speak() {
        System.out.println("Animal speaks");
    }
}

class Dog extends Animal {
    public static void speak() { // ❌ NOT override, this is hiding
        System.out.println("Dog barks");
    }
}

public class StaticPolymorphismProblem {
    public static void main(String[] args) {

        Animal a = new Dog();
        a.speak(); // ❌ OUTPUT: "Animal speaks" (NOT polymorphic)

        Dog d = new Dog();
        d.speak(); // OUTPUT: "Dog barks"
    }
}
