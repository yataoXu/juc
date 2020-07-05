package com.evan.jc.innerclass;


/**
 * @Description
 * @ClassName RangeInnerClassDemo
 * @Author Evan
 * @date 2020.06.30 22:11
 */
public class RangeInnerClassDemo {

    private int a = 21;
    private static int b = 22;

    //静态域中的局部内部类
    static {
        class LocalClass1 {
            // 错误，在静态的作用域中无法访问对象成员
            //  int z = a;
            int zz = b;
        }
    }

    {
        // 实例初始化块中的局部内部类
        class localClass2 {
            int z = a;
            int zz = b;

        }
    }

    public RangeInnerClassDemo() {
        int x = 2;
        final int y = 3;
        //若放开此行注释，编译无法通过，因为局部变量x已经是final类型
        // x = 3;
        //构造器中的局部内部类
        class localClass3 {
            int z = y; //可以访问final的局部变量
            int b = a;//可以访问类的所有成员
            //访问没有用final修饰的局部变量
            int c = x;

        }
    }

    public void createRunnable() {
        final int x = 4;
        //方法中的局部内部类
        class LocalClass4 implements Runnable {

            @Override
            public void run() {
                System.out.println("局部final变量：" + x);
                System.out.println("对象成员变量：" + a);
            }

        }
    }

}


