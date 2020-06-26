package com.evan.juc.jucUtils;

import java.util.concurrent.CountDownLatch;

/**
 *  班级里面有6个人等六个人全部离开教室班长关门
 */
public class CountDownLatchDemo {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "离开了教室");
                latch.countDown();
            }, i + "").start();

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+"\t 班长关门走人");

    }
}
