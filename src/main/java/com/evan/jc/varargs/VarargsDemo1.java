package com.evan.jc.varargs;

import java.util.Arrays;

/**
 * @Description
 * @ClassName VarargsDemo1
 * @Author Evan
 * @date 2020.06.30 16:44
 */
public class VarargsDemo1 {
    public static void main(String[] args) {
        varArgMethod(1,2,4,56,7,8,9);
    }

    public static void varArgMethod(int... a) {

        int lenth = a.length;
        System.out.println("length:" +lenth);


        int sum = Arrays.stream(a).sum();

        System.out.println(sum);
    }


}
