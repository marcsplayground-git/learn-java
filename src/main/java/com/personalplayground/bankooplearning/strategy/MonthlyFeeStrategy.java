package com.personalplayground.bankooplearning.strategy;

public class MonthlyFeeStrategy implements InterestStrategy {

    private final double fee;

    public MonthlyFeeStrategy(double fee) {
        this.fee = fee;
    }

    @Override
    public double calculateNewBalance(double currentBalance) {
        return currentBalance - fee;
    }
}
