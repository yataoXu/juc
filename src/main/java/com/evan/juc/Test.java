package com.evan.juc;

/**
 * @Description: -XX:+TraceClassLoading
 * 可以通过该参数参看类加载情况
 * @ClassName Test
 * @Author Evan
 * @date 2020.01.29 12:48
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }
}
