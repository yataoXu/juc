package com.evan.core.reflect;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @ClassName HelloWorldTest
 * @Author Evan
 * @date 2020.03.21 23:14
 */
public class HelloWorldTest {

    public static void main(String[] args) {
        Chinese chinese = new Chinese();
        HelloWorldHandler helloWorldHandler = new HelloWorldHandler(chinese);

        HelloWorld proxy= (HelloWorld) Proxy.newProxyInstance(
                chinese.getClass().getClassLoader(),
                chinese.getClass().getInterfaces(),
                helloWorldHandler);
        proxy.sayHello();

    }
}
