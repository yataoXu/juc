package com.evan.juc.method.signature;

import lombok.Data;


class Person {

    public void eat(){
        System.out.println("吃饭");
    }

    public static void operatePerson (Person person) {
        person.eat();
    }

}


public class  SignatureIsClass {

    public static void main(String[] args) {
        Person person = new Person();
        Person.operatePerson(person);
    }
}
