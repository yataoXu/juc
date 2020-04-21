package com.evan.jvm.base;

/**
 * @Description
 * @ClassName Father
 * @Author Evan
 * @date 2020.04.03 10:39
 */
public class Father {

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
