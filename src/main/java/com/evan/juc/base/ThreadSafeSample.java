package com.evan.juc.base;

/**
 * @Description
 * @ClassName ThreadSafeSample
 * @Author Evan
 * @date 2020.03.27 22:11
 */
public class ThreadSafeSample {
    private int count;

    public void nonSafeAction() {
        while (count < 1000000) {

            synchronized (this) {

                int former = count++;
                int latter = count;
                if (former != latter - 1) {
                    System.out.println("Observed data race ,former is " + former + ", " + "latter is " + latter);
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadSafeSample sample = new ThreadSafeSample();

        Thread threadA = new Thread(() -> {
            sample.nonSafeAction();
        });

        Thread threadB = new Thread(() -> {
            sample.nonSafeAction();
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();
    }
}
