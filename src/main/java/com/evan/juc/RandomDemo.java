package com.evan.juc;

import java.util.Random;

import java.util.Random;

/**
 * @Description
 * @ClassName RandomDemo
 * @Author Evan
 * @date 2020.02.08 12:25
 */
public class RandomDemo {
    public static void main(String[] args) {

        Random random = new Random(99);
        for (int i = 0; i < 10; i++) {
            System.out.println(random.nextInt(100));
        }
    }
}
