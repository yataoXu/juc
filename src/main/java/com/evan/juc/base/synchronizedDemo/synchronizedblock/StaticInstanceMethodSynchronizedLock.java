package com.evan.juc.base.synchronizedDemo.synchronizedblock;

public class StaticInstanceMethodSynchronizedLock {
    public static synchronized void addJ1(){
        for(int i=400 ;i<500;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
    public static synchronized void addJ2(){
        for(int i=500 ;i<600;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class StaticInstanceMethodSynchronizedLockClient{
    public static void main(String[] args) {

        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ1();},"thread1").start();
        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ2();},"thread2").start();
        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ1();},"thread3").start();
    }
}
