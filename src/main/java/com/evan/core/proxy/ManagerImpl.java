package com.evan.core.proxy;

/**
 * @Description
 * @ClassName ManagerImpl
 * @Author Evan
 * @date 2020.03.22 18:06
 */
public class ManagerImpl implements Manager {
    @Override
    public void modify() {
        System.out.println("*******modify()方法被调用");
    }
}
