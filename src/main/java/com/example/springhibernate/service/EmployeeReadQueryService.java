package com.example.springhibernate.service;

import com.example.springhibernate.dao.AllWageLevelQuery;
import com.example.springhibernate.dao.DepartmentSingleColumnQuery;
import com.example.springhibernate.model.Employee;

import java.util.List;
import java.util.Scanner;

import static com.example.springhibernate.dao.AllEmployeeReadQuery.allEmployeeReadQuery;
import static com.example.springhibernate.dao.ReadEmployeeSameDepartmentQuery.readEmployeeSameDepartmentQuery;
import static com.example.springhibernate.dao.ReadEmployeeSameSalaryQuery.readEmployeeSameSalaryQuery;
import static com.example.springhibernate.dao.SingleEmployeeReadQuery.singleEmployeeReadQuery;
//import com.example.springhibernate.dao.SingleEmployeeReadQuery;

public class EmployeeReadQueryService {
    public static Scanner scanner = new Scanner(System.in);
    private static void singleEmployeeReedService() {
        System.out.print("Enter Employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Employee employee = singleEmployeeReadQuery(id);
        if(employee == null) {
            System.out.println("There is no employee with id " + id);
        } else {
            System.out.println("Employee Name: " + employee.getName());
            System.out.println("Employee Role: " + employee.getRole());
            System.out.println("Employee Salary: " + employee.getWage());
        }
    }

    public static void allEmployeeReadService() {
        List<Employee> employees = allEmployeeReadQuery();
        /*if(employees == null) {
            System.out.println("No employee acquired.");
            return;
        }*/
        for(Employee emp : employees) {
            System.out.println(emp.getName() + ", " + emp.getRole());
        }
    }

    public static void employeesInDepartmentService() {
        String AllDepartmentId = DepartmentSingleColumnQuery.departmentSingleColumnQuery("ID");
        System.out.print("Select a department (" + AllDepartmentId + "): ");
        String selectedDept = scanner.nextLine();
        List<Employee> employees = readEmployeeSameDepartmentQuery(selectedDept);
        System.out.println("Employee list for " + selectedDept + " are given below.");
        for(Employee emp : employees) {
            System.out.println(emp.getName() + " gets " + emp.getWage() + "/month");
        }
    }

    public static void employeesInSalaryLevelService() {
        String allWageLevel = AllWageLevelQuery.allWageLevelQuery();
        System.out.print("Select you salary range (" + allWageLevel + "): ");
        int selectedLevel = scanner.nextInt();
        scanner.nextLine();
        List<Employee> employees = readEmployeeSameSalaryQuery(selectedLevel);
        System.out.println("Employees with wage " + String.valueOf(selectedLevel) + "/month are given below.");
        for(Employee emp : employees) {
            System.out.println(emp.getName() + ", " + emp.getRole());
        }
    }
    public static void employeeReadQueryService() {
        System.out.print("(1 = Single; 2 = All; 3 = Department wise; 4 = Salary wise)\nChoose an option: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        while(true) {
            if (option == 1) {
                singleEmployeeReedService();
                break;
            } else if(option == 2) {
                allEmployeeReadService();
                break;
            } else if(option == 3) {
                employeesInDepartmentService();
                break;
            } else if(option == 4) {
                employeesInSalaryLevelService();
                break;
            } else {
                System.out.println("Please provide a valid option.");
            }
        }
    }
}
