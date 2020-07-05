package com.evan.jc.interfacedemo;

interface A {
    void sameMethodA();
}

interface B {
    void sameMethodA();

    void sameMethodB(int a);//返回类型是void
}

interface C {
    int sameMethodB(int a);//返回类型是 int
}

interface D extends A, B { //编译通过，即使A，B定义了相同方法

}

//interface E extends B,C{//编译失败 B

//}
public class ddd {
}
