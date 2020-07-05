package com.evan.jc.genericitydemo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description
 * @ClassName GenericReading
 * @Author Evan
 * @date 2020.07.02 16:42
 */


class Fruit {
}

class Apple extends Fruit {
}

class Orange extends Fruit {
}


class GenericWriting {
    static List<Apple> apples = new ArrayList<>();
    static List<Fruit> fruit = new ArrayList<>();

    static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    static void f1() {
        for (int i = 0; i < 10; i++) {
            writeExact(apples, new Apple());
        }
        System.out.println(apples);

        writeExact(fruit, new Apple());
    }

    static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    static void f2() {

        for (int i = 0; i < 10; i++) {
            // 传递进来的只能是apple或apple的父类
            // 添加apple实例可以成功
            writeWithWildcard(apples, new Apple());
        }

//        List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error
        // 下限是 fruit 添加apple实例可以成功
        writeWithWildcard(fruit, new Apple());


        File file = new File("test.java");
        try {
            InputStream inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        f1();
        f2();
    }
}


class GenericReading2 {
    public static void main(String[] args) {
        // Wildcards allow covariance:
        List<? extends Fruit> flist = new ArrayList<>();
//        flist.add(new Apple());
//        flist.add(new Orange());
//        flist.add(new Fruit());
//        flist.add(new Object());
//        flist.add(null); // Legal but uninteresting
        // We Know that it returns at least Fruit:
        Fruit f = flist.get(0);
    }
}

class GenericReading {
    static List<Apple> apples = Arrays.asList(new Apple());
    static List<Fruit> fruit = Arrays.asList(new Fruit());

    static class Reader<T> {
        T readExact(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f1() {
        Reader<Fruit> fruitReader = new Reader<>();
        // Errors: List<Fruit> cannot be applied to List<Apple>.
        Fruit f = fruitReader.readExact(apples);
    }

    public static void main(String[] args) {
        f1();
    }
}

