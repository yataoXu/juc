package com.evan.juf;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description
 * @ClassName FunctionDemo
 * @Author Evan
 * @date 2020.02.08 20:55
 */
public class FunctionDemo {

    public static void main(String[] args) {

        // 传入一个String，返回一个Integer类型
        Function<String,Integer> function = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return 1024;
            }
        };

        System.out.println(function.apply("abc"));


        // 使用lambda
        Function<String,Integer> function1 = s -> {
            return s.length();
        };

        System.out.println(function1.apply("abc"));


        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return false;
            }
        };

        System.out.println(predicate.test("abc"));

        Predicate<String> predicate1 = s -> {
            return s.isEmpty();
        };

        System.out.println(predicate1.test("abc"));


        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };

        consumer.accept("abc");

        Consumer<String> consumer1 = s -> {
            System.out.println(s);
        };

        consumer1.accept("abc");


        Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "1024";
            }
        };

        System.out.println(supplier.get());


        Supplier<String> supplier1 = () ->{
        return "1024";
        };

        System.out.println(supplier1.get());
    }


}

class Person{
    private int id;
    private String username;
    private int age;


    public Person(int id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
