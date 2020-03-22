package com.evan.core.proxy;

public class TestClassLoader0 {

    public static void main(String[] args) {

        T0 t0 = new T0();
        T1 t1 = new T1();
        T0 t = new T1();

        Class<T0> t0Class = T0.class;
        Class<T1> t1Class = T1.class;

        System.out.println("t0 : " + t0.getClass().getName());
        System.out.println("t1 : " + t1.getClass().getName());
        System.out.println("t : " + t.getClass().getName());
        t = t0;
        System.out.println("t = t0 -->"+ t.getClass().getName());

        //      t0Class = t1Class; //imcompatible type requored .... found....
    }
}
