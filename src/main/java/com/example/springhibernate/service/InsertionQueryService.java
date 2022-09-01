package com.example.springhibernate.service;

import com.example.springhibernate.dao.DepartmentSingleColumnQuery;

import java.util.Scanner;

import static com.example.springhibernate.dao.DepartmentInsertionQuery.departmentInsertionQuery;
import static com.example.springhibernate.dao.EmployeeInsertionQuery.employeeInsertionQuery;

public class InsertionQueryService {
    public static Scanner scanner = new Scanner(System.in);

    public static void selectRecordType() {
        scanner = new Scanner(System.in);
        while(true) {
            System.out.print("Choose Record type (1 = Employee; 2 = Department): ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if(option == 1) {
                provideEmployeeFields();
                break;
            } else if (option == 2) {
                provideDepartmentFields();
                break;
            } else {
                System.out.println("Please provide a valid option.");
            }
        }
    }

    public static void provideEmployeeFields() {
        String AllDepartmentId = DepartmentSingleColumnQuery.departmentSingleColumnQuery("ID");
        System.out.println("Please provide with Employee Name, Role and Salary");
        System.out.print("Employee Name: ");
        String uname = scanner.nextLine();
        System.out.print("Employee Role (" + AllDepartmentId + "): ");
        String urole = scanner.nextLine();
        System.out.print("Employee Wage: ");
        int wage = scanner.nextInt();
        employeeInsertionQuery(uname, urole, wage);
    }

    public static void provideDepartmentFields() {
        System.out.println("Please provide Department ID and Department Name");
        System.out.print("Department ID: ");
        String dId = scanner.nextLine();
        System.out.print("Department Name: ");
        String dName = scanner.nextLine();
        departmentInsertionQuery(dId, dName);
    }

    public static void insertionQueryService() {
        selectRecordType();
    }
}
