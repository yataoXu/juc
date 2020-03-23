package com.evan.core.listsource;

import java.util.ArrayList;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * @Description
 * @ClassName source
 * @Author Evan
 * @date 2020.03.23 01:11
 */
public class ArrayListDemo {
    public static void main(String[] args) {

        // 使用ImmutableList初始化一个List
        List<String> userNames = ImmutableList.of("Hollis", "hollis", "HollisChuang", "H");

        System.out.println("使用for循环遍历List");
        for (int i = 0; i < userNames.size(); i++) {
            System.out.println(userNames.get(i));
        }

        System.out.println("使用foreach遍历List");
        for (String userName : userNames) {
            System.out.println(userName);
        }

        System.out.println("===============");


    }
}
