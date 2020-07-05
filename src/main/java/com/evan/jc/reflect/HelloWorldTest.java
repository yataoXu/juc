package com.evan.jc.reflect;

import java.lang.reflect.Proxy;

/**
 * @Description
 * @ClassName HelloWorldTest
 * @Author Evan
 * @date 2020.07.04 02:40
 */
public class HelloWorldTest {
    public static void main(String[] args) {
        HelloWorld helloWorld = new HelloWorldImpl();
        HelloWorldHandler helloWorldHandler = new HelloWorldHandler(helloWorld);

        HelloWorld helloWorld1 = (HelloWorld) Proxy.newProxyInstance(helloWorld.getClass().getClassLoader(),
                helloWorld.getClass().getInterfaces(),
                helloWorldHandler);

        helloWorld1.sayHelloWorld();


    }
}
