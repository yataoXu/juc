package com.evan.juc.cas.prepare;

import lombok.Data;

/**
 * @Description
 * @ClassName Counter
 * @Author Evan
 * @date 2020.03.25 13:08
 */
public class Counter {
     int count = 0;

    public void addCount() {
        count++;
    }

    public void decCount() {
        count--;
    }

    public int getCount() {
        return count;
    }
}
