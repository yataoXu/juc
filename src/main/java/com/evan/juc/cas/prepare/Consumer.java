package com.evan.juc.cas.prepare;

/**
 * @Description
 * @ClassName Consumer
 * @Author Evan
 * @date 2020.03.25 13:10
 */
public class Consumer extends Thread {

    private Counter counter;

    public Consumer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int j = 0; j < ClientTest.LOOP; j++) {
            counter.decCount();
        }
    }

}