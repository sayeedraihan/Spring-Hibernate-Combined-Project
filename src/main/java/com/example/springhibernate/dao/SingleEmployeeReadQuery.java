package com.example.springhibernate.dao;

import com.example.springhibernate.model.Employee;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SingleEmployeeReadQuery {
    public static Employee singleEmployeeReadQuery(int eid) {
        Transaction transaction = null;
        Employee employee = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
            Root<Employee> root = criteriaQuery.from(Employee.class);
            criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("ID"), eid));
            Query query = session.createQuery(criteriaQuery);
            employee = (Employee) query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Something is wrong. " + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            if(employee == null) {
                System.out.println("Employee is still null");
            }
            return employee;
        }
    }
}
