package com.evan.juc.threadpool;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  自定义ThreadFactory
 */
public class CustomerThreadFactoryDemo {

    public static void main(String[] args) {


        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(), new CustomerThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        for (int i = 0; i < 100; i++) {
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
    }
}
