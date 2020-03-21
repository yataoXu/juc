package com.evan.core.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName HelloWorldHandler
 * @Author Evan
 * @date 2020.03.21 23:09
 */
public class HelloWorldHandler implements InvocationHandler {

    //要代理的对象
    private Object obj;

    public HelloWorldHandler(Object obj) {
        super();
        this.obj = obj;
    }

    /**
     * 在代理实例上处理方法调用并返回结果
     *
     * @param proxy  代理类
     * @param method 被代理的方法
     * @param args   该方法的参数数组
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        // 调用之前
        doBefore();
        result = method.invoke(obj, args);
        // 调用之前
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
