package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName MyService
 * @Author Evan
 * @date 2020.03.27 22:54
 */
public class MyService {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();


    private int shareData;


    public void await() {
        try {
            lock.lock();
            System.out.println("awaits时间为" + System.currentTimeMillis());
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            System.out.println("await锁被释放了！");
        }

    }

    public void signal() {
        try {
            lock.lock();
            System.out.println("signal时间为" + System.nanoTime());
            condition.signal();
            for (int i = 0; i < 5; i++) {
                shareData++;
                System.out.println("ThreadName = " + Thread.currentThread().getName() + " " + (i + 1));
            }
        } finally {
            lock.unlock();
            System.out.println("await锁被释放了！");
        }

    }

    public int getShareData() {
        return shareData;
    }
}
