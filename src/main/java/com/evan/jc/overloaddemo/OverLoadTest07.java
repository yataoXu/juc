package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest07
 * @Author Evan
 * @date 2020.07.01 00:23
 */
public class OverLoadTest07 {

    public static void m(short... s) {}
//    public static void m(Short... s) {}
//    public static void m(int... s) {}

    public static void main(String[] args) {
        short s = 8;
        Short sl = 10;
        m(s,s);//编译不通过
        m(s,sl);//编译不通过
        m(sl,sl);//编译不通过
    }
}
