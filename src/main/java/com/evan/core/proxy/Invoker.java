package com.evan.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName Invoker
 * @Author Evan
 * @date 2020.03.22 11:36
 */
public class Invoker implements InvocationHandler {
    private AbstractClass abstractClass;


    public Invoker(AbstractClass abstractClass) {
        this.abstractClass = abstractClass;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用之前可以做一些处理
        doBefore();
        //调用代理对象的方法
        method.invoke(abstractClass, args);
        //调用之后也可以做一些处理
        doAfter();
        return null;
    }

    public void doBefore() {
        System.out.println("before.....");
    }

    public void doAfter() {
        System.out.println("after .....");
    }
}
