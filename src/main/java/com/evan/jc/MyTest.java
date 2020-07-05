package com.evan.jc;

public class MyTest {

    public static void main(String[] args) {
        String s = "Love You";
        String s2 = "Love"+" You";
        String s3 = s2 + "";
        String s4 = new String("Love You");
        System.out.println("s == s2 "+(s==s2));
        System.out.println("s == s3 "+(s==s3));
        System.out.println("s == s4 "+(s==s4));
    }
}
