package com.evan.core.base.staticandfinal;

class SelfCounter {
    private static int counter;
    private int id = counter++;


    @Override
    public String toString() {
        return "SelfCounter: " + id;
    }
}

class WithFinalFields {
    static final SelfCounter wffs = new SelfCounter();

    final SelfCounter wff = new SelfCounter();

    @Override
    public String toString() {
        return "wff= " + wff + ",\n wffs= " + wffs;
    }
}

public class StaticFinalDemo {


    public static void main(String[] args) {
        System.out.println("first create");
        System.out.println(new WithFinalFields());
        System.out.println("seconds create");
        System.out.println(new WithFinalFields());

    }
}