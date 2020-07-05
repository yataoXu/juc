package com.evan.jc;

import java.util.LinkedList;
import java.util.List;

public class ForeachDemo {

    public static void main(String[] args) {
        List<String> list = new LinkedList();
        for (int i = 1; i <= 10; i++) {
            list.add(i + "");

        }


        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        for (String str : list){
            System.out.println(str);
        }

        list.stream().forEach(System.out::println);


        int sign = 0;
        for (int i = 0; i < list.size(); i++) {

            if (i <= 4) {
                list.remove(i);
                i--;
                sign++;
                if (sign >= 5) {
                    break;
                }
            }
        }

        list.stream().forEach(System.out::println);

    }
}
