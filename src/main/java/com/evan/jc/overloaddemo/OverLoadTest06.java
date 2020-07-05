package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest06
 * @Author Evan
 * @date 2020.07.01 00:14
 */
public class OverLoadTest06 {
    public static void main(String[] args) {
        int aa = 5;
        short ss = 8;
//        m(aa, ss);//编译不通过，无法确定调用了那个重载方法
    }

    public static void m(int a, double b) {//m1
        System.out.println("调用了m(int，Short)");
    }

    public static void m(float f, int c) {//m2
        System.out.println("调用了m(float,short)");
    }
}