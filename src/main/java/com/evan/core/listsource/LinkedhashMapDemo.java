package com.evan.core.listsource;

import java.util.*;

/**
 * @Description
 * @ClassName LinkedhashMapDemo
 * @Author Evan
 * @date 2020.04.14 19:15
 */
public class LinkedhashMapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<>();

//        Map<String, String> map1 = new HashMap<>();
//
//        map1.put("a", "a1");
        map.put("b", "b1");
        map.put("c", "c1");
        map.put("d", "d1");


        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
            System.out.println("=====================");
        }


    }
}
