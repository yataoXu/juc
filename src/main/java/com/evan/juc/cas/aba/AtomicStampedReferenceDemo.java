package com.evan.juc.cas.aba;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * AtomicStampedReference可以给引用加上版本号，追踪引用的整个变化过程，
 * 如：A -> B -> C -> D - > A，通过AtomicStampedReference，我们可以知道，引用变量中途被更改了3次
 * 但是，有时候，我们并不关心引用变量更改了几次，只是单纯的关心是否更改过，所以就有了AtomicMarkableReference
 * AtomicMarkableReference的唯一区别就是不再用int标识引用，而是使用boolean变量——表示引用变量是否被更改过
 *
 * @author Evan
 */
public class AtomicStampedReferenceDemo {
    static AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(100, 1);

    public static void main(String[] args) {
        new Thread(() -> {

            System.out.println(Thread.currentThread().getName() + "拿到的初始版本号:" + atomicStampedReference.getStamp());
            //睡眠1秒，是为了让BBB线程也拿到同样的初始版本号
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(100, 101, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(101, 100, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1);
        }, "AAA").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println("stamp 的值:" + stamp);

            //睡眠3秒，是为了让AAA线程完成ABA操作
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("最新版本号:" + atomicStampedReference.getStamp());
            System.out.println(Thread.currentThread().getName() + "是否修改成功：" + atomicStampedReference.compareAndSet(100, 2020, stamp, atomicStampedReference.getStamp() + 1));
            System.out.println(Thread.currentThread().getName() + "修改后的值：" + atomicStampedReference.getReference());

        }, "BBB").start();

    }
}
