package com.evan.jc.reflect;

/**
 * @Description
 * @ClassName RealSubject
 * @Author Evan
 * @date 2020.07.04 13:20
 */
public class RealSubject implements Subject {
    @Override
    public void rent() {
        System.out.println("I want to rent my house");
    }

    @Override
    public void hello(String str) {
        System.out.println("hello: " + str);
    }
}