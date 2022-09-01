package com.example.springhibernate.dao;

import com.example.springhibernate.model.Employee;
import com.example.springhibernate.util.HibernateUtil;
import org.hibernate.Session;

import java.util.Date;

public class EmployeeInsertionQuery {
    public static void employeeInsertionQuery(String uname, String urole, int wage) {
        Employee emp = new Employee();
        emp.setName(uname);
        emp.setRole(urole);
        emp.setWage(wage);
        emp.setInsertTime(new Date());

        //Get Session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session = HibernateUtil.getSessionFactory().openSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.persist(emp);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Employee ID="+emp.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }
}
