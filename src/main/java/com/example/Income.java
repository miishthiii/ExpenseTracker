package com.example;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "incomes")
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "incomes_seq")
    @SequenceGenerator(name = "incomes_seq", sequenceName = "INCOMES_SEQ", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double amount;

    @Column(name = "income_date")
    private LocalDate incomeDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Income() {}

    public Income(String type, double amount, LocalDate incomeDate, User user) {
        this.type = type;
        this.amount = amount;
        this.incomeDate = incomeDate;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDate getIncomeDate() { return incomeDate; }
    public void setIncomeDate(LocalDate incomeDate) { this.incomeDate = incomeDate; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    @Override
    public String toString() {
        return id + " | " + type + " | " + amount + " | " + incomeDate;
    }
}
