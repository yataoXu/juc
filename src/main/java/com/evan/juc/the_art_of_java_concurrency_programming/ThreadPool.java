package com.evan.juc.the_art_of_java_concurrency_programming;

public interface ThreadPool <Job extends Runnable> {

    // 执行一个job,这个job需要实现runnnable,将job 提交到线程池中执行
    void execute(Job job);

    // 关闭连接池
    void shutdown();

    //  增加工作线程
    void addWorkers(int number);

    // 减少工作线程
    void removeWorker(int number);

    // 得到正在等待执行的任务数量
    int getJobSize();
}
