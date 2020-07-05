package com.evan.jc;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorDemo {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList();
        for (int i = 1; i <= 10; i++) {
            list.add(i);

        }

        Iterator<Integer> iterator = list.iterator();

        while (iterator.hasNext()){
            if( iterator.next()% 2==0){
                iterator.remove();
            }
        }

        list.stream().forEach(System.out::println);


    }
}
