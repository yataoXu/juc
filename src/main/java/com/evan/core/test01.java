package com.evan.core;

/**
 * @Description
 * @ClassName test01
 * @Author Evan
 * @date 2020.02.27 13:30
 */
public class test01 {

    public static void main(String[] args) {

//        byte b = 128; //编译错误，128超出byte类型所能表示的范围
        byte b = (byte) 128; // 编译通过  -128
        System.out.println(b);

        // char d = -2//字面常量为负数，编译不通过
        char c = (char) -100;//编译通过 输出结果：ﾜ

        System.out.println(c);


        double d = -123d;
        System.out.println(d);
        float f = -123f;
        System.out.println(f);

        int a = 5;
        a = a++;
        System.out.println(a);

        int i = 5;
        int j = (i++) + (i++) +(i++);

        System.out.println(j);
    }

}
