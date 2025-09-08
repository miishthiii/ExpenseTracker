package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class IncomeDAO {

    public void addIncome(Income income) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(income);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        }
    }

    public List<Income> getAllIncomes(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Income> query = session.createQuery(
                    "FROM Income WHERE user = :u ORDER BY id", Income.class);
            query.setParameter("u", user);
            return query.list();
        }
    }

    public double getTotalIncome(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery(
                    "SELECT SUM(amount) FROM Income WHERE user = :u", Double.class);
            query.setParameter("u", user);
            Double total = query.uniqueResult();
            return total != null ? total : 0;
        }
    }
}
