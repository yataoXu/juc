package com.evan.jc.arraydemo;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @ClassName LinkedListDemo
 * @Author Evan
 * @date 2020.07.02 01:02
 */
public class LinkedListDemo {
    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add(null);
        list.add("5");

        System.out.println(list.peek());
        System.out.println(list.element());
        System.out.println(list.poll());
        System.out.println(list);

    }
}
