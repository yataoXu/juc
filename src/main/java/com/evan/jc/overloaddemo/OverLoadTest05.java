package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest05
 * @Author Evan
 * @date 2020.07.01 00:16
 */
public class OverLoadTest05 {

    public static void main(String[] args) {
        //创建Runnable对象
        Runnable r = () -> {
            System.out.println("执行多线程的run()方法");
        };
        //调用泛型方法
        m(r);
    }

    public static <T> void m(T t) {//m1
        System.out.println("调用了<T> void m(T)");
    }

    public static <T extends Runnable> void m(T t) {//m2
        System.out.println("调用了<T extends Runnable> void m(T t)");
    }
}
