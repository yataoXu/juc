package com.evan.jvm;

/**
 * @Description
 * @ClassName VolatileDemo
 * @Author Evan
 * @date 2020.02.06 19:50
 */
public class VolatileDemo {
    public static void main(String[] args) {
        test02();
    }

    // 测试原子性
    private static void test02() {
        Data data = new Data();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.addOne();
                }
            }).start();
        }
        // 默认有 main 线程和 gc 线程
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println(data.a);
    }
}

class Data {
    volatile int a = 0;
    void addOne() {
        this.a += 1;
    }
}

