package com.evan.jvm;

import java.util.Random;

/**
 * @Description
 * @ClassName T4
 * -Xms10m -Xmx10m -XX:+PrintGCDetails
 * @Author Evan
 * @date 2020.02.06 13:28
 */
public class T4 {

    public static void main(String[] args) {
        String str = "evan" ;
        while(true)
        {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999) ;
        }

    }
}
