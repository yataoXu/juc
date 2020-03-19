package com.evan.juc.base;

import java.util.concurrent.LinkedBlockingQueue;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description
 * @ClassName TestReadWriteLock
 * @Author Evan
 * @date 2020.03.19 23:05
 */


enum Locker {
    INSTANCE;
    private static final ReadWriteLock lock = new ReentrantReadWriteLock();

    public Lock writeLock() {
        return lock.writeLock();
    }
}

// 资源类
class CountDatas {
    private static int count = 0;

    public static void addCount() {
        Lock writeLock = Locker.INSTANCE.writeLock();
        writeLock.lock();
        count++;
        // 释放锁
        writeLock.unlock();
    }

    public static int getCount() {
        return count;
    }
}

public class TestReadWriteLock {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 20L, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>());

        try {
            for (int i = 0; i < 1000; i++) {
                threadPoolExecutor.submit(() -> {
                    CountDatas.addCount();
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
            System.out.println(CountDatas.getCount());
        }


    }
}
