package com.evan.juc.synchronizedblock;

/**
 * synchronized 修饰实例方法
 * 输出是混合交错的，即只会对对象加锁。
 *
 * @author Evan
 */
public class InnerSynchronizedLock {
    public void add1() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

    public void add2() {
        synchronized (InnerSynchronizedLock.class) {
            for (int i = 100; i < 200; i++) {

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class InnerClient {
    public static void main(String[] args) {
        final InnerSynchronizedLock test = new InnerSynchronizedLock();

        Thread thread1 = new Thread(() -> {
            test.add1();
        }, "synchronized lock of inner method 1");

        Thread thread2 = new Thread(() -> {
            test.add2();

        }, "synchronized lock of inner method 2 ");

        thread1.start();
        thread2.start();

    }
}
