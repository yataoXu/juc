package com.evan.juc.base.reentrantLockDemo;

/**
 * @Description
 * @ClassName Client1
 * @Author Evan
 * @date 2020.03.28 23:12
 */
public class Client1 {


    public static void main(String[] args) {
        MyService1 myService = new MyService1();

        for (int i = 0; i < 10; i++) {
            new Thread(new Producer(myService)).start();
            new Thread(new Customer(myService)).start();
        }

    }
}
