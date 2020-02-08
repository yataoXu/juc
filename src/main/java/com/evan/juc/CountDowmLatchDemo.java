package com.evan.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @Description
 * @ClassName CountDowmLatchDemp
 * @Author Evan
 * @date 2020.02.08 11:40
 */
public class CountDowmLatchDemo {

    public static void main(String[] args) {


        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t 班长关门走人");
    }
}


