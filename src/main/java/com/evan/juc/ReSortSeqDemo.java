package com.evan.juc;

/**
 * @Description
 * @ClassName ReSortSeqDemo
 * @Author Evan
 * @date 2020.02.06 19:57
 */
public class ReSortSeqDemo {
    int a = 0;
    boolean flag = false;

    public void method01() {
        a = 1; // flag = true;
        // ----线程切换----
        flag = true; // a = 1;
    }

    public void method02() {
        if (flag) {
            a = a + 3;
            System.out.println("a = " + a);
        }
    }
}