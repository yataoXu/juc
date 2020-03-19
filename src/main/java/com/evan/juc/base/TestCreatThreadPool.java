package com.evan.juc.base;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName TestCreatThreadPool
 * @Author Evan
 * @date 2020.03.19 22:20
 */
public class TestCreatThreadPool {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        try {
            for (int i = 0; i < 10; i++) {
                threadPool.submit(() -> {
                            System.out.println(Thread.currentThread().getName() + "线程 starting ....");
                    try {
                        TimeUnit.SECONDS.sleep(2 );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "线程 end....");
                        }
                );
            }

        } finally {
            threadPool.shutdown();
        }


    }
}
