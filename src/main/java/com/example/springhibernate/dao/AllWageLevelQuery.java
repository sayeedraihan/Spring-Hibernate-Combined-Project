package com.example.springhibernate.dao;

import com.example.springhibernate.model.Employee;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class AllWageLevelQuery {

    public static String allWageLevelQuery() {
        Transaction transaction = null;
        String allWageLevel = "";
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root.get("wage")).distinct(true);
            Query query = session.createQuery(criteriaQuery);
            List<Integer> list = query.getResultList();
            boolean begin = true;
            for(int wage : list) {
                if(begin) {
//                    System.out.print(name);
                    allWageLevel = allWageLevel + String.valueOf(wage);
                    begin = false;
                } else {
//                    System.out.print(", " + name);
                    allWageLevel = allWageLevel + ", " + String.valueOf(wage);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Something went wrong." + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            return allWageLevel;
        }
    }
}
