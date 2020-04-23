package com.evan.juc.volatileDemo;

import java.util.concurrent.TimeUnit;

public class VolatileDemo1 {
    private volatile int a;

    public void add() {
        a++;
    }

    public int getA() {
        return a;
    }

    public static void main(String[] args) {

        final VolatileDemo1 volatileDemo1 = new VolatileDemo1();
        VolatileDemo1 volatileDemo2  = new VolatileDemo1();
//        volatileDemo1 = volatileDemo2
        for (int i = 0; i < 1000; i++) {

            new Thread(() -> {

                try {
                    TimeUnit.MILLISECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                volatileDemo1.add();
            }).start();
        }

        System.out.println(volatileDemo1.getA());
    }

}
