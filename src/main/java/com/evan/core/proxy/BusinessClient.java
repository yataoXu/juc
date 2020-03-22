package com.evan.core.proxy;
import java.lang.reflect.Proxy;
import	java.util.logging.Handler;

/**
 * @Description
 * @ClassName BusinessClient
 * @Author Evan
 * @date 2020.03.22 18:10
 */
public class BusinessClient {

    public static void main(String[] args) {
        // 元对象(被代理对象)
        Manager manager = new ManagerImpl();
        // 业务代理类（在元对象的基础上增加内容，比如这个函数运行需要时间，调试信息等）
        BusinessHandler handler = new BusinessHandler(manager);
        // 获得代理类($Proxy0 extends Proxy implements Manager)的实例.
        Manager manager1= (Manager) Proxy.newProxyInstance(manager.getClass().getClassLoader(),
                manager.getClass().getInterfaces(), handler);
        manager1.modify();
    }
}
