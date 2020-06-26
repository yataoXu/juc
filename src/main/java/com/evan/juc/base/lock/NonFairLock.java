package com.evan.juc.base.lock;

public class NonFairLock {


    private boolean lock = false;

    private Thread threadRunning = null;

    public synchronized void lock() {

        while (lock) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lock = true;
        threadRunning = Thread.currentThread();

    }

    public synchronized void unlock() {

        if (threadRunning != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        lock = false;
        threadRunning = null;

        notifyAll();
    }

}

class NonFairLockClient{
    public static void main(String[] args) {
        NonFairLock lock = new NonFairLock();
        lock.lock();
        // do something
        lock.unlock();
    }
}
