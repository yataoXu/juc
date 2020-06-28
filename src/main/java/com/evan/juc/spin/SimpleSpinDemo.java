package com.evan.juc.spin;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicStampedReference;

class SimpleSpin {

    /**
     * 持有锁的线程，null表示锁未被线程持有
     * 定义一个带时间戳的原子线程类，给的默认值是false
     */

    static AtomicStampedReference<Thread> atomicThread = new AtomicStampedReference<>(null, 1);

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " come in,version is " + atomicThread.getStamp());


        //当ref为null的时候compareAndSet返回true，反之为false
        //通过循环不断的自旋判断锁是否被其他线程持有
        while (!atomicThread.compareAndSet(null, thread, 1, 2)) {
            System.out.println("加锁失败");
        }
        System.out.println(thread.getName() + "加锁成功");
        System.out.println(thread.getName() + " come out,version is " + atomicThread.getStamp());
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + " come in,version is " + atomicThread.getStamp());
        while (!atomicThread.compareAndSet(thread, null, 2, 1)) {
            //exception ...
//            System.out.println("解锁失败");
        }
        System.out.println(thread.getName() + "解锁成功");
        System.out.println(thread.getName() + " come out,version is " + atomicThread.getStamp());
    }
}

public class SimpleSpinDemo {

    private static int count = 0;

    public static void main(String[] args) {

        SimpleSpin simpleSpin = new SimpleSpin();
        CountDownLatch latch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {


            new Thread(() -> {
                simpleSpin.lock();
                try {
                    count++;
                } finally {
                    simpleSpin.unlock();
                    latch.countDown();
                }
            }, i + "").start();

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("结束 count 的值" + count);
    }

}
