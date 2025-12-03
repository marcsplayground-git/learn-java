package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class ObjectMutationHelper {

    // Mutates the object that both references point to
    public static void mutateBox(NumberBox boxParam) {
        System.out.println("Inside mutateBox: before mutation = " + boxParam);
        boxParam.value = 777;   // MUTATION
        System.out.println("Inside mutateBox: after mutation  = " + boxParam);
    }

    // Reassigns the reference (does NOT affect caller)
    public static void reassignBox(NumberBox boxParam) {
        System.out.println("Inside reassignBox: before reassignment = " + boxParam);
        boxParam = new NumberBox(888);  // REASSIGNMENT
        System.out.println("Inside reassignBox: after reassignment  = " + boxParam);
    }
}
