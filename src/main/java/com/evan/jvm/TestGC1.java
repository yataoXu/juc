package com.evan.jvm;

/**
 * @Description
 * @ClassName TestGC1
 * @Author Evan
 * @date 2020.03.21 16:22
 */
public class TestGC1 {

    private void dontStop() {
        while (true) {
        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        TestGC1 oom = new TestGC1();
        oom.stackLeakByThread();
    }

}
