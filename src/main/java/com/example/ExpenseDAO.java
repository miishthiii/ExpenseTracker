package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ExpenseDAO {

    // Add expense
    public void addExpense(Expense expense) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(expense);  // persist instead of save
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    // Get all expenses for a user
    public List<Expense> getAllExpenses(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Expense> query = session.createQuery(
                    "FROM Expense e WHERE e.user = :u ORDER BY e.id", Expense.class);
            query.setParameter("u", user);
            return query.list();
        }
    }

    // Get total expenses for a user
    public double getTotalExpenses(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(e.amount) FROM Expense e WHERE e.user = :u", Double.class);
            query.setParameter("u", user);
            Double total = query.uniqueResult();
            return total != null ? total : 0;
        }
    }
}
