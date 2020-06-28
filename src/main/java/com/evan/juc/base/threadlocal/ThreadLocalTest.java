package com.evan.juc.base.threadlocal;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName ThreadLocalTest
 * @Author Evan
 * @date 2020.06.14 17:48
 */
public class ThreadLocalTest {

    volatile static Person person = new Person();

    public static void main(String[] args) {


        new Thread(() -> {

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(person.getName());

        }, "thread-01").start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            person.setName("lisi");

        }, "thread-02").start();
    }
}
