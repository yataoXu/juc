package com.evan.juc.synchronizedblock;

/**
 * synchronized 修饰实例方法
 * 输出是混合交错的，即只会对对象加锁。
 */
public class InstanceMethodSynchronizedLock01 {
    public synchronized void add1() {
        for (int i = 0; i < 100; i++) {

            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public synchronized void add2() {
        for (int i = 100; i < 200; i++) {

            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class InnerSynchronizedClient {
    public static void main(String[] args) {
        final InstanceMethodSynchronizedLock01 test = new InstanceMethodSynchronizedLock01();

        Thread thread1 = new Thread(() -> {
            test.add1();
        }, "synchronized lock of instance method 1");

        Thread thread2 = new Thread(() -> {
            test.add2();

        }, "synchronized lock of instance method 2 ");

        thread1.start();
        thread2.start();

    }
}
