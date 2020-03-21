package com.evan.jvm.oom;

/**
 * VM Args:-Xss160k
 */
public class TestJavaVMStackOveFlowError1 {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "调用dontStop()");
                dontStop();
            }).start();
        }
    }

    public static void main(String[] args) {
        TestJavaVMStackOveFlowError1 test = new TestJavaVMStackOveFlowError1();
        test.stackLeakByThread();
    }

}
