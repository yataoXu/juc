package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest01
 * @Author Evan
 * @date 2020.06.30 23:37
 */
public class OverLoadTest01 {

    /**
     * 方法一
     */

    public static void m(int x) {
        System.out.println("重载方法一");
    }

    /**
     * 方法二
     *
     * @param x
     */
    public static void m(float x) {
        System.out.println("重载方法二");
    }

}


class OverLoadClient {

    public static void main(String[] args) {
        int a = 10;
        float f = 20f;
        short s = 10;

        // short 自动转换为int
        OverLoadTest01.m(s);
        OverLoadTest01.m(a);
        OverLoadTest01.m(f);
    }

}
