package com.evan.juc.base.creadThread;


class ShareData extends Thread {
    @Override
    public void run() {
        System.out.println("当前线程/创建出的线程为" + Thread.currentThread().getName());
    }
}

public class MyThread {


    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        shareData.start();
        shareData.run();
    }

}
