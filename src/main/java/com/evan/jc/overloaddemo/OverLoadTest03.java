package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest03
 * @Author Evan
 * @date 2020.06.30 23:56
 */
public class OverLoadTest03 {
    public static void main(String[] args) {

        short s = 5;
        overloadMethod(s);// m1

        Integer i = 10;
        overloadMethod(i);//m1

        Short ss = 1;
        overloadMethod(ss); //m2

        overloadMethod(s, s);//m3

        overloadMethod(s, s, s);//m4
    }

    public static void overloadMethod(int a) { //m1

        System.out.println("调用  overloadMethod(int)");
    }

    public static void overloadMethod(Short in) {//m2

        System.out.println("调用  overloadMethod(short)");
    }

    public static void overloadMethod(int a, int b) {//m3

        System.out.println("调用  overloadMethod(int,int)");
    }

    public static void overloadMethod(short... s) { //m4

        System.out.println("调用  overloadMethod(short...)");
    }

    public static void overloadMethod(Integer... i) {//m5

        System.out.println("调用  overloadMethod(Integer...)");
    }
}
