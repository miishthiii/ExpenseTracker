package com.example;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "expenses_seq")
    @SequenceGenerator(name = "expenses_seq", sequenceName = "EXPENSES_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double amount;

    @Column(name = "expense_date")
    private LocalDate expenseDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Expense() {}

    public Expense(String description, double amount, LocalDate expenseDate, User user) {
        this.description = description;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public void setExpenseDate(LocalDate expenseDate) { this.expenseDate = expenseDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return id + " | " + description + " | " + amount + " | " + expenseDate;
    }
}
