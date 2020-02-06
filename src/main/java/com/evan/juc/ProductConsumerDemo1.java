package com.evan.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName ProductConsumerDemo1
 * @Author Evan
 * @date 2020.02.05 13:55
 */


class NewAirCondition {

    private int i = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try {
            // 1. 判断
            while (i != 0){
                condition.await();
            }
            //2. 操作
            i++;
            System.out.println(Thread.currentThread().getName() + "线程\ti的值\t" + i);

            //3. 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {

        lock.lock();
        try {
            // 1. 判断
            while (i == 0){
                condition.await();
            }
            //2. 操作
            i--;
            System.out.println(Thread.currentThread().getName() + "线程\ti的值\t" + i);

            //3. 通知
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}

public class ProductConsumerDemo1 {


    public static void main(String[] args) {


        NewAirCondition aircondition = new NewAirCondition();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {

                try {
                    aircondition.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "AAA").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {

                try {
                    aircondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "BBB").start();


        new Thread(() -> {
            for (int i = 0; i < 40; i++) {

                try {
                    aircondition.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "CCC").start();

    }
}