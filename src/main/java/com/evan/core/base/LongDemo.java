package com.evan.core.base;

/**
 * @Description
 * @ClassName LongDemo
 * @Author Evan
 * @date 2020.03.23 14:20
 */
public class LongDemo {
    public static void main(String[] args) {
        System.out.println(12345 + 5432L);
        System.out.println("2 + 2 = " + 2 + 2);
        System.out.println("" + 'H' + 'a');
        System.out.println('H' + 'a');

        String letters = "ABC";
        char[] numbers = {'1', '2', '3'};

        // 能够直接输出 ??
        // 实际上我们知道字符串与任何数值的相加都会变为字符串，上述事例也不例外，
        //  numbers输出其实实际上是调用了Object.toString()方法，让numbers转变为'[c' + '@' + 无符号的十六进制数。
        System.out.println(letters + " easy as " + numbers); //ABC easy as [C@63947c6b

        // 修改程序
        System.out.println(letters + " easy as " + String.valueOf(numbers)); //ABC easy as 123

        // 动物庄园
        final String pig = "length: 10";
        final String dog = "length: " + pig.length();

        System.out.println("pig = " + pig + " dog = " + dog);
        System.out.println("Animal are equal: " + pig == dog);
        System.out.println("Animal are equal: " + pig.equals(dog));

        System.out.println("=================");
        System.out.println(descritpion()); //false
        System.out.println("=================");
//        description1();
        System.out.println("=================");
        int size = 7;
        System.out.println(size>>1);





    }

    public static boolean descritpion() {
        try {
            return true;
        } finally {
            return false;
        }
    }

    public static void description1() {
        try {
            System.out.println("Hello World");
            System.exit(0);
        } finally {
            System.out.println("GoodBye World");
        }
    }
}
