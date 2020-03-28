package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 利用condition实现顺序执行
 *
 */
public class MyService2 implements Runnable {
    protected ReentrantLock lock;
    protected Condition signalCondition;// 在某个线程里负责等待
    protected Condition awaitCondition;// 在某个线程里负责唤醒

    public MyService2(ReentrantLock lock, Condition signalCondition, Condition awaitCondition) {
        this.lock = lock;
        this.signalCondition = signalCondition;
        this.awaitCondition = awaitCondition;
    }

    public void print(Condition signalCondition, Condition awaitCondition) throws InterruptedException {
        lock.lock();
        for (int j = 0; j < 10; j++) {
            for (int i = 1; i < 4; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
            }
            System.out.println("");
            signalCondition.signal();
            awaitCondition.await();
        }
        lock.unlock();
    }

    public void run() {
        try {
            print(signalCondition, awaitCondition);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
