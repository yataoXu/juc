package com.evan.core.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

import java.lang.ref.ReferenceQueue;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description
 * @ClassName PhantomReferenceDemo
 * @Author Evan
 * @date 2020.03.27 15:02
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) {
        ReferenceQueue referenceQueue = new ReferenceQueue();

        List<byte[]> bytes = new ArrayList<>();
        PhantomReference<List<byte[]>> phantomReference = new PhantomReference(bytes, referenceQueue);
        System.out.println(phantomReference.get());

        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                bytes.add(new byte[1024 * 1024]);
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference poll = referenceQueue.poll();
                if (poll != null) {
                    System.out.println("虚引用被回收了：" + poll);
                }
            }
        }).start();


        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap();

        concurrentHashMap.put("a", "a");



        Iterator<Map.Entry<String, String>> iterator = concurrentHashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println("key :" + entry.getKey() + "   value:" + entry.getValue());

        }

    }
}
