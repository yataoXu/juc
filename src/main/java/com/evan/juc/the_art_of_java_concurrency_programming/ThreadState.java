package com.evan.juc.the_art_of_java_concurrency_programming;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName ThreadState
 * @Author Evan
 * @date 2020.06.02 15:34
 */
public class ThreadState {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "TimeWaiting").start();

        new Thread(new Waiting(),"Waiting").start();
        new Thread(new Blocked(),"Blocked-1").start();
        new Thread(new Blocked(),"Blocked-2").start();
    }

    // 该线程在Waiting.class 实例上等待
    static class Waiting implements Runnable {
        @Override
        public void run() {
            synchronized (Waiting.class){
                try {
                    Waiting.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 该线程在Blocked.class 实例上加锁后，不会释放该锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
           synchronized (Blocked.class){
               while (true){
                   try {
                       TimeUnit.SECONDS.sleep(10);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
           }
        }
    }


}