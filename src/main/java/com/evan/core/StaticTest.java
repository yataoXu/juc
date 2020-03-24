package com.evan.core;

class Super{
    public static int m = 11;
    static{
        System.out.println("执行了super类静态语句块");
    }
}


class Father extends Super{
    public static int m = 33;
    static{
        System.out.println("执行了父类静态语句块");
    }
}

class Child extends Father{
    static{
        System.out.println("执行了子类静态语句块");
    }
}

public class StaticTest{
    public static void main(String[] args){
        System.out.println(Child.m);
    }
}
