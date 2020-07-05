package com.evan.jc.arraydemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @ClassName ArrayListDemo
 * @Author Evan
 * @date 2020.07.01 19:23
 */
public class ArrayListDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(0);

        System.out.println(list.size());

        list.add("aa");

        list.stream().forEach(System.out::println);
    }
}
