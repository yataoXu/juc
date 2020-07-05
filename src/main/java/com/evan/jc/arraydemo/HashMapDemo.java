package com.evan.jc.arraydemo;

import java.util.*;

/**
 * @Description
 * @ClassName HashMapDemo
 * @Author Evan
 * @date 2020.07.02 01:52
 */
public class HashMapDemo {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(4, "d");
        map.put(5, "e");
        map.put(6, "e");


        map.forEach((k, v) ->
        {
            System.out.println("Item : " + k + " Count : " + v);

        });


        Set<Map.Entry<Integer, String>> entries = map.entrySet();

        Iterator<Map.Entry<Integer, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            System.out.println(entry.getKey() + entry.getValue());
        }


        Collection<String> values = map.values();
        values.stream().forEach(System.out::println);

    }
}
