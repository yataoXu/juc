package com.evan.juc.base.deadlock;


import java.util.Date;
import java.util.concurrent.TimeUnit;

class LockA implements Runnable {

    @Override
    public void run() {

        System.out.println(new Date().toString() + " LockA 开始执行");

        while (true) {
            synchronized (DeadLockDemo2.obj1) {
                System.out.println(new Date().toString() + " LockA 锁住 obj1");
                try {
                    // 此处等待是给B能锁住机会
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    i *= i;
                }

                synchronized (DeadLockDemo2.obj2) {
                    System.out.println(new Date().toString() + " LockA 锁住 obj2");
                    // 为测试，占用了就不放
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

class LockB implements Runnable {

    @Override
    public void run() {
        System.out.println(new Date().toString() + " LockB 开始执行");
        while (true) {
            synchronized (DeadLockDemo2.obj2) {
                System.out.println(new Date().toString() + " LockB 锁住 obj2");
                try {
                    // 此处等待是给A能锁住机会
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    i *= i;
                }
                synchronized (DeadLockDemo2.obj1) {
                    System.out.println(new Date().toString() + " LockB 锁住 obj1");
                    // 为测试，占用了就不放
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

public class DeadLockDemo2 {

    public static String obj1 = "obj1";
    public static String obj2 = "obj2";

    public static void main(String[] args) {
        new Thread(new LockA(), "AAA").start();
        new Thread(new LockB(), "BBB").start();
    }
}
