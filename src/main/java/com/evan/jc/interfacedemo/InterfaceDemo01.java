package com.evan.jc.interfacedemo;

/**
 * @Description
 * @ClassName InterfaceDemo01
 * @Author Evan
 * @date 2020.06.30 22:36
 */
interface InterfaceTest {
    //全局变量默认强制是 public static final
    int a = 10;

    /**
     * 静态方法
     */

    public static void staticMethod() {
        System.out.println("");
    }


    /**
     * 抽象方法
     */
    int abstractMethod();

    /**
     * 抽象内部类，默认强制 public static
     */
    abstract class InnerClass {

        private int a = 20;
        private int b = 10;

        public int sum(){
            return a+b;
        }
    }

    /**
     * 枚举类，默认强制 public static
     */
    enum MyEnum {RED, BLUE, GRREN}

    /**
     * 嵌套接口，默认强制 public static
     */
    interface InnerInteerface {
        void aa();
    }
}

class MyClass implements InterfaceTest {

    @Override
    public int abstractMethod() {//实现抽象方法

        return 0;
    }

}

class InterfaceTest01Client{
    public static void main(String[] args) {
        MyClass my= new MyClass();
        my.abstractMethod();

    }
}