package com.evan.core.base.enumpk;

import java.util.Arrays;

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

    public static void main(String[] arg) {
        Frutit.APPLE.printFruitInfo();
        Arrays.stream(Frutit.values()).forEach(System.out::println);

    }


}
