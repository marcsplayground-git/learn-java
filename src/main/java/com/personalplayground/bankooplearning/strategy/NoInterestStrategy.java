package com.personalplayground.bankooplearning.strategy;

public class NoInterestStrategy implements InterestStrategy {

    @Override
    public double calculateNewBalance(double currentBalance) {
        return currentBalance; // no change
    }
}
