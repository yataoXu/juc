package com.evan.juc.base.reentrantLockDemo;

/**
 * @Description
 * @ClassName MyThread
 * @Author Evan
 * @date 2020.03.27 22:57
 */
public class MyThread  extends Thread{
    private MyService myService;

    public MyThread(MyService myService){
        this.myService = myService;
    }

    @Override
    public void run() {
        myService.await();
    }
}
