import java.util.Scanner;

class Account {
    private int accountNumber;
    private String accountHolderName;
    private double balance;
    private String email;
    private String phoneNumber;

    // Constructor
    public Account(int accountNumber, String accountHolderName, double balance, String email, String phoneNumber) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: " + amount);
        } else {
            System.out.println("Deposit amount must be positive!");
        }
    }

    // Withdraw money
    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Successfully withdrawn: " + amount);
            } else {
                System.out.println("Insufficient balance!");
            }
        } else {
            System.out.println("Withdrawal amount must be positive!");
        }
    }

    // Display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Balance: " + balance);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
    }

    // Update contact details
    public void updateContactDetails(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        System.out.println("Contact details updated successfully.");
    }

    // Getter for account number
    public int getAccountNumber() {
        return accountNumber;
    }
}

public class UserInterface {
    private Account[] accounts;
    private int accountCount;
    private Scanner scanner;

    // Constructor
    public UserInterface(int size) {
        accounts = new Account[size];
        accountCount = 0;
        scanner = new Scanner(System.in);
    }

    // Create a new account
    public void createAccount() {
        if (accountCount < accounts.length) {
            System.out.print("Enter account holder name: ");
            String name = scanner.nextLine();

            System.out.print("Enter initial deposit amount: ");
            double balance = scanner.nextDouble();
            scanner.nextLine(); // consume newline

            System.out.print("Enter email address: ");
            String email = scanner.nextLine();

            System.out.print("Enter phone number: ");
            String phone = scanner.nextLine();

            int accountNumber = 1000 + accountCount + 1;
            accounts[accountCount] = new Account(accountNumber, name, balance, email, phone);
            accountCount++;

            System.out.println("Account created successfully with Account Number: " + accountNumber);
        } else {
            System.out.println("Account limit reached! Cannot create more accounts.");
        }
    }

    // Find account by number
    private Account findAccount(int accountNumber) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccountNumber() == accountNumber) {
                return accounts[i];
            }
        }
        return null;
    }

    // Deposit money
    public void performDeposit() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.deposit(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Withdraw money
    public void performWithdrawal() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.withdraw(amount);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Show account details
    public void showAccountDetails() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNo);
        if (acc != null) {
            acc.displayAccountDetails();
        } else {
            System.out.println("Account not found!");
        }
    }

    // Update contact details
    public void updateContact() {
        System.out.print("Enter account number: ");
        int accNo = scanner.nextInt();
        scanner.nextLine(); // consume newline

        Account acc = findAccount(accNo);
        if (acc != null) {
            System.out.print("Enter new email: ");
            String email = scanner.nextLine();
            System.out.print("Enter new phone number: ");
            String phone = scanner.nextLine();
            acc.updateContactDetails(email, phone);
        } else {
            System.out.println("Account not found!");
        }
    }

    // Main menu
    public void mainMenu() {
        int choice;
        do {
            System.out.println("\n--- Welcome to the Banking Application ---");
            System.out.println("1. Create a new account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. View account details");
            System.out.println("5. Update contact details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> createAccount();
                case 2 -> performDeposit();
                case 3 -> performWithdrawal();
                case 4 -> showAccountDetails();
                case 5 -> updateContact();
                case 6 -> System.out.println("Exiting... Thank you for using Banking Application!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 6);
    }

    // Main method
    public static void main(String[] args) {
        UserInterface ui = new UserInterface(100); // up to 100 accounts
        ui.mainMenu();
    }
}

