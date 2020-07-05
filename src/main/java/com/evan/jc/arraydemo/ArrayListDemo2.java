package com.evan.jc.arraydemo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description
 * @ClassName ArrayListDemo2
 * @Author Evan
 * @date 2020.07.01 21:41
 */
public class ArrayListDemo2 {
    public static void main(String[] args) {
        List<String> itrList = new ArrayList<>();
        itrList.add("1");
        itrList.add("2");
        itrList.add("3");
        itrList.add("4");
        itrList.add(null);
        itrList.add("5");
//        Iterator<String> iterator = itrList.iterator();
//        while (iterator.hasNext()) {
//            String nextStr = iterator.next();
//            if ("3".equals(nextStr)) {
//                // 操作1：正常不会报错
////                itrList.remove("3");
//                // 操作2：java.util.ConcurrentModificationException
////                itrList.add("3");
//
//                // 操作3：正常不会报错
////                iterator.remove();
//            }
//            if ("2".equals(nextStr)) {
//                // 操作3：Exception in thread "main" java.util.ConcurrentModificationException
//                 itrList.remove("2");
//            }
//        }
        System.out.println(itrList);
    }
}
