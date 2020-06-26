package com.evan.juc.synchronizedblock;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 修饰实例方法
 * 输出是混合交错的，即只会对对象加锁。
 */
public class InstanceMethodSynchronizedLock {
    public synchronized void add1() {
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public void add2() {
        for (int i = 100; i < 200; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class Client {
    public static void main(String[] args) {
        final InstanceMethodSynchronizedLock Test = new InstanceMethodSynchronizedLock();

        Thread thread1 = new Thread(() -> {
            Test.add1();
        }, "synchronized lock of instance method");

        Thread thread2 = new Thread(() -> {
            Test.add2();

        }, " instance method ");

        thread1.start();
        thread2.start();

    }
}
