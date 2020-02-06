package com.evan.jvm;

/**
 * @Description
 * @ClassName T2
 * @Author Evan
 * @date 2020.02.05 22:29
 */
public class T2 {

    public static void main(String[] args) {

        Thread thread = new Thread();
        thread.start();
        thread.start();
        // Exception in thread "main" java.lang.IllegalThreadStateException   非法的线程状态异常

        // 查看源码
       /**
        public synchronized void start() {

            if (threadStatus != 0)
                throw new IllegalThreadStateException();
            group.add(this);
            boolean started = false;
            try {
                start0();
                started = true;
            } finally {
                try {
                    if (!started) {
                        group.threadStartFailed(this);
                    }
                } catch (Throwable ignore) {
                }
            }
        }
         */

    }
}
