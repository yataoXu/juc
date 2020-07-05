package com.evan.jc.duotai;

/**
 * @Description
 * @ClassName MyTest2
 * @Author Evan
 * @date 2020.07.01 01:12
 */

class Father {
    public String s = "father";

    public void count() {
        System.out.println("father 类的count()方法");
    }

    public long sum(long a, long b) {
        System.out.println("father 类的sum()方法");
        return a + b;
    }

}

class Son extends Father {
    public String s = "son";

    @Override
    public void count() {
        System.out.println("son 类的count()方法");
    }

    @Override
    public long sum(long a, long b) {
        System.out.println("son 类的sum()方法");
        return (int) a + b;
    }
}

public class MyTest2 {
    public static void main(String[] args) {
        Father son = new Son();
        System.out.println(son.s);
        son.count();
        long a = 10L;
        long b = 20L;
        System.out.println(son.sum(a, b));

    }
}
