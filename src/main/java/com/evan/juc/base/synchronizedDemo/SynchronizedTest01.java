package com.evan.juc.base.synchronizedDemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 问题：
 * 三个人去买票，张某有20元，李某10元。赵某5元。电影票5元一张，售货员只有3张5元的。
 * <p>
 * * 思路：
 * 张某买了 票就会少3张5元的
 * 李某买了 票就会少2张5元的
 * 赵某买了 票就会多1张5元的
 * <p>
 * 所以有三种情况：
 * 一。赵某先买，张李都可以买
 * 二。张某先买，此时李某买不了，只能等待赵某买了，再买
 * 三。李某先买，此时张某买不了，只能等待赵某买了，再买
 * <p>
 * 静态常量：售货员总钱数 sum=3
 * <p>
 * 1.创建三个线程分别为张某，李某，赵某
 * 2.判断此时sum值，合适就买票，减去相应的钱数，不合适就等待。
 */


/**
 * 李某10元
 */
class Li extends Thread {

    @Override
    public void run() {

        int money = 10;
        // 买到的票数
        int num = 0;
        // 因为要判断等待和唤醒，并且操作sum，所以在此处加锁
        synchronized (SynchronizedTest01.lock) {
            while (true) {
                if (SynchronizedTest01.ticket > 0) {
                    money -= 5;
                    num++;
                    SynchronizedTest01.ticket--;
                    if (money < 0) {
                        System.out.println("李某买了" + num + "张票");

                    }
                } else {
                    System.out.println("李某没有买到票");
                    break;
                }

            }
        }

    }
}

/**
 * 赵某5元
 */
class Zhao extends Thread {
    @Override
    public void run() {
        int money = 5;
        // 买到的票数
        int num = 0;
        // 因为要判断等待和唤醒，并且操作sum，所以在此处加锁
        synchronized (SynchronizedTest01.lock) {
            while (true) {

                money -= 5;
                num++;
                SynchronizedTest01.ticket--;
                if (money < 0) {
                    System.out.println("张某买了" + num + "张票");
                } else {
                    System.out.println("张某没有买到票");
                    break;
                }

            }
        }

    }
}

/**
 * 张某有20元
 */
class Zhang extends Thread {

    @Override
    public void run() {
        int money = 20;
        // 买到的票数
        int num = 0;
        // 因为要判断等待和唤醒，并且操作sum，所以在此处加锁
        synchronized (SynchronizedTest01.lock) {
            while (true) {

                money -= 5;
                num++;
                SynchronizedTest01.ticket--;
                if (money < 0) {
                    System.out.println("张某买了" + num + "张票");
                } else {
                    System.out.println("张某没有买到票");
                    break;
                }

            }
        }
    }
}


public class SynchronizedTest01 {
    public static int ticket = 3;
    /**
     * 创建一个锁
     */
    public static Object lock = new Object();
    // 创建一个集合用于存储购票人的线程
    private static List<Thread> list = new ArrayList<>();

    public static void main(String[] args) {


        list.add(new Li());
        list.add(new Zhang());
        list.add(new Zhao());

        Iterator<Thread> iterator = list.iterator();

        while (iterator.hasNext()) {
            // 获得线程
            Thread current = iterator.next();

            //启动线程
            current.start();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
