package com.evan.core;

import java.util.*;

/**
 * @Description 笔记名称： @SuppressWarnings的常见用法
 * @ClassName SuppressWarningDemo
 * @Author Evan
 * @date 2020.03.23 00:28
 */
public class SuppressWarningDemo {

    @SuppressWarnings(value = {"all"})
    public static void main(String[] args) {

        List list = new ArrayList<>();

        Set set = new HashSet<>();

        Map map = new HashMap<>();
    }

    @SuppressWarnings("unused")
    public void foo() {
    }



}
