package com.evan.juc.the_art_of_java_concurrency_programming;

public class ConcurrencyTest {

    private static final int count = 1000000000;

    public static void main(String[] args) throws InterruptedException {
        concurrency();
        serial();
    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() ->
        {
            int a = 0;
            for (long i = 0; i < count; i++) {
                a += 5;
                System.out.println(a);

            }
        });


        thread.start();
        int b = 0;
        for (
                long i = 0;
                i < count; i++) {
            b--;
        }

        long time = System.currentTimeMillis() - start;
        thread.join();
        System.out.println("concurrency :" + time + "ms,b=" + b);
    }

    public static void serial() {
        long start = System.nanoTime();
        int a = 0;
        for (int i = 0; i < count; i++) {
            a += 5;
        }
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        long time = System.nanoTime() - start;
        System.out.println("serial: " + time + ",b= " + b + ",a= " + a);
    }
}