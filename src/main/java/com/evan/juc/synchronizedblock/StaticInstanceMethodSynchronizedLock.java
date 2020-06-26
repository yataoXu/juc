package com.evan.juc.synchronizedblock;

import java.util.concurrent.TimeUnit;


/**
 * 静态方法同步是同步在静态方法的类对象（不是类的实例，是指保存类信息的对象）上的，事实上，Java虚拟机中一个类只能对应一个类对象，
 * 所以只允许一个线程执行同一个类的静态同步方法。
 *
 * 其实和实例方法同步是类似的，只不过实例方法同步是在类实例上加锁，静态方法同步是在类对象上加锁。
 *
 * @author Evan
 */
public class StaticInstanceMethodSynchronizedLock {
    public static synchronized void addJ1(){
        for(int i=400 ;i<500;i++){

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
    public static  void addJ2(){
        for(int i=500 ;i<600;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class StaticInstanceMethodSynchronizedLockClient{
    public static void main(String[] args) {

        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ1();},"static instance synchronized method 1").start();
        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ2();},"static instance method 1").start();
        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ1();},"static instance synchronized method 2").start();
    }
}
