package com.evan.juf;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

/**
 * @Description
 * @ClassName TestMethodRef
 * @Author Evan
 * @date 2020.02.09 17:54
 * <p>
 * 方法引用：若Lambda体中的内容有方法已经实现了，我们可以使用“方法引用”
 * （可以理解为方法引用是Lambda 表达式的另外一种表现形式）
 * <p>
 * 主要有三种语法格式
 * <p>
 * 对象:: 实例方法名
 * <p>
 * 类:: 静态方法名
 * <p>
 * 类:: 实例方法名
 */
public class TestMethodRef {


    @Test
    public void test03() {
        BiPredicate<String, String> bp = (x, y) -> x.equals(y);
        BiPredicate<String, String> bp1 = String::equals;

//        bp.test("abc","abc");
        System.out.println(bp1.test("abc", "abc"));
        System.out.println(bp1.test("abc", "abd"));

    }

    // 类:: 静态方法名
    @Test
    public void test3() {
//        Comparator<Integer> com = (x,y)->Integer.compare(x,y);
        Comparator<Integer> com1 = Integer::compare;

        System.out.println(com1.compare(2, 4));
    }


    // 对象:: 实例方法名
    @Test
    public void test01() {
        Consumer<String> con = (x) -> System.out.println(x);
        con.accept("abc");
        Consumer<String> con1 = System.out::println;
        con1.accept("abc");


    }
}
