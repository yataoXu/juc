package com.evan.jvm;

/**
 * @Description
 * @ClassName TestTransferValue
 * @Author Evan
 * @date 2020.02.05 23:38
 */

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String name) {
        this.name = name;
    }
}

public class TestTransferValue {

    public void changeValue1(int age) {
        age = 30;
    }

    public void changeValue2(Person person) {
        person.setName("XXXX");
    }

    public void changeValue3(String str) {
        str = "xxx";
    }

    public static void main(String[] args) {
        TestTransferValue test = new TestTransferValue();
        int age = 20;
        test.changeValue1(age);
        System.out.println("age ----------" + age); // 20 基本类型是复印件

        Person person = new Person("aaa");
        test.changeValue2(person);
        System.out.println("Person Name ----------" + person.getName()); // 引用类型 传的是引用

        String str = "abc";
        test.changeValue3(str);
        System.out.println("str ----------" + str); // abc

    }
}
