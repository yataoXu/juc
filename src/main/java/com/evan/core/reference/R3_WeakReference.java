package com.evan.core.reference;

import java.lang.ref.WeakReference;

/**
 * @Description
 * @ClassName R3_WeakReference
 * @Author Evan
 * @date 2020.06.14 16:31
 */
public class R3_WeakReference {
    public static void main(String[] args) {
        WeakReference<A> weak = new WeakReference<A> (new A());
        System.out.println(weak.get());
        //gc回收
        System.gc();
        //遇到GC就会被回收
        System.out.println(weak.get());

    }
}
