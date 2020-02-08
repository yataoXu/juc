package com.evan.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description
 * @ClassName CyclicBarrierDemo
 * @Author Evan
 * @date 2020.02.08 12:03
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier  cycles = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 7  ; i++) {

            int temp = i ;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第"+ temp +"颗龙珠" );
                try {
                    cycles.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }

    }
}
