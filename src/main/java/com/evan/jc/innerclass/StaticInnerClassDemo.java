package com.evan.jc.innerclass;

/**
 * @Description
 * @ClassName StaticInnerClass
 * @Author Evan
 * @date 2020.06.30 21:33
 */

// 外围类
public class StaticInnerClassDemo {

    // 实例成员
    public int aa;
    // private的静态成员
    private static float f = 1.5f;

    static void println() {
        System.out.println("这是静态方法");
    }

    // protected的静态内部类
    protected static class StaticInnerClass {

        float a;

        public StaticInnerClass() {
            // 外围类的private静态变量
            a = f;

            //外围类的静态方法
            println();
        }
    }
}

class StaticInnerClassClient {

    public static void main(String[] args) {
        //创建静态内部类的对象
        StaticInnerClassDemo.StaticInnerClass staticInnerClass = new StaticInnerClassDemo.StaticInnerClass();
    }
}

