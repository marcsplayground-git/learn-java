package com.personalplayground.bankooplearning.strategy;

public class FixedRateInterestStrategy implements InterestStrategy {

    private final double rate; // eg., 0.01 = 1%

    public FixedRateInterestStrategy(double rate) {
        this.rate = rate;
    }

    @Override
    public double calculateNewBalance(double currentBalance) {
        return currentBalance + currentBalance * rate;
    }
}
