package com.evan.jc.staticdemo;

/**
 * @Description
 * @ClassName StaticDemo
 * @Author Evan
 * @date 2020.07.03 15:45
 */

class Super {
    public static int m = 11;

    static {
        System.out.println("执行了super类静态语句块");
    }
}

class Father extends Super {
    public static int m = 33;
    public static final int b = 33;


    static {
        System.out.println("执行了父类静态语句块");
    }
}
class Child extends Father {

    static {
        System.out.println("执行了子类静态语句块");
    }
}
class StaticTest {
    public static void main(String[] args) {
        System.out.println(Child.m);
        System.out.println(Child.b);
    }
}