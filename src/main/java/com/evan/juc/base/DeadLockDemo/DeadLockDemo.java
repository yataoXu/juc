package com.evan.juc.base.DeadLockDemo;

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
            System.out.println(Thread.currentThread().getName() + "自己持有自己的:" + lockA + "尝试获取" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        synchronized (lockB) {
            System.out.println(Thread.currentThread().getName() + "自己持有自己的:" + lockB + "尝试获取" + lockA);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
        }
        }
}

public class DeadLockDemo {

    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLock(lockA,lockB),"AAA").start();
        new Thread(new HoldLock(lockA,lockB),"BBB").start();
    }
}
