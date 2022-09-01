package com.example.springhibernate.dao;

import com.example.springhibernate.model.Department;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AllDepartmentReadQuery {
    public static Transaction transaction = null;
    public static List<Department> departments;
    public static List<Department> allDepartmentReadQuery() {
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
            Root<Department> root = criteriaQuery.from(Department.class);
            criteriaQuery.select(root);
            Query query = session.createQuery(criteriaQuery);
            departments = query.getResultList();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Something went wrong. " + e.getMessage());
            e.printStackTrace();
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            return departments;
        }
    }
}
