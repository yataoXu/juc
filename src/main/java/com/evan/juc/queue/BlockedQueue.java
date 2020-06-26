package com.evan.juc.queue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 对于入队操作，如果队列已满，就需要等待直到队列不满，所以这里用了notFull.await();。
 * 对于出队操作，如果队列为空，就需要等待直到队列不空，所以就用了notEmpty.await();。
 * 如果入队成功，那么队列就不空了，就需要通知条件变量：队列不空notEmpty对应的等待队列。
 * 如果出队成功，那就队列就不满了，就需要通知条件变量：队列不满notFull对应的等待队列。
 */
public class BlockedQueue<T> {
    final Lock lock = new ReentrantLock(true);
    final LinkedList<T> queue = new LinkedList<>();
    final int QUEUE_MAX_SIZE = 100;
    final Condition notFullCondition = lock.newCondition();
    final Condition notEmptyCondition = lock.newCondition();

    /**
     * 入队
     *
     * @param x
     */
    void enq(T x) {
        lock.lock();
        try {
            // 若队列已满
            while (queue.size() == QUEUE_MAX_SIZE) {
                // 等待队列不满
                try {
                    notFullCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            // 入队操作
            queue.addLast(x);

            //入队后,通知可出队
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 出队列
     *
     * @return
     */
    T deq() {
        lock.lock();
        try {

            // 队列已空
            while (queue.isEmpty()) {
                try {
                    notEmptyCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            T firstElement = queue.getFirst();

            // 通知可入队列
            notFullCondition.signal();

            return firstElement;
        } finally {
            lock.unlock();
        }

    }
}
