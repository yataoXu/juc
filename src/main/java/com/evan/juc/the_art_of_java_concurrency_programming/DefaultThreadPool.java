package com.evan.juc.the_art_of_java_concurrency_programming;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description
 * @ClassName DefaultThreadPool
 * @Author Evan
 * @date 2020.06.03 09:55
 */
public class DefaultThreadPool<Job extends Runnable> implements ThreadPool<Job> {

    // 线程池最大限制数
    private static final int MAX_WORKER_NUMBER = 10;
    // 线程池默认的数量
    private static final int DEFAULT_WORKER_NUMBER = 5;
    // 线程池最小的数量
    private static final int MIN_WORK_NUMBER = 1;
    // 这是一个工作list, 将会向里面插入工作
    private final LinkedList<Job> jobs = new LinkedList<>();
    // 工作者列表
    private final List<Worker> workers = new CopyOnWriteArrayList<>();

    // 线程池中线程数量
    private int workerNum = DEFAULT_WORKER_NUMBER;
    // 线程编号生成
    private AtomicLong threadNum = new AtomicLong();

    public DefaultThreadPool() {
        initializeWorkers(DEFAULT_WORKER_NUMBER);
    }

    public DefaultThreadPool(int num) {
        workerNum = num > MAX_WORKER_NUMBER ? MAX_WORKER_NUMBER : num < MIN_WORK_NUMBER ? MIN_WORK_NUMBER : num;
        initializeWorkers(DEFAULT_WORKER_NUMBER);
    }

    private void initializeWorkers(int num) {
        for (int i = 0; i < num; i++) {
            Worker worker = new Worker();
        }
    }


    @Override
    public void execute(Job job) {
        if (job != null) {
            synchronized (jobs) {
                jobs.addLast(job);
                jobs.notifyAll();
            }
        }
    }

    @Override
    public void shutdown() {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public void addWorkers(int number) {
        synchronized (jobs) {
            // 限制新增的Worker 数量不能超过最大值
            if (number + this.workerNum > MAX_WORKER_NUMBER) {
                number = MAX_WORKER_NUMBER - this.workerNum;
            }
            initializeWorkers(number);
            this.workerNum += number;
        }
    }

    @Override
    public void removeWorker(int number) {
        synchronized (jobs) {
            if (number >= this.workerNum){
                throw new IllegalArgumentException("beyong thread");
            }
        }

        int count = 0;
        while (count < number){
            Worker worker = workers.get(count);
            if (workers.remove(worker)){
                worker.shutdown();
                count ++;
            }
        }
        this.workerNum -= count;
    }


    @Override
    public int getJobSize() {

        return jobs.size();
    }


    class Worker implements Runnable {

        private volatile boolean running = true;

        @Override
        public void run() {
            while (running) {
                Job job = null;
                synchronized (jobs) {
                    while (jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    // 取出一个job
                    job = jobs.removeFirst();
                }
                if (job != null) {
                    try {

                        job.run();
                    } catch (Exception e) {

                    }
                }
            }
        }

        public void shutdown() {
            running = false;
        }
    }
}