package com.evan.juc.cas;

/**
 * @Description
 * @ClassName AtomicConsumer
 * @Author Evan
 * @date 2020.03.25 13:38
 */
public class AtomicConsumer  extends Thread{

    private AtomicCounter atomicCounter;

    public AtomicConsumer(AtomicCounter atomicCounter){
        this.atomicCounter = atomicCounter;
    }

    @Override
    public void run() {
        for(int j = 0; j < AtomicTest.LOOP; j++) {
            System.out.println("consumer : " + atomicCounter.getInteger());
            atomicCounter.decrement();
        }
    }
}

