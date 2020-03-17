package com.evan.juc.method.signature;


interface Smoking {

    public abstract void smoking();

    static void operateSmoking(Smoking smoking) {
        smoking.smoking();
    }
}


class Student implements Smoking {

    @Override
    public void smoking() {
        System.out.println("扫脸就是一巴掌");
    }
}

public class SignatureIsInterface {

    public static void main(String[] args) {

        Smoking.operateSmoking(new Student());

        Smoking.operateSmoking(new Smoking() {
            @Override
            public void smoking() {
                System.out.println("不准吸烟");
            }
        });
        Smoking.operateSmoking(() -> {
            System.out.println("不准吸烟");
        });

    }
}


// 总结：类当成方法参数传入，其实就是传对象。抽象类和接口其实就是传其子类或实现类的对象。