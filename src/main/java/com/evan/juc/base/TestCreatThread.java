package com.evan.juc.base;
import	java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName TestClass
 * @Author Evan
 * @date 2020.03.19 22:07
 */
public class TestCreatThread {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String> ();
        for (int i = 0; i < 10; i++) {
            final int temp = i;
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                list.add(String.valueOf(temp));
            }, String.valueOf(temp)).start();
        }
    }
}
