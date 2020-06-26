package com.evan.juc.synchronizedblock;

public class Kejianxing {

    private int count = 0;

    public void add10K() {
        int idx = 0;
        while (idx++ < 10000) {
            count++;
            System.out.println(Thread.currentThread().getName() + "  :  " + count);
        }
    }

    public int getCount() {
        return count;
    }
}

class KejianxingClient {
    public static void main(String[] args) throws InterruptedException {
        Kejianxing kejianxing = new Kejianxing();
        Thread t1 = new Thread(() -> {
            kejianxing.add10K();
        }, "AAA");
        Thread t2 = new Thread(() -> {
            kejianxing.add10K();
        }, "BBB");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(kejianxing.getCount());

    }
}

