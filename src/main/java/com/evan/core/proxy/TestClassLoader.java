package com.evan.core.proxy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @ClassName TestClassLoader
 * @Author Evan
 * @date 2020.03.22 12:05
 */
public class TestClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {

        //第一种方式：java语言中任何一个java对象都有getClass()方法，getClass()返回运行类
        //c1代表引用保存内存地址指向堆中的对象，该对象代表的是 T 整个类
        T1 t = new T1();
        System.out.println(t.getClass().getName());

        //第二种方式：java中每个类型都有class属性 
        Class c2 = T1.class;
        System.out.println(c2);

        Class<?>[] interfaces = t.getClass().getInterfaces();
        List<Class<?>> collect = Arrays.stream(interfaces).collect(Collectors.toList());
        collect.forEach(System.out::println);


    }
}
