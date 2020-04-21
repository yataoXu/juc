package com.evan.core.base;

/**
 * @Description
 * @ClassName DDD
 * @Author Evan
 * @date 2020.04.07 17:48
 */


class Super{

    public int dd = 4;
    public int getLength() {
        return 4;
    }
}

public class Sub extends Super {
    public int dd = 5;
    public int getLength() {
        return 5;
    }

    public static void main(String[] args) {

        Super sup = new Super();
        Super sub = new Sub();
        System.out.println(sup.getLength()+"------"+sub.getLength());
        System.out.println(sup.dd+"------"+sub.dd);
    }

}

