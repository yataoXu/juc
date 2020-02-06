package com.evan.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 多线程之间按照顺序调用：
 * <p>
 * 实现：
 * 三个线程AA，BB CC
 * <p>
 * AA 打印5次 BB打印10次 CC 打印15次
 * 紧接着
 * AA 打印5次 BB打印10次 CC 打印15次
 * 共10次
 */


class ShareData {

    private int flag = 1; // A 1 B 2 C 3
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 1) {
                condition1.await();
            }
            // 操作
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "线程\t打印" + i);
            }
            // 通知
            flag = 2;
            condition2.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print10() throws InterruptedException {

        lock.lock();
        try {
            // 判断
            while (flag != 2) {
                condition2.await();
            }
            // 操作
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "线程\t打印" + i);
            }
            // 通知
            flag = 3;
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    public void print15() throws InterruptedException {
        lock.lock();
        try {
            // 判断
            while (flag != 3) {
                condition3.await();
            }
            // 操作
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "线程\t打印" + i);
            }
            // 通知
            flag = 1;
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }
}

public class ConditionDemo {

    public static void main(String[] args) {

        ShareData shareData = new ShareData();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.print5();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.print10();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    shareData.print15();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CC").start();
    }
}
