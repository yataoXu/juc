package com.evan.juf;

/**
 * @Description
 * @ClassName FilterEmployeeBySalary
 * @Author Evan
 * @date 2020.02.09 12:54
 */
public class FilterEmployeeBySalary implements MyPredicate<Employee> {


    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() > 500;
    }
}
