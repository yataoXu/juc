package com.evan.juc;

/**
 * @Description
 * @ClassName LambdaExpressDemo
 * @Author Evan
 * @date 2020.02.03 23:04
 * 函数式编程
 */
public class LambdaExpressDemo {

    public static void main(String[] args) {

        // copy中括号，写死右箭头，落地大括号
        Foo foo1 = (int x, int y) -> {
            System.out.println("这是一个有入参，有返回值的方法实现");
            return x + y;
        };

        System.out.println(foo1.add(1, 2));
        System.out.println(foo1.mul(2, 3));
        System.out.println(Foo.div(2, 1));
    }
}

@FunctionalInterface
interface Foo {
    public int add(int x, int y);

    public default int mul(int x, int y) {
        return x * y;
    }

    public static int div(int x, int y) {
        return x / y;
    }
}
