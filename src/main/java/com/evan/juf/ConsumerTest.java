package com.evan.juf;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Description
 * @ClassName ConsumerTest
 * @Author Evan
 * @date 2020.02.08 21:17
 */
public class ConsumerTest {

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        String[] prefix = {"A", "B"};
        for (int i = 1; i <= 10; i++) {
            employees.add(new Employee(prefix[i % 2] + i, i * 1000));
        }
        employees.forEach(new SalaryConsumer());
        employees.forEach(new NameConsumer());

    }

    static class Employee {
        private String name;
        private int salary;

        public Employee() {
            this.salary = 4000;
        }

        public Employee(String name, int salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return new StringBuilder()
                    .append("name:").append(name)
                    .append(",salary:").append(salary)
                    .toString();
        }
    }

    // 输出需要交税的员工
    static class SalaryConsumer implements Consumer<Employee> {

        @Override
        public void accept(Employee employee) {

            if (new SalaryConsumer1(2000).test(employee)) {
                System.out.println(employee.getName() + "要交税了.");
            }
        }
    }

    static class SalaryConsumer1 implements Predicate<Employee> {

        private int tax;

        public SalaryConsumer1(int tax) {
            this.tax = tax;
        }

        @Override
        public boolean test(Employee employee) {
            return employee.getSalary() > tax;
        }
    }

    // 输出需要名字前缀是‘A’的员工信息
    static class NameConsumer implements Consumer<Employee> {

        @Override
        public void accept(Employee employee) {
            if (employee.getName().startsWith("A")) {
                System.out.println(employee.getName() + " salary is " + employee.getSalary());
            }
        }

    }
}