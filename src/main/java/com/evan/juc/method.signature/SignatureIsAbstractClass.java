package com.evan.juc.method.signature;



abstract class Animal {
    public abstract void eat();


    public static void operateAnimal(Animal a) {
        a.eat();
    }
}

class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("猫吃鱼");
    }
}

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("狗吃屎");
    }
}


public class SignatureIsAbstractClass {

    public static void main(String[] args) {

        Animal.operateAnimal(new Dog());
        Animal.operateAnimal(new Cat());
        Animal.operateAnimal(new Animal() {
            @Override
            public void eat() {
                System.out.println("羊吃草");
            }
        });
    }
}

// 要求传入父类对象，但可以传入任意子类对象，这样就使得扩展性得到提高
// 而且传入什么类，就调用什么类的功能，这就是多态的实现。