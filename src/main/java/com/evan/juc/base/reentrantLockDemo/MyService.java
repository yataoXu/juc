package com.evan.juc.base.reentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName MyService
 * @Author Evan
 * @date 2020.03.27 22:54
 */
public class MyService implements Runnable {

    protected ReentrantLock lock;
    protected Condition conditionA;
    protected Condition conditionB;

    public MyService(ReentrantLock lock,Condition conditionA,Condition conditionB){
        this.lock = lock;
        this.conditionA = conditionA;
        this.conditionB = conditionB;
    }

    public void await_A(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" await_A time is "+System.currentTimeMillis());
            conditionA.await();
            System.out.println(Thread.currentThread().getName()+" after await_A info...");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void await_B(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" await_B time is "+System.currentTimeMillis());
            conditionB.await();
            System.out.println(Thread.currentThread().getName()+" after_B await info...");
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void signal_A(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" signal_A time is "+System.currentTimeMillis());
            conditionA.signal();
        } finally {
            lock.unlock();
        }
    }

    public void signal_B(){
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName()+" signal_B time is "+System.currentTimeMillis());
            conditionB.signal();
        } finally {
            lock.unlock();
        }
    }

    public void run() {
        String tname = Thread.currentThread().getName();
        if (tname.equals("A")) {
            await_A();
        } else if (tname.equals("B")) {
            await_B();
        }
    }

}