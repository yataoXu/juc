package com.evan.core.reference;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName ThreadLocalTest1
 * @Author Evan
 * @date 2020.06.14 17:48
 */
public class ThreadLocalTest1 {

    // volatile static Person person = new Person();
    static ThreadLocal<Person> tl = new ThreadLocal<>();


    public static void main(String[] args) {


        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(tl.get());

        }, "thread-01").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tl.set(new Person("李四"));

        }, "thread-02").start();
    }
}
