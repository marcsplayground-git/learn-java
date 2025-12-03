package com.personalplayground.bankooplearning.demo;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BadBankApp {
    public static void main(String[] args) {
        // Array for balances:
        // index 0 = checking
        // index 1 = savings
        double[] balances = new double[2];
        String[] accountTypes = new String[2];

        accountTypes[0] = "CHECKING";
        balances[0] = 1000.0;
        accountTypes[1] = "SAVINGS";
        balances[1] = 2000.0;

        // Deposit to checking
        deposit(balances, 0, 200.0);
        // Withdraw for savings
        withdraw(balances, 1, 300.0);
        // Apply monthly rules
        applyMonthlyUpdates(accountTypes, balances);

        // Print final balances
        for (int i = 0; i < balances.length; i++) {
            System.out.println(accountTypes[i] + " final balance: " + balances[i]);
        }
    }

    public static void deposit(double[] balances, int index, double amount) {
        balances[index] = balances[index] + amount;
        System.out.println("Deposited " + amount + " to account " + index);
        // TODO: send email or SMS depending on some flags (not implemented)
    }

    public static void withdraw(double[] balances, int index, double amount) {
        if (balances[index] - amount < 0) {
            System.out.println("Not enough funds");
            return;
        }
        balances[index] = balances[index] - amount;
        System.out.println("Withdrew " + amount + " from account " + index);
        // TODO: send email or SMS depending on some flags (not implemented)
    }

    public static void applyMonthlyUpdates(String[] accountTypes, double[] balances) {
        for (int i=0; i < balances.length; i++) {
            if ("CHECKING".equals(accountTypes[i])) {
                balances[i] -= 10.0; //flat fee
            } else if ("SAVINGS".equals(accountTypes[i])) {
                balances[i] += balances[i] * 0.01; // 1% interest
            } else {
                System.out.println("Unknown account type");
            }
        }
    }

}