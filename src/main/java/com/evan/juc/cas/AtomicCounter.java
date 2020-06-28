package com.evan.juc.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @ClassName AutomicCount
 * @Author Evan
 * @date 2020.03.25 13:31
 */

public class AtomicCounter {

    private AtomicInteger integer  = new AtomicInteger();

    public AtomicInteger getInteger() {
        return integer;
    }

    public void setInteger(AtomicInteger integer) {
        this.integer = integer;
    }

    public void increment() {
        integer .incrementAndGet();
    }

    public void decrement() {
        integer .decrementAndGet();
    }
}

class AtomicTest {
    final static int LOOP = 100;

    public static void main(String[] args) throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();
        AtomicProducer producer = new AtomicProducer(counter);
        AtomicConsumer consumer = new AtomicConsumer(counter);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println(counter.getInteger());
    }
}
