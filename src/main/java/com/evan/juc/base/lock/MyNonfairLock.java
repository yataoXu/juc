package com.evan.juc.base.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class MyNonfairLock {
    private static ReentrantLock lock = new ReentrantLock();

    private static int count = 0;


    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(10);


        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName() + "启动");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到锁开始操作资源");
                count++;
            } finally {
                lock.unlock();
                latch.countDown();
            }
        };


        List<Thread> list = new ArrayList();
        for (int i = 0; i < 10; i++) {


            Thread thread = new Thread(runnable);
            list.add(thread);

        }

        for (Thread thread : list) {
            thread.start();
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=================");
        System.out.println(count);

    }
}