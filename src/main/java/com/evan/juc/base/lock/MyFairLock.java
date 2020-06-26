package com.evan.juc.base.lock;

import java.util.concurrent.CopyOnWriteArraySet;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName ReentrantLockDemo
 * @Author Evan
 * @date 2020.03.25 17:39
 */
public class MyFairLock extends Thread {

    private ReentrantLock lock = new ReentrantLock(true);


    public void fairLock() {

        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "正在持有锁");
        } finally {
//            System.out.println(Thread.currentThread().getName() + "释放了锁");
            lock.unlock();
        }
    }

    public static void main(String[] args) {


        Map<String, String> map = new HashMap();
        map.put("a", "a");


        CopyOnWriteArraySet copy = new CopyOnWriteArraySet();
        MyFairLock myFairLock = new MyFairLock();
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            myFairLock.fairLock();
        };
        Thread[] thread = new Thread[10];
        for (int i = 0; i < 10; i++) {
            thread[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            thread[i].start();
        }
    }
}