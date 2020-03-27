package com.evan.juc.cas;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @ClassName AutomicCount
 * @Author Evan
 * @date 2020.03.25 13:31
 */

public class AtomicCounter {

    private AtomicInteger integer  = new AtomicInteger();



    public AtomicInteger getInteger() {
        return integer;
    }

    public void setInteger(AtomicInteger integer) {
        this.integer = integer;
    }

    public void increment() {
        integer .incrementAndGet();
    }

    public void decrement() {
        integer .decrementAndGet();
    }
}
