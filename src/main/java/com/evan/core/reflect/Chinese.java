package com.evan.core.reflect;
import	java.util.stream.Collectors;
import	java.util.Arrays;

import org.checkerframework.checker.units.qual.C;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @ClassName Chinese
 * @Author Evan
 * @date 2020.03.21 23:08
 */
public class Chinese implements HelloWorld {
    @Override
    public void sayHello() {
        System.out.println("中国人你好");
    }

    public static void main(String[] args) {
        Chinese chinese = new Chinese();
        System.out.println(chinese.getClass().getClassLoader());
        System.out.println(chinese.getClass().getInterfaces());
        System.out.println("------------------------");
        Method[] methods = chinese.getClass().getMethods();
        List<Method> collect = Arrays.stream(methods).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("------------------------");
        Annotation[] annotations = chinese.getClass().getAnnotations();
        List<Annotation> collect1 = Arrays.stream(annotations).collect(Collectors.toList());
        collect1.forEach(System.out::println);
        System.out.println("------------------------");
        Package aPackage = chinese.getClass().getPackage();
        System.out.println(aPackage);



    }
}
