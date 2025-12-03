package com.personalplayground.staticintro;

public final class PlaneFactory {

    private PlaneFactory() {}

    public static Plane createSmallPlane() {
        System.out.println("Creating small plane...");
        return new Plane();
    }

    public static Plane createStandardPlane() {
        System.out.println("Creating standard plane...");
        return new Plane();
    }
}
