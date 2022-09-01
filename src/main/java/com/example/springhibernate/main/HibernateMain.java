package com.example.springhibernate.main;

import java.util.Scanner;

import static com.example.springhibernate.service.AggregateOperations.aggregateOperations;
import static com.example.springhibernate.service.AllDepartmentReadService.allDepartmentReadService;
import static com.example.springhibernate.service.EmployeeReadQueryService.employeeReadQueryService;
import static com.example.springhibernate.service.InsertionQueryService.insertionQueryService;

public class HibernateMain {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("(1 = insert; 2 = Read Employee; 3 = Read Departments; 4 = Aggregates on Wages;)\nPick an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1) {
                insertionQueryService();
            } else if (option == 2) {
                employeeReadQueryService();
            } else if (option == 3) {
                allDepartmentReadService();
                System.out.println();
            } else if (option == 4) {
                aggregateOperations();
            } else if(option == 0) {
                break;
            }
        }
//        AllDepartmentReadService.allDepartmentReadService();
    }
}
