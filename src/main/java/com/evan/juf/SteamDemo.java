package com.evan.juf;

import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @ClassName SteamDemo
 * @Author Evan
 * @date 2020.02.08 21:22
 */
public class SteamDemo {

    public static void main(String[] args) {
        Person person1 = new Person(11, "aaa", 21, Person.Status.BUSY);
        Person person2 = new Person(12, "bbb", 26, Person.Status.VOCATION);
        Person person3 = new Person(13, "ccc", 26, Person.Status.FREE);
        Person person4 = new Person(14, "dddd", 24, Person.Status.VOCATION);
        Person person5 = new Person(15, "eee", 27, Person.Status.FREE);

        List<Person> list = Arrays.asList(person1,person2,person3,person4,person5);
    }

}
