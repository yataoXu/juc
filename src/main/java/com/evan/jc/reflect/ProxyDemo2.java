package com.evan.jc.reflect;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @Description
 * @ClassName ProxyDemo2
 * @Author Evan
 * @date 2020.07.04 12:08
 */
public class ProxyDemo2 {
    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        Class proxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor constructor = proxy.getConstructor(InvocationHandler.class);

        //第一种创建实例对象的方法
        class MyInvocationHandler implements InvocationHandler {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                // TODO Auto-generated method stub
                return null;
            }
        }
        Collection collection1 = (Collection) constructor.newInstance(new MyInvocationHandler());

        //第二种创建实例对象的方法
        Collection collection2 = (Collection) constructor.newInstance(new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args)
                    throws Throwable {
                // TODO Auto-generated method stub
                return null;
            }

        });

        //第三种创建实例对象的方法,直接用Proxy自带的方法，newProxyInstance()
        Collection collection3 = (Collection) Proxy.newProxyInstance(
                Collection.class.getClassLoader(),//第一个参数：类加载器
                new Class[]{Collection.class},//与目标相同的接口
                new InvocationHandler() {//参数：一个对象
                    ArrayList target = new ArrayList();

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        // TODO Auto-generated method stub
                        Object retVal = method.invoke(target, args);//此处可以修改参数哟！
                        return retVal;
                    }
                });
        collection3.add("123");
        collection3.add("123");
        collection3.add("123");
        System.out.println(collection3.size());
        System.out.println(collection3.getClass().getName());//为什么返回的类名不是ArrayList?因为只有这三个hashCode,equals,toString从Object继承的方法交给Handler处理，其余的方法Proxy有处理的方式。
    }

}