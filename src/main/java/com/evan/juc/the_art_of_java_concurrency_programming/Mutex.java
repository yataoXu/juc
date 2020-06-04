package com.evan.juc.the_art_of_java_concurrency_programming;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @Description
 * @ClassName Mutex
 * @Author Evan
 * @date 2020.06.03 17:20
 */
public class Mutex implements Lock {

    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 是否处于独占状态
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        /**
         * 当状态为0的时候获取锁
         *
         * @return
         */
        public boolean tryAcquired(int acquired) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        public boolean tryRelease(int release) {
            if (getState() == 0) throw new IllegalThreadStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }



        /**
         * 返回一个Condition对象，每个Condition都包含了一个Condition队列
         *
         * @return
         */
        Condition newCondition() {
            return new ConditionObject();
        }
    }

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquired(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition(){
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueueThreads(){
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireSharedNanos(1,unit.toNanos(timeout));
    }

}
