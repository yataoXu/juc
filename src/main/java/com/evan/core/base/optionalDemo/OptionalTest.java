package com.evan.core.base.optionalDemo;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OptionalTest {

    //创建一个空的 Optional 对象，我们通常使用它的静态方法 empty()
    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());
        assertFalse(empty.isPresent());
    }

    //使用静态方法 of() 创建一个 Optional 对象。
    @Test
    public void givenNonNull_whenCreatesNonNullable_thenCorrect() {
        String name = "baeldung";
        Optional<String> opt = Optional.of(name);
        System.out.println(opt.isPresent());
        assertTrue(opt.isPresent());
    }

    //传递给 of() 方法的参数不能为 null。否则，我们将获得 NullPointerException。
    @Test(expected = NullPointerException.class)
    public void givenNull_whenThrowsErrorOnCreate_thenCorrect() {
        String name = null;
        Optional.of(name);
    }

    //如果我们期望一些空值，则可以使用 ofNullable()方法。
    @Test
    public void givenNonNull_whenCreatesNullable_thenCorrect() {
        String name = "baeldung";
        Optional<String> opt = Optional.ofNullable(name);
        System.out.println(opt.isPresent());
        assertTrue(opt.isPresent());
    }

    //如果我们传入一个空引用，它不会引发异常，而是返回一个空的 Optional 对象。
    @Test
    public void givenNull_whenCreatesNullable_thenCorrect() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
//        System.out.println(opt.get());
        System.out.println(opt.isPresent());
        assertFalse(opt.isPresent());
    }
}