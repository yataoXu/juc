package com.evan.juc.cas.aba;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * ABA问题复现
 *
 * A->B->A
 * 初始值为100
 * 线程AAA将100改成101，然后又将101改回100
 * 线程BBB先睡眠1秒，等待AAA操作完成，然后BBB线程将值改成2020
 * 可以看到，线程BBB修改成功
 *
 * ABA问题解决
 * 要解决ABA问题，可以增加一个版本号，当内存位置V的值每次被修改后，版本号都加1
 *
 *
 * AtomicStampedReference
 *
 * AtomicStampedReference内部维护了对象值和版本号，在创建AtomicStampedReference对象时，
 * 需要传入初始值和初始版本号，当AtomicStampedReference设置对象值时，对象值以及状态戳都必须满足期望值，写入才会成功
 *
 *
 *
 * @author Evan
 */
public class ABADemo {

    static AtomicReference<Integer> atomicReference = new AtomicReference<>(100);

    public static void main(String[] args) {

        new Thread(() -> {
            atomicReference.compareAndSet(100, 101);
            atomicReference.compareAndSet(101, 100);
        }, "AAA").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicReference.compareAndSet(100, 2020);
            System.out.println(Thread.currentThread().getName() + "修改后的值：" + atomicReference.get());

        }, "BBB").start();
    }
}
