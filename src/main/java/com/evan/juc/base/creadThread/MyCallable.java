package com.evan.juc.base.creadThread;


import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@AllArgsConstructor
class MyCallableShareDate implements Callable {

    private String taskNum;

    @Override
    public Object call() throws Exception {
        System.out.println(">>>" + taskNum + "任务启动");
        long start = System.currentTimeMillis();

        TimeUnit.SECONDS.sleep(10);

        long time = System.currentTimeMillis() - start;

        System.out.println(time / 1000 + " sec");

        System.out.println(">>>" + taskNum + "任务终止");
        return taskNum + "任务返回运行结果，当前任务时间[" + time / 1000 + "sec]";
    }
}

/**
 * @author seassoon
 */
public class MyCallable {

    public static void main(String[] args) {
        System.out.println("程序开始运行..........");
        long start = System.currentTimeMillis();

        //线程池中可以容纳的线程数量
        int taskSize = 5;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        //创建有多个返回值的任务
        List<Future> list = new ArrayList<Future>();
        for (int i = 0; i < taskSize; i++) {
            Callable c = new MyCallableShareDate(i + "");
            //执行任务并获取Future对象
            Future f = pool.submit(c);
            list.add(f);
        }
        //关闭线程池
        pool.shutdown();


        // 获取所有并发任务的运行结果
        for (Future f : list) {
            try {
                //从future对象上获取任务的返回值，并输出到控制台
                System.out.println(">>>" + f.get().toString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("程序运行结束.......，程序运行时间[" + (time / 1000) + "sec]");
    }
}
