package com.personalplayground.bankooplearning.demo;

public class BankAppStep3 {

    public static void main(String[] args) {

        /* Done
        // Strategies
        InterestStrategy savingInterest = new FixedRateInterestStrategy(0.01);
        InterestStrategy checkingFee = new MonthlyFeeStrategy(10.0);

        // Accounts using strategies
        BankAccount checking = new CheckingAccount("CHK-001", "Alice", 1000.00, checkingFee);
        BankAccount savings = new SavingsAccount("SVG-001", "Bob", 2000, savingInterest);

        List<BankAccount> accounts = new ArrayList<>();
        accounts.add(checking);
        accounts.add(savings);

        // Apply updates polymorphically
        for (BankAccount account : accounts) {
            account.deposit(100);
            account.applyMonthlyUpdate(); // Different strategy, same method
        }

        // Print results
        for (BankAccount account : accounts) {
            System.out.println(
                    account.getClass().getSimpleName() + " | " +
                    account.getId() + " | Owner: " + account.getOwnerName() +
                    " | Balance: " + account.getBalance()
            );
        }

         */
    }
}
