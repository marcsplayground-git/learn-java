package com.personalplayground.methodparameteroverloadingproject.methodintro;

public class NumberBox {

    public int value; // public for simplicity in this demo

    public NumberBox(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "NumberBox{value=" + value + "}";
    }
}
