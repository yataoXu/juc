package com.evan.jvm.gc;

/**
 * @Description
 * @ClassName TestGC
 * @Author Evan
 * @date 2020.03.21 19:26
 */
public class TestGC {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4,allocation5,allocation6,allocation7;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation3 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];    // 这里会出现一次 Minor GC
        allocation5 = new byte[4 * _1MB];
        allocation6 = new byte[4 * _1MB];
        allocation7 = new byte[4 * _1MB];
    }
}
