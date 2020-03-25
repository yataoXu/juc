package com.evan.juc.cas.prepare;

/**
 * @Description
 * @ClassName Producer
 * @Author Evan
 * @date 2020.03.25 13:11
 */
public class Producer extends Thread {

    private Counter counter;

    public Producer(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int j = 0; j < ClientTest.LOOP; j++) {
            counter.addCount();
        }
    }
}
