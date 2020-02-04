package com.evan.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Description
 * @ClassName NoSafeDemo
 * @Author Evan
 * @date 2020.02.04 11:37
 */
public class NoSafeDemo {

    public static void main(String[] args) {

//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName() + "\t" + UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

//        Set<String> set = new HashSet();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
//        Map<String, String> map = new HashMap();
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                map.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }

}
