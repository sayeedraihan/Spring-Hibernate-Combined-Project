package com.example.springhibernate.service;

import com.example.springhibernate.model.Department;

import java.util.List;

import static com.example.springhibernate.dao.AllDepartmentReadQuery.allDepartmentReadQuery;

public class AllDepartmentReadService {
    public static void allDepartmentReadService() {
        List<Department> departments = allDepartmentReadQuery();
        boolean begin = true;
        for(Department dept : departments) {
            if(!begin) {
                System.out.print(", " + dept.getId());
            } else {
                System.out.print(dept.getId());
                begin = false;
            }
        }
    }
}
