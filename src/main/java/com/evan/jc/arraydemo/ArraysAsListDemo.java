package com.evan.jc.arraydemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @ClassName asListsTest01
 * @Author Evan
 * @date 2020.07.01 16:20
 */


public class ArraysAsListDemo {
    public static void main(String[] args) {

        String[] s0 = {"Apple", "Orange", "Banana"};
        List<String> list = Arrays.asList("Apple", "Orange");
        list.forEach(System.out::println);


        /**
         * 陷阱一：入参为基本类型
         */
        int[] array = {1, 2, 3};
        List myList = Arrays.asList(array);
        System.out.println(myList.size()); // 1
        int[] array1 = {3, 4, 5};
        List myList1 = Arrays.asList(array, array1);
        System.out.println(myList1.size());// 2


        /**
         * 陷阱一：入参为包装类型
         * Integer[] array = { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) };
         */
        Integer[] integerArray = {1, 2, 3};
        List integerList = Arrays.asList(integerArray);
        System.out.println(integerList.size()); // 3
        integerList.stream().forEach(System.out::println);

        /**
         * 陷阱一：入参为 String
         * Integer[] array = { Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3) };
         */
        String[] arrStr = {"1", "2", "3"};
        List<String> listStr = Arrays.asList(arrStr);
        System.out.println(listStr.size()); // 3


        /**
         * 陷阱二
         * Exceptioninthread"main"java.lang.UnsupportedOperationException
         */
        myList.add("Pear");


        /**
         * asList()的替代方案
         */

        // type 1
        System.out.println("---------type 1---------");
        List arraysList = new ArrayList<>(Arrays.asList(array));
        System.out.println(arraysList.size());
        arraysList.add(3);
        arraysList.forEach(System.out::println);


        // type 2
        // 这个必须使用包装类型
        System.out.println("---------type 2---------");
        Integer[] array2 = {1, 2, 3};
        List<Integer> collect = Arrays.stream(array2).collect(Collectors.toList());
        collect.add(4);
        collect.forEach(System.out::println);


        // type 3
        //基本类型也可以实现转换（依赖boxed的装箱操作）
        System.out.println("---------type 3---------");
        int[] myArray2 = {1, 2, 3};
        List<Integer> collect1 = Arrays.stream(myArray2).boxed().collect(Collectors.toList());
        collect1.add(4);
        collect1.forEach(System.out::println);

        // type 4
        // import org.springframework.util.CollectionUtils;
//        int[] myArray2 = {1, 2, 3};
//        CollectionUtils.arrayToList(myArray2);

    }
}
