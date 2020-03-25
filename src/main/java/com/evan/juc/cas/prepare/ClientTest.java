package com.evan.juc.cas.prepare;

/**
 * @Description
 * @ClassName ClientTest
 * @Author Evan
 * @date 2020.03.25 13:15
 */
public class ClientTest {


    final static int LOOP = 1000;

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();
        Producer producer = new Producer(counter);
        Consumer consumer = new Consumer(counter);

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println(counter.getCount());

    }
}

