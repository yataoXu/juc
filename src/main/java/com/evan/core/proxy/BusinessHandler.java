package com.evan.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName BusinessHandler
 * @Author Evan
 * @date 2020.03.22 18:07
 */
public class BusinessHandler implements InvocationHandler {

    private Object obj;

    public BusinessHandler(Object obj){
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("动态代理前 可以加验证");
        Object result = method.invoke(obj, args);
        System.out.println("动态代理后 可以加日志");

        return result;
    }
}
