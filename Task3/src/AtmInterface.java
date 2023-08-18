import java.util.Scanner;

public class AtmInterface{
    private BankAccount bankAccount;
    private Scanner scanner;

    public AtmInterface(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Welcome to the ATM!");

        while (true) {
            displayMenu();
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nOptions:");
        System.out.println("1. Check Balance");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
    }

    private void checkBalance() {
        System.out.println("Your current balance is: $" + bankAccount.getBalance());
    }

    private void withdraw() {
        System.out.print("Enter the amount to withdraw: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            if (bankAccount.withdraw(amount)) {
                System.out.println("Withdrawal successful. Remaining balance: $" + bankAccount.getBalance());
            } else {
                System.out.println("Insufficient balance. Withdrawal failed.");
            }
        } else {
            System.out.println("Invalid amount. Withdrawal failed.");
        }
    }

    private void deposit() {
        System.out.print("Enter the amount to deposit: $");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            bankAccount.deposit(amount);
            System.out.println("Deposit successful. New balance: $" + bankAccount.getBalance());
        } else {
            System.out.println("Invalid amount. Deposit failed.");
        }
    }

    public static void main(String[] args) {
        // Create a bank account with an initial balance of $1000
        BankAccount bankAccount = new BankAccount(1000);

        // Create an ATM instance and run the ATM
        AtmInterface atm = new AtmInterface(bankAccount);
        atm.run();
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= balance && amount > 0) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

