package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class ObjectMutationDemo {

    public static void main(String[] args) {
        System.out.println("=== Milestone 2: Mutating Objects vs Reassigning References ===\n");

        NumberBox box = new NumberBox(10);
        System.out.println("Initial box in caller: " + box);

        System.out.println("\n--- Calling mutateBox(box) ---");
        ObjectMutationHelper.mutateBox(box);
        System.out.println("After mutateBox(box), caller sees: " + box);

        System.out.println("\n--- Calling reassignBox(box) ---");
        ObjectMutationHelper.reassignBox(box);
        System.out.println("After reassignBox(box), caller sees: " + box);

        System.out.println("\nExplanation:");
        System.out.println(" - mutateBox() changes the object's field, caller sees the change.");
        System.out.println(" - reassignBox() changes only the local reference, caller does NOT see a new object.");
    }
}
