package com.example.springhibernate.dao;

import com.example.springhibernate.model.Department;
import com.example.springhibernate.util.HibernateUtil;
import org.hibernate.Session;

public class DepartmentInsertionQuery {
    public static void departmentInsertionQuery(String did, String dname) {
        Department dept = new Department();
        dept.setId(did);
        dept.setName(dname);

        //Get Session
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session = HibernateUtil.getSessionFactory().openSession();
        //start transaction
        session.beginTransaction();
        //Save the Model object
        session.persist(dept);
        //Commit transaction
        session.getTransaction().commit();
        System.out.println("Department ID="+dept.getId());

        //terminate session factory, otherwise program won't end
        HibernateUtil.getSessionFactory().close();
    }
}
