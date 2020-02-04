package com.evan.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * 多线程的核心：
 * 线程  操作  资源类
 * @ClassName LockDemo
 * @Author Evan
 * @date 2020.02.03 17:39
 */


// 资源类 = 类变量 + 操作类变量的方法
class Ticket {
    private int count = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出的是\t" + (count--) + "\t还剩\t" + count);
            } else {
                System.out.println(Thread.currentThread().getName() + "\t没票了");
            }
        } finally {
            lock.unlock();
        }
    }
}

public class LockDemo {

    public static void main(String[] args) {

        final Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "AAA").start();


        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "BBB").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "CCCC").start();
    }
}
