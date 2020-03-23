package com.evan.core.retryDemo;

class Father {

    void drinkMilk() {
        System.out.println("父亲喜欢喝牛奶");
    }
}

public class Son extends Father {

    @Override
    void drinkMilk() {
        System.out.println("儿子喜欢喝牛奶");
    }

    public static void main(String[] args) {
        Father son = new Son();
        son.drinkMilk();
    }
}
