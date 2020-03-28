package com.evan.juc.base.reentrantLockDemo;

/**
 * @Description
 * @ClassName Producer
 * @Author Evan
 * @date 2020.03.28 23:12
 */
public class Producer implements  Runnable {


    protected MyService1 myService;

    public Producer(MyService1 myService) {
        this.myService = myService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            myService.set();
        }
    }
}
