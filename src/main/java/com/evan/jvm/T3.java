package com.evan.jvm;

/**
 * @Description
 * -Xms1024m -Xmx1024m -XX:+PrintGCDetails
 * @ClassName T3
 * @Author Evan
 * @date 2020.02.06 13:18
 */
public class T3 {
    public static void main(String[] args) {
        long maxMemory = Runtime.getRuntime().maxMemory() ;//返回 Java 虚拟机试图使用的最大内存量。
        long totalMemory = Runtime.getRuntime().totalMemory() ;//返回 Java 虚拟机中的内存总量。
        System.out.println("MAX_MEMORY = " + maxMemory + "（字节）、" + (maxMemory / (double)1024 ) + "K");
        System.out.println("TOTAL_MEMORY = " + totalMemory + "（字节）、" + (totalMemory / (double)1024 ) + "K");
    }
}
