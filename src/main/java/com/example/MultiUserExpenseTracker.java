package com.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import com.example.IncomeDAO;
import com.example.ExpenseDAO;
import com.example.User;
import com.example.Income;
import com.example.Expense;


public class MultiUserExpenseTracker {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();
        IncomeDAO incomeDAO = new IncomeDAO();
        ExpenseDAO expenseDAO = new ExpenseDAO();

        User loggedInUser = null;

        while (true) {
            if (loggedInUser == null) {
                System.out.println("\n--- Welcome ---");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        loggedInUser = userDAO.register(username, password);
                        System.out.println("Registered and logged in as " + username);
                    }
                    case 2 -> {
                        System.out.print("Enter username: ");
                        String username = sc.nextLine();
                        System.out.print("Enter password: ");
                        String password = sc.nextLine();
                        loggedInUser = userDAO.login(username, password);
                        if (loggedInUser != null) System.out.println("Logged in as " + username);
                        else System.out.println("Invalid credentials!");
                    }
                    case 3 -> System.exit(0);
                    default -> System.out.println("Invalid choice!");
                }
            } else {
                System.out.println("\n--- Expense Tracker ---");
                System.out.println("1. Add Income");
                System.out.println("2. Add Expense");
                System.out.println("3. View Incomes");
                System.out.println("4. View Expenses");
                System.out.println("5. Show Balance");
                System.out.println("6. Logout");
                System.out.print("Choose: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Income type: ");
                        String type = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        incomeDAO.addIncome(new Income(type, amt, LocalDate.now(), loggedInUser));
                        System.out.println("Income added!");
                    }
                    case 2 -> {
                        System.out.print("Expense description: ");
                        String desc = sc.nextLine();
                        System.out.print("Amount: ");
                        double amt = sc.nextDouble();
                        sc.nextLine();
                        expenseDAO.addExpense(new Expense(desc, amt, LocalDate.now(), loggedInUser));
                        System.out.println("Expense added!");
                    }
                    case 3 -> {
                        List<Income> incomes = incomeDAO.getAllIncomes(loggedInUser);
                        System.out.println("ID | Type | Amount | Date");
                        incomes.forEach(System.out::println);
                    }
                    case 4 -> {
                        List<Expense> expenses = expenseDAO.getAllExpenses(loggedInUser);
                        System.out.println("ID | Description | Amount | Date");
                        expenses.forEach(System.out::println);
                    }
                    case 5 -> {
                        double totalIncome = incomeDAO.getTotalIncome(loggedInUser);
                        double totalExpenses = expenseDAO.getTotalExpenses(loggedInUser);
                        double balance = totalIncome - totalExpenses;
                        System.out.println("Remaining Balance: " + balance);
                    }
                    case 6 -> {
                        loggedInUser = null;
                        System.out.println("Logged out!");
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }
        }
    }
}
