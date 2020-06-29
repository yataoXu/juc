package com.evan.juc.collection;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapDemo {

    private final static ConcurrentHashMap<String, String> map = new ConcurrentHashMap();


    public static void main(String[] args) {

         final int NCPU = Runtime.getRuntime().availableProcessors();

        System.out.println(NCPU);

        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.put("e", "e");

        System.out.println(map.size());

    }
}
