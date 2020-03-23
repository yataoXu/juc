package com.evan.core.listsource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Collectors;

/**
 * @Description
 * @ClassName ForeachDeo
 * @Author Evan
 * @date 2020.03.23 10:31
 */
public class ForeachDemo {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        for (int i = 0; i < list1.size(); i++) {
            if ("hollis".equalsIgnoreCase(list1.get(i))) {
                list1.remove(i);
            }
            System.out.println(list1.get(i));
        }
        System.out.println(list1);

        System.out.println("===================");


//        for (String string : list1) {
//            if ("hollis".equals(string)) {
//                list1.remove(string);
//            }
//            System.out.println(string);
//        }

        System.out.println("===================");

        Iterator<String> iterator = list1.iterator();
//        while (iterator.hasNext()){
//            String next = iterator.next();
//            if ("hollis".equals(next)) {
//                list1.remove(next);
//            }
//        }
//        System.out.println(list1);

        List<String> userNames = new ArrayList<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        Iterator iterator1 = userNames.iterator();

        while (iterator1.hasNext()) {
            if (iterator1.next().equals("Hollis")) {
                iterator1.remove();
            }
        }
        System.out.println(userNames);

        System.out.println("===============");

        List<String> userNameFilter = userNames.stream().filter(userName -> !(userName.equals("Hollis"))).collect(Collectors.toList());
        userNameFilter.forEach(System.out::println);

        System.out.println("===============");

        ConcurrentLinkedDeque<String> userNameQueue = new ConcurrentLinkedDeque<String>() {{
            add("Hollis");
            add("hollis");
            add("HollisChuang");
            add("H");
        }};

        for (String userName : userNameQueue) {
            if (userName.equals("Hollis")) {
                userNameQueue.remove();
            }
        }
        System.out.println(userNameQueue);
        System.out.println("-------------");

        for (String userName : userNames) {
            if (userName.equals("Hollis")) {
                userNames.remove(userName);
                break;
            }
        }
        System.out.println(userNames);


        System.out.println("===========IndexOutOfBoundsException=============");
        for (int i = 0; userNames.iterator().hasNext(); i++) {
            if ("hollis".equals(userNames.get(i))) {
                userNames.remove(i);

            }
            System.out.println(userNames);
        }
    }
}
