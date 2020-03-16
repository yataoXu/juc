package com.evan.core;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @ClassName test02
 * @Author Evan
 * @date 2020.02.27 22:04
 */
public class test02 {

    public static void main(String args[]) {
        List<String> list = new LinkedList();
        list.add("aa");
        list.add("bb");
        for (String string : list) {
            if ("bb".equals(string))
                list.add("cc");
        }
    }

}
