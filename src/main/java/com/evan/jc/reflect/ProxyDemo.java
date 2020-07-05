package com.evan.jc.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

/**
 * @Description
 * @ClassName ProxyDemo
 * @Author Evan
 * @date 2020.07.04 12:02
 */
public class ProxyDemo {
    public static void main(String[] args) {
        Class proxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        System.out.println(proxy.getName());

        System.out.println("-------Constructors List ---------");
        //按照以下格式输出构造函数列表，带参数
        //$Proxy0
        //$Proxy0(参数列表)

        Constructor[] constructors = proxy.getConstructors();

        for (Constructor collection : constructors) {
            String name = collection.getName();
            System.out.println(name);
            StringBuilder stringBuilder = new StringBuilder();
            Class[] parames = collection.getParameterTypes();
            stringBuilder.append(name);
            stringBuilder.append('(');
            for (Class parame : parames) {
                stringBuilder.append(parame.getName()).append(",");
            }
            if (parames != null && parames.length != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            stringBuilder.append(')');
            System.out.println(stringBuilder.toString());
        }

        System.out.println("-------Methods List ---------");
        //和上面的格式相同
        Method[] methods = proxy.getMethods();

        for (Method method : methods) {
            String name = method.getName();
            // System.out.println(name);
            StringBuilder stringBuilder = new StringBuilder();
            Class[] parames = method.getParameterTypes();
            stringBuilder.append(name);
            stringBuilder.append('(');
            for (Class parame : parames) {
                stringBuilder.append(parame.getName());
                stringBuilder.append(",");
            }
            if (parames != null && parames.length != 0)
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append(')');
            System.out.println(stringBuilder.toString());
        }

    }
}
