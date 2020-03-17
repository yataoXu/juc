package com.evan.core.retryDemo;

/**
 *
 * 一个面试题，在for寻魂嵌套中 ，内部的for 循环怎么让外部的for循环结束。
 * 解决的问题：但是实际项目中我们往往可能需要只要内层循环终止了（break了），就把整个对应的外层循环也停止了，不需要再进行一些额外的循环判断。
 *
 * 使用
 * retry就是一个标记，标记程序跳出循环的时候从哪里开始执行，功能类似于goto。
 * retry一般情况下：一是常常跟随for循环或while循环出现；
 * 二就是常常跟随continue或是break，其中 break retry;用来表示退出retry循环，继续走循环下面的代码；而continue retry;则表示回到retry代码处，从循环体头开始执行。
 * retry的命名不是固定，只要符合Java的命名规范即可。例如retry1, retry2等等这样的名称都是合法的
 */
public class TestRetryDemo1 {

    public static void main(String[] args) {

        testContinue();

        System.out.println("-----------------");
        testContinue1();

        testBreak();
        testBreak1();
    }


    public static void testContinue() {
        retry:
        for (int i = 0; i < 10; i++) {
            System.out.println(" i = " + i);
            for (int j = 10; j < 15; j++) {
                System.out.println(" j = " + j);
                if (j == 13) {
                    continue retry;
                }
            }
            System.out.print(" >>> OK \n");
        }
    }



    public static void testContinue1() {

        for (int i = 0; i < 10; i++) {
            System.out.println(" i = " + i);
            for (int j = 10; j < 15; j++) {
                System.out.println(" j = " + j);
                if (j == 13) {
                    continue;
                }
            }
            System.out.println();
            System.out.print(" >>> OK \n");
        }
    }



    public static void testBreak() {

        for (int i = 0; i < 10; i++) {
            System.out.println(" i = " + i);
            for (int j = 10; j < 15; j++) {
                System.out.println(" j = " + j);
                if (j == 13) {
                    break;
                }
            }
            System.out.println();
            System.out.print(" >>> OK \n");
        }
    }



    public static void testBreak1() {
        retry:
        for (int i = 0; i < 10; i++) {
            System.out.println(" i = " + i);
            for (int j = 10; j < 15; j++) {
                System.out.println(" j = " + j);
                if (j == 13) {
                    break retry;
                }
            }
            System.out.println(" >>> OK");
        }
    }
}
//从上面的例子可以看出，在内层循环里面调用continue（或者break）后接着retry标识符，程序直接转到最外层for循环去处理了
