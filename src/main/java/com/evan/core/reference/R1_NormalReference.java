package com.evan.core.reference;

import java.io.IOException;

/**
 * @Description
 * @ClassName R1_NormalReference
 * @Author Evan
 * @date 2020.06.14 16:19
 */
public class R1_NormalReference {
    public static void main(String[] args) throws IOException {
        //正常引用
        A c = new A();
        c = null;//没人指向
        System.gc();//DisableExplicitGC

        //阻塞一下,方便看结果
        System.in.read();
    }
}
