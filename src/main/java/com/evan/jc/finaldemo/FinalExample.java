package com.evan.jc.finaldemo;

/**
 * @Description
 * @ClassName FinalTest01
 * @Author Evan
 * @date 2020.07.01 01:43
 */
public class FinalExample {
    /**
     * 普通变量
     */
    int i;
    /**
     * final 变量
     */
    final int j;

    static FinalExample obj;


    /**
     * 构造函数
     */
    public FinalExample() {
        // 写普通域
        i = 1;
        // 写 final 域
        j = 2;
        System.out.println("写普通域 i=" + i);
        System.out.println("写 final 域 j=" + j);
    }

    /**
     * 写操作
     */
    public static void writer() {
        System.out.println(Thread.currentThread().getName() + "执行writer()");
        final FinalExample finalExample = new FinalExample();
        obj = finalExample;
    }

    /**
     * 读操作
     */
    public static void reader() {

        System.out.println(Thread.currentThread().getName() + "执行reader()");

        // 读对象引用
        final FinalExample object = obj;
        // 读普通域
        int a = object.i;
        // 读 final 域
        int b = object.j;
    }
}


class FinalTest01Client {
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            FinalExample.writer();
        }, "A");

        Thread thread2 = new Thread(() -> {
            FinalExample.reader();
        }, "B");

        // 写线程 A 执行
        thread1.start();
        // 读线程 B 执行
        thread2.start();


    }
}