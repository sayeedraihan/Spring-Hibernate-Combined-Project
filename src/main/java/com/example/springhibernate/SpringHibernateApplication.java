package com.example.springhibernate;

import com.example.springhibernate.dao.SingleEmployeeReadQuery;
import com.example.springhibernate.main.HibernateMain;
import com.example.springhibernate.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Scanner;

import static com.example.springhibernate.service.AggregateOperations.aggregateOperations;
import static com.example.springhibernate.service.AllDepartmentReadService.allDepartmentReadService;
import static com.example.springhibernate.service.EmployeeReadQueryService.employeeReadQueryService;
import static com.example.springhibernate.service.InsertionQueryService.insertionQueryService;

@SpringBootApplication
@RestController
public class SpringHibernateApplication {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        runApp();
        SpringApplication.run(SpringHibernateApplication.class, args);
    }

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

    public static void runApp() {
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
    }


}
