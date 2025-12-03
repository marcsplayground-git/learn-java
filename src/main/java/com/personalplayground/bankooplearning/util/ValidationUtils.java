package com.personalplayground.bankooplearning.util;

import com.personalplayground.bankooplearning.exception.InsufficientFundsException;
import com.personalplayground.bankooplearning.exception.InvalidAmountException;

public final class ValidationUtils {

    private ValidationUtils() {
        // prevent instantiation
    }

    public static void requirePositiveAmount(double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive, but was: " + amount);
        }
    }

    public static void requireSufficientFunds(double balance, double amountToWithdraw) {
        if (balance - amountToWithdraw < 0) {
            throw new InsufficientFundsException(
                    "Insufficient funds: balance=" + balance + ", requested=" + amountToWithdraw
            );
        }
    }
}
