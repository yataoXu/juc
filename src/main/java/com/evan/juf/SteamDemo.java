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
        Person person1 = new Person(11,"a",21);
        Person person2 = new Person(12,"b",26);
        Person person3 = new Person(13,"c",26);
        Person person4 = new Person(14,"d",24);
        Person person5 = new Person(15,"e",22);

        List<Person> list = Arrays.asList(person1,person2,person3,person4,person5);
    }

}
