package com.evan.jc.finaldemo;

/**
 * @Description
 * @ClassName FinalTest01
 * @Author Evan
 * @date 2020.07.01 13:03
 */
public class FinalTest01 {
    /**
     * final成员变量不可更改
     */
    final int VALUE_ONE = 1;

    /**
     * 在声明final成员变量时没有赋值
     */
    final int BLANK_FINALVALUE;

    /**
     * 在声明final成员变量时没有赋值 必须在构造函数中，或构造函数执行之前（例如：构造代码块）对其赋值
     */
    public FinalTest01() {
        BLANK_FINALVALUE = 1;
    }

    /**
     * 设置参数为final，不可以改变x的值
     */
    int doIt(final int x) {
        //x = x + 1;// 这句编译错误
        return x + 1;
    }

    /**
     * 局部变量定义为final，不可改变i的值
     */
    public void doSomeThing() {
        final int i = 1;
//        i++;// 这句编译错误
    }
}