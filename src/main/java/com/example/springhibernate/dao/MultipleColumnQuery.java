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

public class MultipleColumnQuery {
    public static void multipleColumnQuery() {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root root = criteriaQuery.from(Employee.class);
            criteriaQuery.multiselect(root.get("name"), root.get("role"));
            Query<Object[]> query = session.createQuery(criteriaQuery);
            List<Object[]> list = query.getResultList();
            for(Object[] objects : list) {
                System.out.println("Name: " + objects[0] + " | Role: " + objects[1]);
            }
        } catch (Exception e) {
            System.out.println("Something went wrong." + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }
}
