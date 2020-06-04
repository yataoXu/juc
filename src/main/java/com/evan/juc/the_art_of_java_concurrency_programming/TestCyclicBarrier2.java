package com.evan.juc.the_art_of_java_concurrency_programming;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description
 * @ClassName TestCyclicBarrier2
 * @Author Evan
 * @date 2020.06.04 22:48
 */
public class TestCyclicBarrier2 {
    static CyclicBarrier barrier = new CyclicBarrier(2, new A());

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                barrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(1);

        }).start();
        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(2);
    }

    static class A implements Runnable {


        @Override
        public void run() {
            System.out.println(3);
        }
    }
}
