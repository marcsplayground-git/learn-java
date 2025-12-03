package com.personalplayground.bankooplearning.demo;

public class BankAppStep2 {

    /* Done
    public static void main(String[] args) {
        //Upcasting: subclasses stored as base type
        List<BankAccount> accounts = new ArrayList<>();

        BankAccount checking = new CheckingAccount("CHK-001", "Alice", 1000.0);
        BankAccount savings = new SavingsAccount("SVG-001", "Bob", 2000.0);

        accounts.add(checking);
        accounts.add(savings);

        // Same operations on all account
        for (BankAccount account : accounts) {
            account.deposit(100); // same method -> same behavior (from base)
            account.applyMonthlyUpdate(); // same call -> different behavior per subclass
        }

        // Print results
        for (BankAccount account : accounts) {
            System.out.println(
                    account.getClass().getSimpleName() + " | " +
                    account.getId() + " | Owner: " + account.getOwnerName() +
                    " | Balance: " + account.getBalance()
            );
        }
    }

     */
}
