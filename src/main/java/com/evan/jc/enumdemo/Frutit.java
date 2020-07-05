package com.evan.jc.enumdemo;

public enum Frutit {

    APPLE {
        @Override
        public void printFruitInfo() {
            System.out.println("This is apple");
        }
    }, BANANA {
        @Override
        public void printFruitInfo() {
            System.out.println("This is apple");
        }
    }, WATERMELON {
        @Override
        public void printFruitInfo() {
            System.out.println("This is apple");
        }
    };

    //抽象方法
    public abstract void printFruitInfo();
}

class FrutitClient {
    public static void main(String[] args) {
        Frutit.APPLE.printFruitInfo();
        System.out.println(Frutit.valueOf("BANANA").name());

    }
}
