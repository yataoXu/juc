package com.evan.core;


class Eat {
}

class Drink {
}

class Father1 {
    public void doSomething(Eat arg) {
        System.out.println("爸爸在吃饭");
    }

    public void doSomething(Drink arg) {
        System.out.println("爸爸在喝水");
    }
}

class Child1 extends Father1 {
    public void doSomething(Eat arg) {
        System.out.println("儿子在吃饭");
    }

    public void doSomething(Drink arg) {
        System.out.println("儿子在喝水");
    }
}
//class Child1 extends Father1{
//    @Override
//    public void doSomething(Eat arg) {
//        System.out.println("儿子在吃饭");
//    }
//
//    @Override
//    public void doSomething(Drink arg) {
//        System.out.println("儿子在喝水");
//    }
//}


public class SingleDoublePai {
    public static void main(String[] args) {
        Father1 father = new Father1();
        Father1 child = new Child1();
        father.doSomething(new Eat());
        child.doSomething(new Drink());
    }
}