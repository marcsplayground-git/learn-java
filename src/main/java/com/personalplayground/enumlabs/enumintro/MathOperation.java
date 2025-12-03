package com.personalplayground.enumlabs.enumintro;

public enum MathOperation {

    ADD("Addition") {
        public double apply(double a, double b) { return a + b; }
    },
    SUBTRACT("Subtraction") {
        public double apply(double a, double b) { return a - b; }
    },
    MULTIPLY("Multiplication")  {
        public double apply(double a, double b) { return a * b; }
    },
    DIVIDE("Divide") {
        public double apply(double a, double b) { return a / b; }
    };

    private final String operation;

    MathOperation(String operation) {
        this.operation = operation;
    }

    // Getter methods
    public String getTitle() {
        return operation;
    }

    public abstract double apply(double a, double b);
}
