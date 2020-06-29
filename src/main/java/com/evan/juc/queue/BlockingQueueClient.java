package com.evan.juc.queue;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


class Producer implements Runnable {

    //是否在运行标志
    private volatile boolean isRunning = true;
    //阻塞队列
    private BlockingQueue queue;
    //自动更新的值
    private static AtomicInteger count = new AtomicInteger();
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }


    @Override
    public void run() {

        String data;
        Random r = new Random();

        System.out.println("启动生产者线程！");

        while (isRunning) {

            System.out.println("生产数据...");
            try {
                TimeUnit.MILLISECONDS.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                // 以原子方式将count当前值加1
                data = "data:" + count.incrementAndGet();
                System.out.println(data);
                System.out.println("将数据：" + data + "放入队列...");

                // 设定的等待时间为2s，如果超过2s还没加进去返回true
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("放入数据失败：" + data);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("退出生产者线程！");
            }

        }
    }

    public void stop() {
        isRunning = false;
    }
}


class Consumer implements Runnable {

    private BlockingQueue<String> queue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 10;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("启动消费者线程！");
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("正从队列获取数据...");
                //有数据时直接从队列的队首取走，无数据时阻塞，在2s内有数据，取走，超过2s还没数据，返回失败
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("拿到数据：" + data);
                    System.out.println("正在消费数据：" + data);
                    TimeUnit.MILLISECONDS.sleep(DEFAULT_RANGE_FOR_SLEEP);
                } else {
                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
                    isRunning = false;
                }

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("退出消费者线程！");
        }
    }
}


public class BlockingQueueClient {
    public static void main(String[] args) {

        BlockingQueue<String> queue = new ArrayBlockingQueue(10);
        Producer producer = new Producer(queue);
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        ExecutorService executor = new ThreadPoolExecutor(1, 10, 0L, TimeUnit.SECONDS,
                new SynchronousQueue<>());

        executor.execute(producer);
        executor.execute(producer1);
        executor.execute(producer2);
        executor.execute(producer3);
        executor.execute(consumer);


        try {
            // 执行10s
            TimeUnit.SECONDS.sleep(10);

            producer1.stop();
            producer2.stop();
            producer3.stop();

            TimeUnit.SECONDS.sleep(2);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 退出Executor
            executor.shutdown();
        }


    }


}