package com.evan.juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerDemo {


    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2);
        System.out.println(atomicInteger.get()); //2
        System.out.println(atomicInteger.getAndIncrement()); // 2  相当于 i++
        System.out.println(atomicInteger.get()); // 3

        System.out.println(atomicInteger.compareAndSet(3, 10)); // true
        System.out.println(atomicInteger.get()); // 10
        System.out.println(atomicInteger.compareAndSet(4, 10)); // false
        System.out.println(atomicInteger.get()); // 10



    }
}
