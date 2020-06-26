package com.evan.juc.base.deadlock;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName DeadLockDemo
 * @Author Evan
 * @date 2020.03.28 15:43
 */

class HoldLock extends Thread {

    private String lockA;
    private String lockB;

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "获得锁" + lockA + ",接下来需要获取" + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "获得锁" + lockA + ",获取锁" + lockB);
            }
        }


    }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA, lockB), "AAA").start();
        new Thread(new HoldLock(lockB, lockA), "BBB").start();
    }
}
