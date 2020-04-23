package com.evan.juc.volatileDemo;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName VolatileTest
 * @Author Evan
 * @date 2020.04.23 22:23
 */
public class VolatileTest {
    int a;
    int b;

    public void change(){
        a = 3;
        b = a;
    }
    public void print(){
        System.out.println("b="+b+";a="+a);
    }

    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();

        while (true){
            new Thread(()->{

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volatileTest.change();
            }).start();

            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                volatileTest.print();
            }).start();
        }
    }
}

