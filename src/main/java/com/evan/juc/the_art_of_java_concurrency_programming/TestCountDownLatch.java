package com.evan.juc.the_art_of_java_concurrency_programming;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @ClassName TestCountDownLatch
 * @Author Evan
 * @date 2020.06.04 22:37
 */
public class TestCountDownLatch {

    static CountDownLatch latch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            System.out.println(1);
            latch.countDown();
            System.out.println(2);
            latch.countDown();

        }).start();

        latch.await();

        System.out.println(3);
    }

}
