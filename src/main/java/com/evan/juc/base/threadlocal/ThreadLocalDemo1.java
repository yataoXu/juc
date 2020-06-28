package com.evan.juc.base.threadlocal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadLocalDemo1 {


    /**
     * 每个线程执行5次该方法
     */
    public static void incrementSameThreadId() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread() + "执行了" + i + "次。ThreadLoalId::" + ThreadLoalId.get());

        }
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                incrementSameThreadId();
                System.out.println("++++++++++++++++++++++");
            }, i + "").start();

            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class ThreadLoalId {
    private static final AtomicInteger atomic = new AtomicInteger(0);

    private static final ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return atomic.incrementAndGet();
        }
    };

    public static int get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }

    public static void set(int id) {
        threadLocal.set(id);
    }
}
