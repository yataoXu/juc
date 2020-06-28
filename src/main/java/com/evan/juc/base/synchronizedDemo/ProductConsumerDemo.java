package com.evan.juc.base.synchronizedDemo;

/**
 * @Description 题目： 两个线程，一个线程对共享变量+1 一个线程对共享变量-1，共享变量的取值范围(0,1)
 * @ClassName ProductConsumerDemo
 * @Author Evan
 * @date 2020.02.05 11:36
 */


//资源类
class Aircondition {

    private int i = 0;

    public synchronized void increment() throws InterruptedException {
        // 判断
        while (i != 0) {
            this.wait();
        }

        // 操作
        i++;
        System.out.println(Thread.currentThread().getName() + "线程\ti的值\t" + i);

        // 通知
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException {
        // 判断
        while (i == 0) {
            this.wait();
        }
        // 操作
        i--;
        System.out.println(Thread.currentThread().getName() + "线程\ti的值\t" + i);

        // 通知
        this.notifyAll();
    }

}

public class ProductConsumerDemo {


    public static void main(String[] args) {

        Aircondition aircondition = new Aircondition();

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
