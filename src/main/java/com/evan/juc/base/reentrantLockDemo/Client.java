package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName Client
 * @Author Evan
 * @date 2020.03.27 23:00
 */
public class Client {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();

        Condition conditionA = lock.newCondition();
        Condition conditionB = lock.newCondition();
        MyService service = new MyService(lock, conditionA, conditionB);

        Thread tA = new Thread(service);
        tA.setName("A");
        tA.start();

        Thread tB = new Thread(service);
        tB.setName("B");
        tB.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.signal_A();
    }
}