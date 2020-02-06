package com.evan.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Description
 * @ClassName CallableDemo
 * @Author Evan
 * @date 2020.02.05 16:55
 */


class MyThread01 extends Thread {

}

class MyThread02 implements Runnable {
    @Override
    public void run() {

    }
}

class MyThread03 implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        return 1024;
    }
}


public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Runnable 接口的使用
        Thread t1 = new Thread(new MyThread02(), "AAA");
        t1.start();

        // Callable 的使用
        FutureTask<Integer> futureTask = new FutureTask(new MyThread03());
        new Thread(futureTask, "AA").start();

        Integer result =  futureTask.get();
        System.out.println(result);


    }


}
