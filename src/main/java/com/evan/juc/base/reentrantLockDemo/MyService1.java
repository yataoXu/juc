package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName MyService1
 * @Author Evan
 * @date 2020.03.28 23:11
 */
public class MyService1 {

    protected ReentrantLock lock = new ReentrantLock(true);
    protected Condition condition = lock.newCondition();
    protected boolean hasValue = false;

    public void set() {
        try {
            lock.lock();
            while (hasValue == true) {
                System.out.println("有可能★连续打印");
                condition.await();
            }
            System.out.println("★");
            hasValue = true;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void get() {
        try {
            lock.lock();
            while (hasValue == false) {
                System.out.println("有可能☆连续打印");
                condition.await();
            }
            System.out.println("☆");
            hasValue = false;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}