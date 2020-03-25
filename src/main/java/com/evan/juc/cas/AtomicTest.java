package com.evan.juc.cas;
import	java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * @ClassName AtomicTest
 * @Author Evan
 * @date 2020.03.25 13:38
 */
public class AtomicTest {
    final static int LOOP = 10000;

    public static void main(String[] args) throws InterruptedException {

        AtomicCounter counter = new AtomicCounter();
        AtomicProducer producer = new AtomicProducer(counter);
        AtomicConsumer consumer = new AtomicConsumer(counter);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println(counter.getInteger());


        ReentrantLock reentrantLock = new ReentrantLock( false);
    }
}