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

public class ReadEmployeeSameDepartmentQuery {
    public static List<Employee> readEmployeeSameDepartmentQuery(String dId) {
        List<Employee> employees = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("role"), dId));
            Query query = session.createQuery(criteriaQuery);
            employees = query.getResultList();
            transaction.commit();
            session.close();
            return employees;
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
