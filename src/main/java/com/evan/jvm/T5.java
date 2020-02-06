package com.evan.jvm;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName T5
 * @Author Evan
 * @date 2020.02.06 18:56
 */
class MyNumber {
    volatile int num = 10;

    public void add() {
        this.num = 1024;
    }
}

public class T5 {

    public static void main(String[] args) {

        MyNumber my = new MyNumber();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t thread come in");

            //线程暂停一会
            try {
                TimeUnit.SECONDS.sleep(3);
                my.add();
                System.out.println(Thread.currentThread().getName() + "\t thread update num， num value is  " + my.num);
                System.out.println(Thread.currentThread().getName() + "\t thread end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        while (my.num == 10) {
            // 需要一种通知机制来告诉main线程，num的值已经不为10，退出while
        }
        System.out.println(Thread.currentThread().getName() + "\t missin is over");
    }
}
