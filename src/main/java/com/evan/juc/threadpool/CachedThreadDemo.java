package com.evan.juc.threadpool;

import java.util.concurrent.*;

/**
 * @Description
 * @ClassName CachedThreadDemo
 * @Author Evan
 * @date 2020.02.08 18:30
 */
public class CachedThreadDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程




//
//        threadPool.submit(() -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(Thread.currentThread().getName() + ": " + i);
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//
//        threadPool.submit(() -> {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(Thread.currentThread().getName() + ": " + i);
//                try {
//                    TimeUnit.SECONDS.sleep(3);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        });
//        threadPool.shutdown();
        try {
            for (int i = 0; i < 10; i++) {

                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 执行");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 执行完成");

                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}
