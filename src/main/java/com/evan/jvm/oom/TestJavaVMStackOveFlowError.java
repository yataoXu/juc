package com.evan.jvm.oom;

/**
 * @Description
 * @ClassName TestJavaVMStackOveflowError
 * @Author Evan
 * @date 2020.03.21 17:36
 */
public class TestJavaVMStackOveFlowError {

    private static void StackOverFlow() {
        StackOverFlow();
    }

    public static void main(String[] args) {
        StackOverFlow();
    }
}
