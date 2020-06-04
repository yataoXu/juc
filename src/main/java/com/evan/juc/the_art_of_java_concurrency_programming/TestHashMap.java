package com.evan.juc.the_art_of_java_concurrency_programming;

import java.util.UUID;
import java.util.HashMap;
import java.util.UUID;

/**
 * @Description
 * @ClassName TestHashMap
 * @Author Evan
 * @date 2020.06.04 13:07
 */
public class TestHashMap {
    public static void main(String[] args) throws InterruptedException {
        HashMap<String, String> map = new HashMap(2);
//        Thread t = new Thread(() -> {
//            for (int i = 0; i < 10000000; i++) {
//                new Thread(() -> {
//                    map.put(UUID.randomUUID().toString(), "");
//                }, "ftf" + i);
//            }
//        }, "ftf");
//
//        t.start();
//        t.join();

        map.put("1", "1");
        map.put("2", "2");
        System.out.println("map size: "+map.size());
        map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ">>>>" + e.getValue()));

        System.out.println("=====================");
        map.put("3", "3");
        System.out.println("map size: "+map.size());
        map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ">>>>" + e.getValue()));

        System.out.println("=====================");
        map.put("4", "4");
        System.out.println("map size: "+map.size());
        map.entrySet().stream().forEach(e -> System.out.println(e.getKey() + ">>>>" + e.getValue()));

    }
}
