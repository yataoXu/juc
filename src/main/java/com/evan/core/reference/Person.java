package com.evan.core.reference;

/**
 * @Description
 * @ClassName Person
 * @Author Evan
 * @date 2020.06.14 17:47
 */


public class Person {

    private String name = "zhangsan";

    Person(String name){
        this.name = name;
    }
    Person() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
