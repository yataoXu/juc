package com.evan.juc.cas;

/**
 * @Description
 * @ClassName AtomicProducer
 * @Author Evan
 * @date 2020.03.25 13:34
 */
public class AtomicProducer extends Thread {

    private AtomicCounter atomicCounter;

    public AtomicProducer(AtomicCounter atomicCounter) {
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        for (int j = 0; j < AtomicTest.LOOP; j++) {
            atomicCounter.increment();
        }
    }
}
