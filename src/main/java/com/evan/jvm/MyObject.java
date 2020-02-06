package com.evan.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName MyObject
 * @Author Evan
 * @date 2020.02.05 21:53
 */
public class MyObject {

    public static void main(String[] args) {
        Object object = new Object();
        // null
        // Object是jdk自带的，这个是C++ 写的，使用的是Bootstrap 加载器，Java去操作所以为null
        System.out.println(object.getClass().getClassLoader());  // null

        // sun.misc.Launcher$AppClassLoader@18b4aac2
        // 我们自己写的走的是 AppClassLoader 加载器
        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader()); //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(myObject.getClass().getClassLoader().getParent()); //sun.misc.Launcher$ExtClassLoader@1b6d3586
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent()); //null 其实是就是Bootstrap 类加载器


        //以这种方式操作Object
        System.out.println("--------------");
        System.out.println(object.getClass().getClassLoader()); //null
        System.out.println(object.getClass().getClassLoader().getParent()); //Exception in thread "main" java.lang.NullPointerException
        System.out.println(object.getClass().getClassLoader().getParent().getParent()); // Exception in thread "main" java.lang.NullPointerException

        // 操作深度太深，所以报错。




    }
}
