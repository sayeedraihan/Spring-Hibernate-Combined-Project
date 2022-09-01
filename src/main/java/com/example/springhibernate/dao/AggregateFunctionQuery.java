package com.example.springhibernate.dao;

import com.example.springhibernate.model.Employee;
import com.example.springhibernate.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AggregateFunctionQuery {
    public static void aggregateFunctionQuery() {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery1 = criteriaBuilder.createQuery(Long.class);
            Root<Employee> root1 = criteriaQuery1.from(Employee.class);
            criteriaQuery1.select(criteriaBuilder.count(root1));
            Query<Long> query1 = session.createQuery(criteriaQuery1);
            long count = query1.getSingleResult();
            System.out.println("Total Employee in database: " + count);

            CriteriaQuery<Integer> criteriaQuery2 = criteriaBuilder.createQuery(Integer.class);
            Root<Employee> root2 = criteriaQuery2.from(Employee.class);
            criteriaQuery2.select(criteriaBuilder.max(root2.get("wage")));
            Query<Integer> query2 = session.createQuery(criteriaQuery2);
            int maxWage = query2.getSingleResult();
            System.out.println("Maximum wage: " + maxWage);

            CriteriaQuery<Double> criteriaQuery3 = criteriaBuilder.createQuery(Double.class);
            Root<Employee> root3 = criteriaQuery3.from(Employee.class);
            criteriaQuery3.select(criteriaBuilder.avg(root3.get("wage")));
            Query<Double> query3 = session.createQuery(criteriaQuery3);
            double averageWage = query3.getSingleResult();
            System.out.println("Average wage: " + averageWage);

            CriteriaQuery<Integer> criteriaQuery4 = criteriaBuilder.createQuery(Integer.class);
            Root<Employee> root4 = criteriaQuery4.from(Employee.class);
            criteriaQuery4.select(criteriaBuilder.sum(root4.get("wage")));
            Query<Integer> query4 = session.createQuery(criteriaQuery4);
            int totalWageBill = query4.getSingleResult();
            System.out.println("Total wage cost: " + totalWageBill);

            CriteriaQuery<Long> criteriaQuery5 = criteriaBuilder.createQuery(Long.class);
            Root<Employee> root5 = criteriaQuery5.from(Employee.class);
            criteriaQuery5.select(criteriaBuilder.countDistinct(root5.get("wage")));
            Query<Long> query5 = session.createQuery(criteriaQuery5);
            Long numberOfWageLevel = query5.getSingleResult();
            System.out.println("Total number of wage level: " + numberOfWageLevel);
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
