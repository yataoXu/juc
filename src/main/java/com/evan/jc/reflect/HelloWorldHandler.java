package com.evan.jc.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName HelloWorldHandler
 * @Author Evan
 * @date 2020.07.04 02:37
 */
public class HelloWorldHandler implements InvocationHandler {

    //要代理的原始对象
    private Object obj;


    public HelloWorldHandler(Object obj) {
        super();
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        //调用之前
        doBefore();
        //调用原始对象的方法
        result = method.invoke(obj, args);
        //调用之后
        doAfter();
        return result;
    }


    private void doBefore() {
        System.out.println("before method invoke");
    }

    private void doAfter() {
        System.out.println("after method invoke");
    }
}
