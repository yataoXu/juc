package com.evan.juc.the_art_of_java_concurrency_programming;
import	java.util.concurrent.TimeUnit;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName WaitNotify
 * @Author Evan
 * @date 2020.06.02 18:58
 */
public class WaitNotify {

    private static Boolean flag = true;
    private static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException{
        Thread waitThread = new Thread(new Waiting(),"waitThread");
        waitThread.start();
        TimeUnit.SECONDS.sleep(1);

        Thread notifyThread = new Thread(new Notify(),"notifyThread");
        notifyThread.start();

    }

    static class Waiting implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                // 当条件不满足时，进入等待状态，此时 flag 为 true
                while (flag) {
                    System.out.println(Thread.currentThread().getName() + "flag is true. wait @ " + new Date());
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println(Thread.currentThread().getName() + "flag is false. running @ " + new Date());
            }
        }
    }

    static class Notify implements Runnable {

        @Override
        public void run() {
            synchronized (lock) {
                // 当条件不满足时，进入等待状态，此时 flag 为 true
                System.out.println(Thread.currentThread().getName() + "hold lock. notify @ " + new Date());
                try {
                    lock.notifyAll();
                    flag = false;
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "hold lock again. notify @ " + new Date());
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
