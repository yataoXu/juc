package com.evan.juf;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @Description
 * @ClassName TestLambda3
 * @Author Evan
 * @date 2020.02.09 13:45
 */
public class TestLambda3 {


    //  Predicate<T> 断言型接口
    @Test
    public void test04() {
        List<String> list = Arrays.asList("hello", "evan", "home", "Lambda", "ok");
        filterStr(list, (s -> s.length() > 3));
    }

    public List<String> filterStr(List<String> list, Predicate<String> pre) {
        List<String> strList = new ArrayList<>();
        for (String str : list) {
            if (pre.test(str)) {
                strList.add(str);
            }
        }
        return strList;
    }

    // Function<T, R> 函数型接口
    @Test
    public void test03() {

        String newStr = "\t\t\t我是函数型接口";
        strHandler(newStr, (str) -> str.trim());
    }

    // 用于处理字符串
    public String strHandler(String str, Function<String, String> fun) {
        return fun.apply(str);
    }

    // Supplier<T> 供应型接口
    @Test
    public void test02() {
        List<Integer> numList = getNumList(10, () ->
                (int) (Math.random() * 100));

        numList.stream().forEach(System.out::println);
    }

    public List<Integer> getNumList(int num, Supplier<Integer> sup) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Integer n = sup.get();
            list.add(n);

        }
        return list;
    }


    // Consumer<T> 消费型接口
    @Test
    public void test01() {

        happy(1000, (m) -> {
            System.out.println("这是方法体");
            System.out.println("这是入参：" + m);
        });

    }

    public void happy(double m, Consumer<Double> con) {
        con.accept(m);
    }
}
