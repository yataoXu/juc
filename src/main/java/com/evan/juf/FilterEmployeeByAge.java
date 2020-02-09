package com.evan.juf;

/**
 * @Description
 * @ClassName FilterEmployeeByAge
 * @Author Evan
 * @date 2020.02.09 12:45
 */
public class FilterEmployeeByAge implements MyPredicate<Employee>{
    @Override
    public boolean test(Employee o) {
        return  o.getAge()>38;
    }
}
