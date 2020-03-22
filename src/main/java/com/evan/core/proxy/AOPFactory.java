package com.evan.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName AOPFactory
 * @Author Evan
 * @date 2020.03.22 16:05
 */
public class AOPFactory implements InvocationHandler {

    private Object obj;

    public AOPFactory(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("\n\n====>调用方法名：" + method.getName());
        Class<?>[] variables = method.getParameterTypes();
        for (Class<?> typevariables : variables) {
            System.out.println("=============>" + typevariables.getName());
        }

        printInfo("传入的参数为：", args);
        Object result = method.invoke(obj, args);
        printInfo("返回的参数为：", result);
        printInfo("返回值类型为：", method.getReturnType());
        return result;
    }

    public void printInfo(String info, Object... args) {
        System.out.println(info);
        if (args == null) {
            System.out.println("\t空值。");
        } else {
            for (Object obj : args) {
                System.out.println(obj);
            }
        }
    }
}
