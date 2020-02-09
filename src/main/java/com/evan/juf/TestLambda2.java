package com.evan.juf;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Description
 * @ClassName TestLambda2
 * @Author Evan
 * @date 2020.02.09 13:07
 * Lambda  表达式的基础语法： Java8中引入一个新的操作符"->" 该操作符称为箭头操作符或Lambda操作符，箭头操作符将Lambda表达式分成两个部分
 * 左侧：Lambda表达式的参数列表
 * 右侧：Lambda表达式中所需执行的功能，即Lambda体
 * <p>
 * 语法格式一：无参数，无返回值
 * ()->{
 * System.out.println("这是Lambda写法");
 * <p>
 * 语法格式二：有一个参数，无返回值（小括号可以省略）
 * (x) -> {
 * System.out.println(x);
 * };
 * x -> {
 * System.out.println(x);
 * };
 *
 * 语法格式三： 有两个以上的参数，有返回值，并且Lambda体中多个语句
 * Comparator<Integer> com = (x, y) -> {
 * System.out.println("函数式接口");
 * return Integer.compare(x, y);
 * };
 *
 * 语法格式四：若Lambda体中只有一条语句，return 和大括号都可以省略不写
 *Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
 *
 * 语法格式五： Lambda表达式的参数列表的数据类型可以省略不写，因为jvm编译器可以通过上下文推断出数据类型，即“类型推断”
 *
 * 二， lambda表达式需要“函数式接口”的支持
 * 函数式接口：接口中只有一个抽象方法的接口，称为函数式接口，可以使用注解@FunctionalInterface修饰。
 *
 */

public class TestLambda2 {

    @Test
    public void test1() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是匿名内部类写法");
            }
        };
        System.out.println("-------------------------");

        Runnable runnable1 = () -> {
            System.out.println("这是Lambda写法");
        };
    }

    @Test
    public void test02() {
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("有一个参数，无返回值");
        Consumer<String> consumer1 = (x) -> System.out.println(x);
        consumer1.accept("有一个参数，无返回值");
        Consumer<String> consumer2 = (x) -> {
            System.out.println(x);
        };
        consumer2.accept("有一个参数，无返回值");
        Consumer<String> consumer3 = x -> {
            System.out.println(x);
        };
        consumer3.accept("有一个参数，无返回值");
    }

    @Test
    public void test03() {
        Comparator<Integer> com = (x, y) -> {
            System.out.println("函数式接口");
            return Integer.compare(x, y);
        };
    }

    @Test
    public void test04() {
        Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
    }

}
