package com.evan.juf;

/**
 * @Description
 * @ClassName Ddemo1
 * @Author Evan
 * @date 2020.02.09 11:44
 */
interface Person2 {
    public void eat();
}


public class Demo1 {

    public static void main(String[] args) {
        Person2 p = new Person2() {
            public void eat() {
                System.out.println("eat something");
            }
        };
        p.eat();
    }
}