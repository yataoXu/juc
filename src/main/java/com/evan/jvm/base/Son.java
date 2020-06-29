package com.evan.jvm.base;

/**
 * @Description
 * @ClassName Son
 * @Author Evan
 * @date 2020.04.03 10:40
 */


class Father {

    private int i = test();
    private static int j = method();


    static {
        System.out.print("(1)");
    }

    {
        System.out.print("(2)");
    }

    Father() {
        System.out.print("(3)");
    }

    public int test() {
        System.out.print("(4)");
        return 1;
    }

    public static int method() {
        System.out.print("(5)");
        return 1;
    }
}

public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.print("(6)");
    }

    {
        System.out.print("(7)");
    }

    Son() {
        System.out.print("(8)");
    }

    @Override
    public int test() {
        System.out.print("(9)");
        return 1;
    }

    public static int method() {
        System.out.print("(10)");
        return 1;
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        System.out.println("=============");
        Son s2 = new Son();
    }
}