package com.evan.jc.innerclass;

/**
 * @Description
 * @ClassName NonNameInnerClassDemo
 * @Author Evan
 * @date 2020.06.30 22:25
 */
public class NonNameInnerClassDemo {

    private int x = 5;

    void createThread() {
        final int a = 10;
        int b = 189;
        Thread thread1 = new Thread("AAA") {
            //访问成员变量
            int c = x;
            //final的局部变量
            int d = a;
            //访问没有用final修饰的局部变量
            int e = b;

            @Override
            public void run() {
                System.out.println("这是线程AAA");

            }
        };

        // 匿名内部类实现Runnable接口
        Runnable r = new Runnable() {

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "线程运行中");
            }
        };

        Thread thread2 = new Thread(r, "BBB");

        thread1.start();
        thread2.start();
    }
}

class NonNameClient {
    public static void main(String[] args) {
        NonNameInnerClassDemo demo = new NonNameInnerClassDemo();
        demo.createThread();
    }
}
