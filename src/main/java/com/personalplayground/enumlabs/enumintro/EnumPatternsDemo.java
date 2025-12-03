package com.personalplayground.enumlabs.enumintro;

public class EnumPatternsDemo {

    public static void main(String[] args) {

        System.out.println("\n=== Milestone 5: Practical Enum Patterns ===\n");

        // ----- 1. ORDER STATUS -----
        OrderStatus status = OrderStatus.NEW;
        System.out.println("Current status: " + status);
        System.out.println("Is final? " + status.isFinalState());

        status = OrderStatus.DELIVERED;
        System.out.println("\nStatus changed to: " + status);
        System.out.println("Is final? " + status.isFinalState());


        // ----- 2. USER ROLES -----
        UserRole role = UserRole.ADMIN;
        System.out.println("\nRole: " + role);
        System.out.println("Can access dashboard: " + role.canAccessDashboard());

        role = UserRole.GUEST;
        System.out.println("Role changed to: " + role);
        System.out.println("Can access dashboard: " + role.canAccessDashboard());


        // ----- 3. MATH OPERATION WITH BEHAVIOR -----
        double result = MathOperation.ADD.apply(10, 5);
        System.out.println("\n10 + 5 = " + result);

        result = MathOperation.MULTIPLY.apply(10, 5);
        System.out.println("10 * 5 = " + result);

        System.out.println("\nAll operations:");
        for (MathOperation op : MathOperation.values()) {
            System.out.println(op + " → 10 op 3 = " + op.apply(10, 3) + " " + op.getTitle());
        }

        System.out.println("\nNotes:");
        System.out.println(" - OrderStatus models application states.");
        System.out.println(" - UserRole shows enums storing permissions.");
        System.out.println(" - MathOperation demonstrates enums with behavior (polymorphism).");
    }
}
