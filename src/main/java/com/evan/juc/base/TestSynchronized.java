package com.evan.juc.base;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @Description
 * @ClassName TestSynchronized
 * @Author Evan
 * @date 2020.03.19 22:39
 */

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在Java中，解决共享资源竞争问题的首个解决方案就是使用关键字synchronized。当线程执行被synchronized保护的代码片段的时候，
 * 会对这段代码进行上锁，其他调用这段代码的线程会被阻塞，直到锁被释放。
 * <p>
 * 下面这段代码使用ThreadPoolExecutor创建了一个线程池，池里面的每个线程会对共享资源count进行+1操作。现在，闭上眼想一想，
 * 当1000个线程执行结束后，count的值会是多少呢？
 */

/**
 * 线程 操作 资源类
 */

// 资源类
class CountData {
    private int count = 0;

    public  synchronized void addCount() {
//        synchronized (CountData.class){ 这个不管用，不知道为啥子
            count++;
//        }
    }

    public int getCount() {
        return count;
    }
}

public class TestSynchronized {
    public static void main(String[] args) {

        CountData countData = new CountData();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000));

        try {
            for (int i = 0; i < 1000; i++) {
                threadPoolExecutor.submit(() -> {
                    countData.addCount();
                });
            }
        } finally {
            threadPoolExecutor.shutdown();
            System.out.println(countData.getCount());
        }

/**
 * workQueue 为 new SynchronousQueue<Runnable>()
 *
 * Exception in thread "main" java.util.concurrent.RejectedExecutionException:
 * Task java.util.concurrent.FutureTask@27d6c5e0 rejected from java.util.concurrent.ThreadPoolExecutor@4f3f5b24[Running,
 * pool size = 10, active threads = 0, queued tasks = 0, completed tasks = 14]
 * 	at java.util.concurrent.ThreadPoolExecutor$AbortPolicy.rejectedExecution(ThreadPoolExecutor.java:2063)
 * 	at java.util.concurrent.ThreadPoolExecutor.reject(ThreadPoolExecutor.java:830)
 * 	at java.util.concurrent.ThreadPoolExecutor.execute(ThreadPoolExecutor.java:1379)
 * 	at java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:112)
 * 	at com.evan.juc.base.TestSynchronized.main(TestSynchronized.java:50)
 *
 *
 * 	workQueue 为 new ArrayBlockingQueue<>(1000)
 *
 * 	994
 */

    }
}
