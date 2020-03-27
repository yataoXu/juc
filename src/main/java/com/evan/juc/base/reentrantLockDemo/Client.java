package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName Client
 * @Author Evan
 * @date 2020.03.27 23:00
 */
public class Client {
    public static void main(String[] args) {

        MyService myService = new MyService();
        MyThread a1 = new MyThread(myService);


        a1.start();

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myService.signal();
        System.out.println(myService.getShareData());

    }
}
