package com.evan.core.base;

import java.util.Objects;

public class TestEquals {
    public static void main(String[] args) {
//        String a = "abc";
//        String b = "bac";
//        System.out.println(a.equals(b));

        UserEquals user1user1 = new UserEquals("zhangsan", 20, 1);
        UserEquals user1user2 = new UserEquals("zhangsan", 20, 1);

        System.out.println(user1user1.equals(user1user2));

    }
}


class UserEquals {
    private String name;
    private int age;
    private int sex;

    public UserEquals(String name, int age, int sex) {
        this.name = name;
        this.age = age;
        this.sex = sex;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSex(), getName(), getAge());
    }

    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        if (!(anObject instanceof UserEquals)) return false;

        UserEquals anotherUser = (UserEquals) anObject;

        return getName().equals(anotherUser.getName()) && getAge() == anotherUser.getAge() && getSex() == anotherUser.getSex();
    }
}
