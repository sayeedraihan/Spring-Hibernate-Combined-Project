package com.example.springhibernate.dao;

import com.example.springhibernate.model.Employee;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AllEmployeeReadQuery {
    private static Transaction transaction = null;
    public static List<Employee> employees = null;
    public static List<Employee> allEmployeeReadQuery() {
        try {
            System.out.println("Inside try block.");
            Session session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Inside try block");
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            employees = query.getResultList();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Something went wrong. " + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            return employees;
        }
    }
}
