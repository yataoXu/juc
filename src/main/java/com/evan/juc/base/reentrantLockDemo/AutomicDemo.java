package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName AutomicDemo
 * @Author Evan
 * @date 2020.03.28 12:53
 */
public class AutomicDemo {


    private static int count = 0;
    private static Lock lock = new ReentrantLock();


    public static void inc() {
        try {
            lock.lock();
            try {
                TimeUnit.MILLISECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            new Thread(()->{AutomicDemo.inc();}).start();
        }

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(AutomicDemo.count);
    }
}
