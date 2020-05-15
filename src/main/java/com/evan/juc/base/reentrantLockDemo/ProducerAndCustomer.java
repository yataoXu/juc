package com.evan.juc.base.reentrantLockDemo;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 资源类
class Resource {

    private String name;
    private int count;
    private boolean flag = false;
    Lock lock = new ReentrantLock();
    Condition producerLock = lock.newCondition();
    Condition customerLock = lock.newCondition();

    public void set(String name) {
        lock.lock();
        try {
            while (flag)//flag为1，表示还没消费完
            {
                try {
                    producerLock.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.name = name + count;
            count++;
            System.out.println(Thread.currentThread().getName() + " 生产者...... " + this.name);
            flag = true;
            customerLock.signal();
        } finally {
            lock.unlock();
        }
    }

    public void out() {
        lock.lock();
        try {
            while (!flag) {
                try {
                    customerLock.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " 消费者............... " + this.name);
            flag = false;
            producerLock.signal();
        } finally {
            lock.unlock();
        }
    }
}

class Producers implements Runnable {
    private Resource resource;

    public Producers(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.set("烤鸭");
        }
    }
}

class Customers implements Runnable {
    private Resource resource;

    public Customers(Resource resource) {
        this.resource = resource;
    }

    @Override
    public void run() {
        while (true) {
            resource.out();
        }
    }
}

public class ProducerAndCustomer {
    public static void main(String[] args) {
        Resource resource = new Resource();
        Producers producer = new Producers(resource);
        Customers customer = new Customers(resource);
        Thread t0 = new Thread(producer);
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(customer);
        Thread t3 = new Thread(customer);
        t0.start();
        t1.start();
        t2.start();
        t3.start();
    }
}