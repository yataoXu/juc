package com.evan.jc.equalsdemo;

import java.util.Objects;

/**
 * @Description
 * @ClassName MyTest01
 * @Author Evan
 * @date 2020.07.01 15:15
 */
public class MyTest01 {
    public static void main(String[] args) {


        System.out.println("+++++++++++++++| ==比较 |++++++++++");
        // 基本数据类型比较
        int a = 1;
        int b = 1;
        char c = 'a';
        char d = 'a';
        System.out.println("a==b  :  " + (a == b));
        System.out.println("c==d  :  " + (c == d));

        // 对象比较
        User userEqOne = new User();
        User userEqTwo = new User();
        /**
         * 对象 userOne 和 userTwo 虽然都是 User 的实例，
         * 但对应了堆内存的不同区域，因此他们的引用也不同，所以为 false
         */
        System.out.println("userOne==userTwo : " + (userEqOne == userEqTwo));


        System.out.println("+++++++++++++++| equals()比较 |++++++++++");

        // 未重写equals方法的类
        User userOne = new User();
        User userTwo = new User();
        System.out.println("userOne.equals(userTwo) : " + (userOne.equals(userTwo)));
        //重写了equals方法的类
        String aString = "1111";
        String bString = "1111";
        String cString = "1111";

        System.out.println("aString.equals(bString) : " + (aString.equals(bString)));
        System.out.println("aString.equals(cString) : " + (aString.equals(cString)));
        System.out.println(aString.hashCode());
        System.out.println(bString.hashCode());
        System.out.println(cString.hashCode());

        //重写equals方法
        User userEQOne = new User("evan", "123");
        User userEQTwo = new User("evan", "123");

        System.out.println("userEQOne.equals(userEQTwo) : " + (userEQOne.equals(userEQTwo)));


    }

    static class User {
        private String name;
        private String pass;


        public User(String name, String pass) {
            this.name = name;
            this.pass = pass;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return name.equals(user.name) &&
                    pass.equals(user.pass);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, pass);
        }

        public User() {
        }
    }


}
