package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest04
 * @Author Evan
 * @date 2020.07.01 00:05
 */
public class OverLoadTest04 {
    public static void main(String[] args) {
        int a = 5;
        short s = 8;
        m(a, s);
    }

    public static void m(int a, Short b) {//m1
        System.out.println("调用了m(int，Short)");
    }

    public static void m(float f, short s) {//m2
        System.out.println("调用了m(float,short)");
    }
}