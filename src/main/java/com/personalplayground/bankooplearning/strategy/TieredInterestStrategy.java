package com.personalplayground.bankooplearning.strategy;

public class TieredInterestStrategy implements InterestStrategy {
    @Override
    public double calculateNewBalance(double currentBalance) {

        if (currentBalance < 1000) {
            return currentBalance + currentBalance * 0.01;  // 1%
        }
        else if (currentBalance < 5000) {
            return currentBalance + currentBalance * 0.015; // 1.5%
        }
        else {
            return currentBalance + currentBalance * 0.02;  // 2%
        }
    }
}
