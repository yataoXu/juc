package com.evan.juf;

/**
 * @Description
 * @ClassName Demo
 * @Author Evan
 * @date 2020.02.09 11:22
 */
abstract class Person1 {
    public abstract void eat();
}

class Child extends Person1 {
    public void eat() {
        System.out.println("eat something");
    }
}


public class Demo {
    public static void main(String[] args) {
        Person1 p = new Child();
        p.eat();
    }

}
