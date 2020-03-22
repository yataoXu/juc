package com.evan.core.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

//创建一个类，用来做测试
class TestClass {
    public void doSome() {
        System.out.println("====>咿呀咿呀喂");
    }
}

public class CglibIntereceptor {

    static class MethodInterceptorImpl implements MethodInterceptor {
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println(method);
            proxy.invokeSuper(obj, args);
            return null;
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestClass.class);
        enhancer.setCallback(new MethodInterceptorImpl());
        TestClass my = (TestClass) enhancer.create();
        my.doSome();
    }
}