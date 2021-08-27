package com.rfs.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Employee {
    private String name;
    private Integer age;

    public static void main(String[] args) {
        List<Employee> list= Arrays.asList(new Employee("张三",16),
                new Employee("李四",20));
        List<Employee> employees = filterEmployee(list, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.age > 18;
            }
        });
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        List<Employee> employees1 = filterEmployee(list, (employee -> employee.age > 18));
        for (Employee employee : employees1) {
            System.out.println(employee);
        }
        list.stream().filter(e->e.getAge()>18).limit(2).forEach(System.out::println);
    }
    public static  List<Employee> filterEmployee(List<Employee>list,MyPredicate<Employee> dicate){
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : list) {
            if (dicate.test(employee)){
                employees.add(employee);
            }
        }
        return employees;
    }
}
