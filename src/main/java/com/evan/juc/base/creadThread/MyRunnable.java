package com.evan.juc.base.creadThread;

class ShareDataOfRunnalbe implements Runnable {

    @Override
    public void run() {
        System.out.println("当前线程/创建出的线程为" + Thread.currentThread().getName());
    }
}

public class MyRunnable {

    public static void main(String[] args) {
        ShareDataOfRunnalbe runnalbe = new ShareDataOfRunnalbe();
        new Thread(runnalbe).start();
    }
}
