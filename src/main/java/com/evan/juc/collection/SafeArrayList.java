package com.evan.juc.collection;

import java.util.ArrayList;
import java.util.List;


/**
 * 面向对象的思想写好并发程序
 *  1. 把非线程安全的容器封装在对象内部
 *  2. 对外提供阻塞的方法
 *
 * @param <T>
 */
public class SafeArrayList<T> {

    /**
     * 封装ArrayList
     */
    List<T> list = new ArrayList<>();

    /**
     * 控制访问路径
     */
    public synchronized T get(int index) {
        return list.get(index);
    }

    public synchronized void set(int index, T value) {
        list.add(index, value);
    }

    public synchronized boolean addiFNotExitElement(T value) {
        if (!list.contains(value)) {
            list.add(value);
            return true;
        }

        return false;
    }

}
