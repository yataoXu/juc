package com.evan.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @ClassName DynamicProxy
 * @Author Evan
 * @date 2020.03.22 16:10
 */
public class DynamicProxy {

    public static Object getBean(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Object obj = Class.forName(className).newInstance();
        InvocationHandler invocationHandler = new AOPFactory(obj);
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                invocationHandler);
    }

    public static void main(String[] args) {

        Hello hello = null;
        try {
            hello = (Hello) getBean("com.evan.core.proxy.HelloImplements");
            hello.setInfo("evan1", "evan2");
            hello.getInfos1();
            hello.getInfos2();
            hello.display();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }

    public void sum(int... a) {

    }
}
