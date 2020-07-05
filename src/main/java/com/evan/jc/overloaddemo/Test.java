package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName Test
 * @Author Evan
 * @date 2020.07.03 00:11
 */
public class Test {
    final int a = 10;
    private int m;

    public Test() {
        m = 10;
    }

    public int inc() {
        return m + 1;
    }

    public static void main(String[] args) {

        int b = 20;

        int sum = new Test().a + b;
        System.out.println(sum);

        int inc = new Test().inc();
        System.out.println(inc);
    }
}
