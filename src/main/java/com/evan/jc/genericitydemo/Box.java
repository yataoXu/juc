package com.evan.jc.genericitydemo;

/**
 * @Description
 * @ClassName Box
 * @Author Evan
 * @date 2020.07.02 12:02
 */
public class Box<T> {

    private T object;

    public void set(T object) {
        this.object = object;
    }

    public T get() {
        return object;
    }

}

class BoxClient {
    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<>();
        Box<String> stringBox = new Box<>();
        Box<Short> shortBox = new Box<>();
        integerBox.set(12);
        stringBox.set("string");
        short s = 10;
        shortBox.set(s);
        System.out.println(integerBox.get());
        System.out.println(stringBox.get());
        System.out.println(shortBox.get());
    }
}