package com.evan.core.proxy;

import javax.swing.*;
import java.util.Collections;

/**
 * @Description
 * @ClassName ClassB
 * @Author Evan
 * @date 2020.03.22 11:35
 */
public class ClassB{
    //implements
//} AbstractClass {
//    @Override
//    public void show() {
//        System.out.println("show ClassB method");
//    }

    public static void main(String[] args) {
        ClassB a=new ClassB();
        System.out.println(a.getClass());
        System.out.println(ClassB.class);

        System.out.println(JButton.class);
        System.out.println(String.class);
        System.out.println(Collections.class);

    }
}
