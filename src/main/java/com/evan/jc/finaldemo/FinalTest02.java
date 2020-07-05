package com.evan.jc.finaldemo;

import java.util.Arrays;
import java.util.Random;

import static java.lang.System.*;


/**
 * @Description
 * @ClassName FinalTest02
 * @Author Evan
 * @date 2020.07.01 13:09
 */

class Test {
    int i = 10;
}

public class FinalTest02 {
    static Random rand = new Random();
    /**
     * 声明一个final常量
     */
    private final int VALUE_1 = 9;
    /**
     * 声明一个final、static常量
     */
    private static final int VALUE_2 = 10;
    /**
     * 声明一个final的引用
     */
    private final Test test = new Test();
    /**
     * 声明一个非final引用
     */
    private Test test2 = new Test();
    /**
     * 声明一个定义为final的数组
     */
    private final int[] a = {1, 2, 3, 4, 5, 6};
    private final int i4 = rand.nextInt(20);

    /**
     *     static 只在类初始化执行一次，  知识点类初始化和实例初始化
     */
    private static final int i5 = rand.nextInt(20);

    @Override
    public String toString() {
        return i4 + " " + i5 + " ";
    }

    public static void main(String[] args) {
        FinalTest02 data = new FinalTest02();
        // 不能改变指向的对象的指针
        //data.test=new Test();
        //可以对指定为final的引用中的成员变量赋值
        //data.test.i =100;

        //不能改变定义为final的常量值
        // data.VALUE_2++;

        // 可以将没有定义为final的引用指向其他引用
        data.test2 = new Test();


        //不能为final修饰数组指向别的数组
//        data.a = new int[]{1, 2, 3, 4, 5, 6};
        for (int i = 0; i < data.a.length; i++) {
            // 可以修改final 修饰数组中的元素
            data.a[i] = 9;

        }
        Arrays.stream(data.a).forEach(out::println);

        out.println("data: i4,i5 = " + data);
        out.println("data2: i4,i5 = " + new FinalTest02());

    }

}