package com.evan.core.reference;

import java.lang.ref.SoftReference;

/**
 * @Description 软引用
 * @ClassName R2_SoftReference
 * @Author Evan
 * @date 2020.06.14 16:30
 */
public class R2_SoftReference {
    public static void main(String[] args) {
        SoftReference<byte[]> soft = new SoftReference<>(new byte[1024 * 1024 * 10]);//10M
        System.out.println(soft.get());
        //gc回收
        System.gc();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(soft.get());

        //再分配一个数组,好heap(堆)放不下, 这个时候系统会回收一次, 如果不够,会把软引用回收
        byte[] bytes = new byte[1024 * 1024 * 15];
        System.out.println(soft.get());

    }
}

