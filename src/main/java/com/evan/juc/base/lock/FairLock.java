package com.evan.juc.base.lock;

import java.util.LinkedList;
import java.util.List;

public class FairLock {

    private boolean isLock = false;
    private Thread threadRunning = null;
    private LinkedList<QueueObject> waitingThreads = new LinkedList<>();

    public synchronized void lock() throws InterruptedException {

        QueueObject queueObject = new QueueObject();
        boolean isLockedForThisThread = true;
        synchronized (this) {
            isLockedForThisThread = isLock || waitingThreads.get(0) != queueObject;
            if (!isLockedForThisThread) {
                isLock = true;
                waitingThreads.remove(queueObject);
                threadRunning = Thread.currentThread();
                return;
            }
        }
        try {
            queueObject.doWait();
        } catch (InterruptedException e) {
            synchronized (this) {
                waitingThreads.remove(queueObject);
            }
            throw e;
        }

    }

    public synchronized void unlock() {
        if (threadRunning != Thread.currentThread()) {
            throw new IllegalMonitorStateException("Calling thread has not locked this lock");
        }
        isLock = false;
        threadRunning = null;
        if (!waitingThreads.isEmpty()) {
            waitingThreads.pollFirst().doNotify();
        }

    }

}


class QueueObject {
    private boolean isNotified = false;

    public synchronized void doWait() throws InterruptedException {

        while (!isNotified) {
            this.wait();
        }

        this.isNotified = false;

    }

    public synchronized void doNotify() {
        this.isNotified = true;
        this.notify();
    }

    @Override
    public boolean equals(Object o) {
        return this == o;
    }

}