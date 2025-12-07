package com.personalplayground.jrelab.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionAndAnnotationsDemo {

    public static void run() {
        System.out.println("=== Reflection & Annotations Demo ===");

        explainConcepts();
        inspectTypeDemo();
        invokeMethodDemo();
        annotationDemo();
    }

    // 1) High-level explanation
    private static void explainConcepts() {
        System.out.println("\n-- What is reflection? --");
        System.out.println("Reflection lets you inspect types and members at runtime,");
        System.out.println("even if you don't know the concrete type at compile time.");
        System.out.println();
        System.out.println("Key ideas:");
        System.out.println("  * Every type has exactly one Class<?> object.");
        System.out.println("  * From Class you can ask for fields, methods, constructors, modifiers.");
        System.out.println("  * You can also create instances and invoke methods dynamically.");
        System.out.println();
        System.out.println("Annotations are metadata attached to classes, methods, fields, etc.");
        System.out.println("  * They don't change behavior by themselves.");
        System.out.println("  * Tools / frameworks / your code read them (often via reflection).");
    }

    // 2) Inspect a type: fields, methods, modifiers
    private static void inspectTypeDemo() {
        System.out.println("\n-- Inspecting a type with reflection --");

        // Get the Class object in different ways
        Class<DemoTarget> viaLiteral = DemoTarget.class;
        Class<?> viaForName;

        try {
            viaForName = Class.forName("com.personalplayground.jrelab.reflection.ReflectionAndAnnotationsDemo$DemoTarget");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load class via Class.forName: " + e.getMessage());
            return;
        }

        System.out.println("Class via literal:  " + viaLiteral.getName());
        System.out.println("Class via forName:  " + viaForName.getName());
        System.out.println("Same object? " + (viaLiteral == viaForName));

        Class<?> type = viaLiteral;

        System.out.println("\nBasic type info:");
        System.out.println("  Simple name: " + type.getSimpleName());
        System.out.println("  Full name:   " + type.getName());

        Class<?> superclass = type.getSuperclass();
        System.out.println("  Superclass:  " + (superclass != null ? superclass.getName() : "<none>"));

        int modifiers = type.getModifiers();
        System.out.println("  Is public?   " + Modifier.isPublic(modifiers));
        System.out.println("  Is final?    " + Modifier.isFinal(modifiers));

        System.out.println("\nDeclared fields:");
        for (Field f : type.getDeclaredFields()) {
            System.out.println("  " + Modifier.toString(f.getModifiers()) + " "
                    + f.getType().getSimpleName() + " " + f.getName());
        }

        System.out.println("\nDeclared methods (excluding Object methods):");
        for (Method m : type.getDeclaredMethods()) {
            if (m.getDeclaringClass() == Object.class) {
                continue; // skip toString, equals, etc. from Object
            }
            System.out.print("  " + Modifier.toString(m.getModifiers()) + " "
                    + m.getReturnType().getSimpleName() + " " + m.getName() + "(");
            Class<?>[] paramTypes = m.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) System.out.print(", ");
                System.out.print(paramTypes[i].getSimpleName());
            }
            System.out.println(")");
        }
    }

    // 3) Invoke a method dynamically using reflection
    private static void invokeMethodDemo() {
        System.out.println("\n-- Invoking a method via reflection --");

        // Normal construction (just to compare)
        DemoTarget normal = new DemoTarget("Logging module", 1);
        System.out.println("Normal instance: " + normal);

        // Create using reflection via a Constructor
        try {
            Constructor<DemoTarget> ctor =
                    DemoTarget.class.getConstructor(String.class, int.class);

            DemoTarget reflected = ctor.newInstance("Reflection module", 2);
            System.out.println("Instance created via reflection: " + reflected);

            // Call incrementLevel() reflectively
            Method incrementMethod = DemoTarget.class.getMethod("incrementLevel");
            System.out.println("Calling incrementLevel() via reflection...");
            incrementMethod.invoke(reflected); // same as reflected.incrementLevel()

            System.out.println("After reflection call: " + reflected);
        } catch (Exception e) {
            System.out.println("Error during reflective construction/invocation: " + e);
        }
    }

    // 4) Show custom annotation + reading it via reflection
    private static void annotationDemo() {
        System.out.println("\n-- Annotations demo --");

        Class<DemoTarget> type = DemoTarget.class;

        DemoInfo info = type.getAnnotation(DemoInfo.class);
        if (info == null) {
            System.out.println("DemoTarget has no @DemoInfo annotation (this should not happen).");
            return;
        }

        System.out.println("Found @DemoInfo on DemoTarget:");
        System.out.println("  topic:      " + info.topic());
        System.out.println("  difficulty: " + info.level());
        System.out.println("  author:     " + info.author());

        System.out.println();
        System.out.println("Remember:");
        System.out.println("  * @DemoInfo has RUNTIME retention, so reflection can see it.");
        System.out.println("  * Without RUNTIME, getAnnotation(...) would return null.");
        System.out.println("  * Tools / frameworks use the same pattern to read annotations.");
    }

    // =========================
    // Types used in the demo
    // =========================

    /**
     * Custom annotation used as metadata for demo types.
     */
    @Retention(RetentionPolicy.RUNTIME)        // keep at runtime for reflection
    @Target(ElementType.TYPE)                  // can be used on classes/interfaces
    private @interface DemoInfo {
        String topic();
        int level() default 1;
        String author() default "Unknown";
    }

    /**
     * A simple type that we will inspect and invoke via reflection.
     */
    @DemoInfo(
            topic = "Reflection & Annotations",
            level = 2,
            author = "JRE Learning Lab"
    )
    private static class DemoTarget {

        private String name;
        private int level;

        public DemoTarget(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        public void incrementLevel() {
            this.level++;
        }

        @Override
        public String toString() {
            return "DemoTarget{" +
                    "name='" + name + '\'' +
                    ", level=" + level +
                    '}';
        }
    }
}
