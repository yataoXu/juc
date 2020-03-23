package com.evan.core.listsource;

import java.util.LinkedList;

/**
 * @Description
 * @ClassName LinkedListDemo
 * @Author Evan
 * @date 2020.03.23 16:50
 */
public class LinkedListDemo {

    public static void main(String[] args) {
        LinkedList<String> list = new LinkedList() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};
    }
}
