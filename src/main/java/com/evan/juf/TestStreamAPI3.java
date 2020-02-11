package com.evan.juf;

import java.util.ArrayList;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @Description
 * @ClassName TestStreamAPI3
 * @Author Evan
 * @date 2020.02.11 22:43
 * <p>
 * map: 接收lambda,将元素转换成其他形式或提取元素。接收一个函数作为参数，该函数会被应用到每个
 * flatMap: 接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流。
 * <p>
 * 就像list 中的add() 和 addALL()的区别
 */
public class TestStreamAPI3 {
    Person person1 = new Person(11, "aaa", 21, Person.Status.BUSY);
    Person person2 = new Person(12, "bbb", 26, Person.Status.VOCATION);
    Person person3 = new Person(13, "ccc", 26, Person.Status.FREE);
    Person person4 = new Person(14, "dddd", 24, Person.Status.VOCATION);
    Person person5 = new Person(15, "eee", 27, Person.Status.FREE);
    Person person6 = new Person(15, "eee", 27, Person.Status.VOCATION);
    Person person7 = new Person(15, "eee", 27, Person.Status.BUSY);

    List<Person> list = Arrays.asList(person1, person2, person3, person4, person5, person6, person7);


    @Test
    public void test01() {
        list.stream().map((p) -> p.getUsername().toUpperCase()).forEach(System.out::println);
        System.out.println("---------");
        list.stream().map(Person::getUsername).forEach(System.out::println);
        System.out.println("---------");


        Stream<Stream<Character>> rStream = list.stream().map((p) -> TestStreamAPI3.filterCharacter(p.getUsername()));
        rStream.forEach((sm) -> sm.forEach(System.out::println));


    }

    @Test
    public void test02() {
        // 一个需求
        Stream<Stream<Character>> rStream = list.stream().map((p) -> TestStreamAPI3.filterCharacter(p.getUsername()));//{{a,a,a},{b,b,b},{c,c,c}}
        rStream.forEach((sm) -> sm.forEach(System.out::println));

        System.out.println("==========================");
        // 优化
        list.stream().flatMap((person -> TestStreamAPI3.filterCharacter(person.getUsername()))).forEach(System.out::println);//{a,a,a,b,b,b,c,c,c}
    }

    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

    /**
     * 排序
     * <p>
     * sorted() : 自然排序
     * sorted(Comparator com) : 定制排序
     */

    @Test
    public void test03() {
        List<String> list2 = Arrays.asList("aaa", "ccc", "bbb", "ddd", "fff", "eee");
        list2.stream().sorted().forEach(System.out::println);
        System.out.println("===============");
        list.stream().sorted((e1, e2) -> {
            if (e1.getAge() == e2.getAge()) {
                return e1.getUsername().compareTo(e2.getUsername());
            } else {
                return e1.getAge() + "".compareTo(e2.getAge() + "");
            }
        }).forEach(System.out::println);
    }

    /**
     * allMatch : 检查是否匹配所有元素
     * anyMatch : 检查是否至少匹配一个元素
     * noneMatch ：检查是否没有匹配所有元素
     * findFirst : 返回第一个元素
     * findAny : 返回当前流中的任意一个元素
     * count : 返回流中元素的总个数
     * max : 返回流中最大值
     * min: 返回流中最小值
     */

    @Test
    public void test04() {
        boolean b1 = list.stream().allMatch((p) -> p.getStatus().equals(Person.Status.BUSY));
        System.out.println(b1); //false
        System.out.println("====================");

        boolean b2 = list.stream().anyMatch((p) -> p.getStatus().equals(Person.Status.BUSY));
        System.out.println(b2); //true
        System.out.println("====================");

        boolean b3 = list.stream().noneMatch((p) -> p.getStatus().equals(Person.Status.BUSY));
        System.out.println(b3); // false
        System.out.println("====================");

        Optional<Person> first = list.stream().sorted((e1, e2) -> Double.compare(e1.getAge(), e2.getAge())).findFirst();
        first.orElse(new Person());
        System.out.println(first.get());
        System.out.println("====================");

        Optional<Person> findAny = list.stream().filter((e1) -> e1.getStatus().equals(Person.Status.FREE)).findAny();
        System.out.println(findAny.get());

    }


    // T reduce(T identity, BinaryOperator<T> accumulator)
    // Optional<T> reduce(BinaryOperator<T> accumulator)
    //
    // 可以将流中元素反复结合起来，得到 一个值
    @Test
    public void test06(){
        List<Integer> list1 = Arrays.asList(1,22,4,5,6,73,342,6,46,32,67);
        Integer reduce = list1.stream().reduce(0, (x, y) -> x + y);
        System.out.println(reduce);
        System.out.println("===============================");
        Optional<Integer> reduce1 = list.stream().map(person -> person.getAge()).reduce(Integer::sum);
        System.out.println(reduce1.get());
    }
}
