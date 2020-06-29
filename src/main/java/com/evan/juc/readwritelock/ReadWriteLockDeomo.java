package com.evan.juc.readwritelock ;

import java.util.concurrent.locks.ReadWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @Description
 * @ClassName ReadWriteLockDeomo
 * @Author Evan
 * @date 2020.02.08 14:24
 */
public class ReadWriteLockDeomo {

    public static void main(String[] args) {

        MyCache mycache = new MyCache();


        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                mycache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {

            final int temp = i;
            new Thread(() -> {
                mycache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
        try {
            TimeUnit.SECONDS.sleep(3);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入数据成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 读取数据");
        readWriteLock.readLock().lock();
        try {
            TimeUnit.SECONDS.sleep(3);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取数据完成" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}
