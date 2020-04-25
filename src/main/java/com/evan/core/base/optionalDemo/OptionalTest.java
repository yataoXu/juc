package com.evan.core.base.optionalDemo;

import java.util.NoSuchElementException;
import java.util.Optional;

import lombok.ToString;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class OptionalTest {

    //创建一个空的 Optional 对象，我们通常使用它的静态方法 empty()
    @Test
    public void whenCreatesEmptyOptional_thenCorrect() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.isPresent());
        assertFalse(empty.isPresent());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateOptionalOfempty_theNull() {
        Optional<String> empty = Optional.empty();
        System.out.println(empty.get());
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
        System.out.println(opt.isPresent());  // true
        assertTrue(opt.isPresent());
    }

    //如果我们传入一个空引用，它不会引发异常，而是返回一个空的 Optional 对象。
    @Test
    public void givenNull_whenCreatesNullable_thenCorrect() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        System.out.println(opt.isPresent()); // flase
        assertFalse(opt.isPresent());
    }


    // 访问 Optional 对象的值

    //从 Optional 实例中取回实际值对象的方法之一是使用 get() 方法
    @Test
    public void whenCreateOfNullableOptional_thenOk() {
        String name = "John";
        Optional<String> opt = Optional.ofNullable(name);

        assertEquals("John", opt.get());
    }

    // 上面这个方法会在值为 null 的时候抛出异常。要避免异常，你可以选择首先验证是否有值：
    @Test
    public void whenCheckIfPresent_thenOk() {
        User user = new User("john@gmail.com", "1234");
        Optional<User> opt = Optional.ofNullable(user);
        assertTrue(opt.isPresent());

        assertEquals(user.getEmail(), opt.get().getEmail());
    }


    //    返回默认值
    @Test
    public void whenEmptyValue_thenReturnDefault() {
        User user = null;
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals(user2.getEmail(), result.getEmail());
    }

    // 如果对象的初始值不是 null，那么默认值会被忽略
    @Test
    public void whenValueNotNull_thenIgnoreDefault() {
        User user = new User("john@gmail.com", "1234");
        User user2 = new User("anna@gmail.com", "1234");
        User result = Optional.ofNullable(user).orElse(user2);

        assertEquals("john@gmail.com", result.getEmail());
    }

    // 流式调用
    @Test
    public void whenFilter_thenOk() {
        UserAddressDetail user = new UserAddressDetail();

        String result = Optional.ofNullable(user).flatMap(u -> u.getAddress()).flatMap(a -> a.getCountry()).flatMap(c->c.getName()).orElse("default");
        assertEquals(result, "default");
    }
}