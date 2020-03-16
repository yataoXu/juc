package com.evan.core;

import org.junit.Test;

import java.time.*;

/**
 * @Description
 * @ClassName LocalDateDemo
 * @Author Evan
 * @date 2020.02.20 22:33
 */
public class LocalDateDemo {

    @Test
    public void test01() {
        LocalDate now = LocalDate.now();
        System.out.println(now);

        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);

        System.out.println(now1.getMonthValue());
        System.out.println(now1.getMonth());

        Instant now2 = Instant.now(); // 默认获取UTC市区
        OffsetDateTime offsetDateTime = Instant.now().atOffset(ZoneOffset.ofHours(8));// 默认获取UTC市区

        System.out.println(now2);
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.toEpochSecond());

    }



    // 计算两个时间的间隔
    @Test
    public void test02() throws InterruptedException {
        Instant now = Instant.now();
        Thread.sleep(1000);
        Instant now1 = Instant.now();

        Duration between = Duration.between(now, now1);
        System.out.println(between);
        System.out.println(between.toMinutes());
    }
}
