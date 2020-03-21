package com.evan.core.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArraysAsListDemo {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("Apple", "Orange");
        list.forEach(System.out::println);


        int[] array = {1, 2, 3};
        List myList = Arrays.asList(array);
        System.out.println(myList.size()); // 1
        int[] array1 = {3, 4, 5};
        List myList1 = Arrays.asList(array, array1);
        System.out.println(myList1.size());// 2


        // improve

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
//        import org.springframework.util.CollectionUtils;
//        int[] myArray2 = {1, 2, 3};
//        CollectionUtils.arrayToList(myArray2);






    }


}
