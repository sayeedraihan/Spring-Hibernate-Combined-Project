package com.example.springhibernate.dao;

import com.example.springhibernate.model.Department;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DepartmentSingleColumnQuery {
    public static String departmentSingleColumnQuery(String columnName) {
        Transaction transaction = null;
        String allDepartment = "";
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
            Root<Department> root = criteriaQuery.from(Department.class);
            criteriaQuery.select(root.get(columnName));
            Query<String> query = session.createQuery(criteriaQuery);
            List<String> list = query.getResultList();
            boolean begin = true;
            for(String name : list) {
                if(begin) {
//                    System.out.print(name);
                    allDepartment = allDepartment + name;
                    begin = false;
                } else {
//                    System.out.print(", " + name);
                    allDepartment = allDepartment + ", " + name;
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
            return allDepartment;
        }
    }
}
