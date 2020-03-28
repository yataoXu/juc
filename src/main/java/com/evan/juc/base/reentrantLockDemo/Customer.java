package com.evan.juc.base.reentrantLockDemo;

/**
 * @Description
 * @ClassName Customer
 * @Author Evan
 * @date 2020.03.28 23:15
 */
public class Customer implements Runnable {

    protected MyService1 myService;

    public Customer(MyService1 myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            myService.get();
        }
    }
}
