package com.evan.core.reference;

import java.lang.ref.WeakReference;

public class WeakReferenceDemo {
    public static void main(String[] args) {
        String str = "test threadlocl memory leak";
        WeakReference<String> ref = new WeakReference<String>(str);
        System.gc();
        if (ref.get() != null) {
            System.out.println(ref.get().getClass());
        } else {
            System.out.println("weakReference 被 GC 回收");
        }

        WeakReference<String> ref1 = new WeakReference<String>(new String("test threadlocl memory leak"));
        System.gc();
        if (ref1.get() != null) {
            System.out.println(ref1.get().getClass());
        } else {
            System.out.println("weakReference 被 GC 回收");
        }
    }
}
