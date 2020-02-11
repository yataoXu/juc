package com.evan.juf;

import com.evan.juf.ConsumerTest;
import com.sun.deploy.util.ArrayUtil;
import com.evan.juf.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Description
 * @ClassName TestStreamAPI
 * @Author Evan
 * @date 2020.02.11 15:48
 * <p>
 * <p>
 * Stream 的三个操作步骤
 * <p>
 * 1. 创建Stream
 * 2. 中间操作
 * 3. 终止操作
 */
public class TestStreamAPI {

    // 创建Stream
    @Test
    public void test1() {
        // 1. 可以通过Collection 系列集合提供的stream()或者parallelStream()
        List<String> list = new ArrayList<>();
        // 得到流
        Stream<String> stream = list.stream();

        // 2. 通过Arrays中静态方法stream() 获取数组流
        Employee[] employees = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(employees);

        // 3. 通过Stream类的静态方法of()
        Stream<String> aa = Stream.of("aa", "bb", "cc");

        // 4. 创建无限流
        Stream<Integer> iterate = Stream.iterate(0, (x) -> x + 2);
//        iterate.forEach(System.out::print);
        // 生成
        Stream.generate(() -> Math.random()).forEach(System.out::print);
    }
}
