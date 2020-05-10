package com.evan.juc.the_art_of_java_concurrency_programming.part01;

public class ConcurrencyTest {

    private static final int count = 100001;

    public static void main(String[] args) {

    }

    private static void concurrency() throws InterruptedException {

        long start = System.nanoTime();

        Thread thread = new Thread(() -> {

            int a = 0;
            for (int i = 0; i < count; i++) {
                a += 5;
            }
        });

        thread.start();
        int b = 0;
        for (int i = 0; i < count; i++) {
            b--;
        }
        thread.join();
        long time = System.nanoTime() - start;
        System.out.println();

    }
}
