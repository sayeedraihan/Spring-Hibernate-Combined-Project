package com.example.springhibernate.dao;

import com.example.springhibernate.model.Department;
import com.example.springhibernate.model.Employee;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class InnerJoinQuery {
    public static void innerJoinQuery() {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Employee> rootE = criteriaQuery.from(Employee.class);
            Root<Department> rootD = criteriaQuery.from(Department.class);
            criteriaQuery.multiselect(rootE, rootD);
            criteriaQuery.where(criteriaBuilder.equal(rootE.get("role"), rootD.get("id")));
            Query<Object[]> query = session.createQuery(criteriaQuery);
            List<Object[]> list = query.getResultList();
            for(Object[] objects : list) {
                Employee employee = (Employee) objects[0];
                Department department = (Department) objects[1];
                System.out.println("Employee name: " + employee.getName() + " \t Department name: " + department.getName());
            }
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Something went wrong. " + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }
}
