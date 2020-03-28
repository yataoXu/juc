package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName Client2
 * @Author Evan
 * @date 2020.03.28 23:32
 */
public class Client2 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        Condition conditionC = lock.newCondition();

        new Thread(new MyService2(lock, conditionB, conditionA), "thread a").start();
        new Thread(new MyService2(lock, conditionC, conditionB), "thread b").start();
        new Thread(new MyService2(lock, conditionA, conditionC), "thread c").start();

    }
}

