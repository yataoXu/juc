package com.evan.juc.base.synchronizedDemo.synchronizedblock;

public class InstanceMethodSynchronizedLock {
    public synchronized void add() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public synchronized void add2() {
        for (int i = 100; i < 200; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public void addX1() {
        for (int i = 200; i < 300; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

}

class Client {
    public static void main(String[] args) {
        final InstanceMethodSynchronizedLock Test = new InstanceMethodSynchronizedLock();

        Thread thread1 = new Thread(() -> {
            Test.add();
        }, "synchronized lock of instance method");

//        Thread thread2 = new Thread(() -> {
//            Test.add2();
//
//        }, " instance method ");

        Thread thread3 = new Thread(() -> {
            Test.add();

        }, "synchronized lock of instance method 2");
//
//        Thread thread4 = new Thread(() -> {
//            Test.addX1();
//
//        }, "thread4");

        thread1.start();
//        thread2.start();
        thread3.start();
//        thread4.start();
    }
}
