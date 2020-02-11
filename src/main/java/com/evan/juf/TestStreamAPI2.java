package com.evan.juf;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @Description Stream API
 * @ClassName TestStreamAPI2
 * @Author Evan
 * @date 2020.02.11 17:33
 * <p>
 * 1. filter() :接收Lambda,从流中排出某些元素
 * 2. distinct() : 筛选，通过流所生成元素的hashcode()和equals()去除重复元素
 * 3. limit(): 截断流，使其元素不超过给定数量
 * 4. skip(n): 跳过元素，返回一个扔掉了前n个元素的流。若流中元素不足n个。则返回一个空流。
 * <p>
 * 中间操作不会执行任何操作，终止操作一次性全部处理
 */
public class TestStreamAPI2 {

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
        list.stream().filter((e) -> e.getAge() > 25).forEach(System.out::println);
    }


    // 外部迭代
    @Test
    public void test02() {
        Iterator<Person> iterator = list.iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }

    }

    @Test
    public void test03() {
        list.stream().filter((e) -> e.getAge() > 25).limit(1).forEach(System.out::println);
    }

    // 扔掉第一个
    @Test
    public void test04() {

        list.stream().filter((e) -> e.getAge() > 25).forEach(System.out::println);
        System.out.println("==============");
        list.stream().filter((e) -> e.getAge() > 25).distinct().forEach(System.out::println);
    }
}
