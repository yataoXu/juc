package com.evan.juc.base.threadlocal;

public class ThreadLocalDemo {

    static A a = new A();
    static final ThreadLocal<A> threadLocal = new ThreadLocal<A>() {
        @Override
        protected A initialValue() {
            return a;
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[5];

        for (int i = 0; i < 5; i++) {

            threads[i] = new Thread(() -> {
                threadLocal.get().setNumber(threadLocal.get().getNumber() + 5);
                System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get().getNumber());
            }, "thread-" + i);
        }

        for (Thread thread : threads) {
            thread.start();
        }
    }

}

class A {
    private int number = 0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
