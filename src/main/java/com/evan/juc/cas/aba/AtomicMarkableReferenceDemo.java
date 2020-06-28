package com.evan.juc.cas.aba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicMarkableReference;

/**
 * @author Evan
 */
public class AtomicMarkableReferenceDemo {

    private static AtomicMarkableReference<Integer> atomicMarkableReference = new AtomicMarkableReference<Integer>(100, false);

    public static void main(String[] args) {
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "版本号是否被更改:" + atomicMarkableReference.isMarked());
            //睡眠1秒，是为了让BBB线程也拿到同样的初始版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicMarkableReference.compareAndSet(100, 101, atomicMarkableReference.isMarked(), true);
            atomicMarkableReference.compareAndSet(101, 100, atomicMarkableReference.isMarked(), true);
        }, "AAA").start();

        new Thread(() -> {

            boolean isMarked = atomicMarkableReference.isMarked();
            System.out.println("t2版本号是否被更改:" + isMarked);
            System.out.println(Thread.currentThread().getName() + "版本号是否被更改:" + isMarked);

            //睡眠3秒，是为了让AAA线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("是否更改过:" + atomicMarkableReference.isMarked());
            System.out.println(Thread.currentThread().getName() + "是否修改成功：" + atomicMarkableReference.compareAndSet(100, 2020, isMarked, true));
            System.out.println(Thread.currentThread().getName() + "修改后的值：" + atomicMarkableReference.getReference());

        }, "BBB").start();

    }
}
