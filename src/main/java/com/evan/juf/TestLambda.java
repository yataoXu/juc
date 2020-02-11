package com.evan.juf;

import java.util.List;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.junit.Test;

import java.util.Comparator;

/**
 * @Description
 * @ClassName TestLambda
 * @Author Evan
 * @date 2020.02.09 11:53
 */
public class TestLambda {


    List<Employee> employees = Arrays.asList(
            new Employee("张三", 19, 55.55),
            new Employee("李四", 19, 1255.55),
            new Employee("王五", 19, 545.55),
            new Employee("张飞", 29, 535.55),
            new Employee("关羽", 39, 255.55),
            new Employee("刘备", 59, 155.55)
    );

    @Test
    public void test1() {
        Comparator<Integer> com = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> ts = new TreeSet<>(com);
    }

    @Test
    public void test2() {
        Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
        ;
    }

    @Test
    public void test3() {


        // 策略模式
        List<Employee> employees1 = filterEmployee(employees, new FilterEmployeeByAge());
        System.out.println("两种遍历方法");
        for (Employee employee : employees1) {
            System.out.println(employee);
        }
        ;
        System.out.println("++++++++++++++++++++++");
        employees1.stream().forEach(System.out::println);

        System.out.println("======根据salary过滤=========");
        List<Employee> employeesBySalary = filterEmployee(employees, new FilterEmployeeBySalary());
        employeesBySalary.stream().forEach(System.out::println);


    }

    public List<Employee> filterEmployee(List<Employee> list, MyPredicate<Employee> mp) {
        List<Employee> emps = new ArrayList<>();

        for (Employee employee : list) {
            if (mp.test(employee)) {
                emps.add(employee);
            }
        }
        return emps;
    }


    // 匿名内部类
    @Test
    public void test04() {
        List<Employee> list = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() <= 400;
            }
        });

        list.stream().forEach(System.out::println);
    }

    @Test
    public void test05(){
        List<Employee> list = filterEmployee(employees,(employee)->{return employee.getSalary()<=400;});

        list.stream().forEach(System.out::println);
    }

}


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name) {
        this.name = name;
    }
}