package com.evan.juf;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
 * <p>
 * Lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值保持一致
 * 若Lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用Class::method
 * <p>
 * <p>
 * 构造器引用
 * <p>
 * 格式：
 * <p>
 * ClasssName::new
 * <p>
 * 注意： 需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致
 * <p>
 * <p>
 * 数组引用
 * Type[]:: new
 */
public class TestMethodRef {


    // 数组引用
    public void test07() {
        Function<Integer, String[]> fun = (x) -> new String[x];
        System.out.println(fun.apply(10));

        Function<Integer, String[]> fun1 = String[]::new;
        System.out.println(fun.apply(20));
    }

    // 构造器引用
    @Test
    public void test06() {
        Function<String, Employee> fun = (x) -> new Employee(x);
        Function<String, Employee> fun1 = Employee::new;
        System.out.println(fun1.apply("x"));
    }

    // 构造器引用
    @Test
    public void test05() {
        Supplier<Employee> sup1 = () -> new Employee();

        Supplier<Employee> sup = Employee::new;
        System.out.println(sup.get());
    }

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
