package com.evan.core.proxy;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @ClassName ProxyTest1
 * @Author Evan
 * @date 2020.03.22 11:41
 */
public class ProxyTest1 {
    public static void main(String[] args) {
        Invoker invoker1=new Invoker(new ClassA());
        AbstractClass abstractClass = (AbstractClass) Proxy.newProxyInstance(
                AbstractClass.class.getClassLoader(),
                new Class [] {
                    AbstractClass.class
                },
                invoker1);
        abstractClass.show();
    }
}
