package com.evan.jc.overloaddemo;

/**
 * @Description
 * @ClassName OverLoadTest02
 * @Author Evan
 * @date 2020.06.30 23:50
 */
public class OverLoadTest02 {

    public static void m(Ancestor a) {
        System.out.println("方法一");
    }

    public static void m(Child c) {
        System.out.println("方法二");
    }
}

class Ancestor {
}

class Parent extends Ancestor {
}

class Child extends Parent {
}

class OverLoadTest02Client {
    public static void main(String[] args) {

        Ancestor ancestor = new Ancestor();

        Child ancestor2 = new Child();
        Parent ancestor3 = new Child();

        OverLoadTest02.m(ancestor);
        OverLoadTest02.m(ancestor2);
        OverLoadTest02.m(ancestor3);
    }
}
