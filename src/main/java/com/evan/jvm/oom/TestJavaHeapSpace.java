package com.evan.jvm.oom;

/**
 * @Description
 * @ClassName TestJavaVMStackOveflowError
 * @Author Evan
 * @date 2020.03.21 17:36
 */
public class TestJavaHeapSpace {

    static final int SIZE = 2 * 1024 * 1024;

    public static void main(String[] args) {
        int[] i = new int[SIZE];
    }

}