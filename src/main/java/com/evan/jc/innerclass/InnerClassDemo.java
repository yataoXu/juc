package com.evan.jc.innerclass;

/**
 * @Description
 * @ClassName InnerClassDemo
 * @Author Evan
 * @date 2020.06.30 21:46
 */
public class InnerClassDemo {//外围类
    //实例成员
    private int aa;
    //private的静态成员
    private static float f = 1.5f;
    private InnerClass innerClass;

    public void initInnerClass() {
        System.out.println("内部类的初始化方法");
    }

    public InnerClassDemo() {
    }

    public InnerClassDemo(double innerClassaa) {
        this.innerClass = new InnerClass(innerClassaa);
    }

    public void createInnerClass(double innerClassaa) {
        //外围类的成员方法中创建成员内部类对象
        InnerClass innerClass = new InnerClass(innerClassaa);
    }

    //成员内部类
    class InnerClass {
        //与围类的变量aa的名字重复
        private double aa;

        public InnerClass(double aa) {
            // 明确指定两个aa的所属
            this.aa = aa;
            this.aa = InnerClassDemo.this.aa + InnerClass.this.aa + f;
            System.out.println("inner class aa : " + this.aa);
            initInnerClass();
        }
    }
}

//class ChildClass extends InnerClassDemo.InnerClass {
//    public ChildClass(double aa) {
//        super(aa);
//    }
//}

//其他类
class InnerClassClient {
    public static void main(String[] args) {
        //其他类中创建成员内部类

        // 外部类对象
        InnerClassDemo oc = new InnerClassDemo();
        oc.createInnerClass(14);
        //创建内部类对象
        InnerClassDemo.InnerClass innerClass = oc.new InnerClass(12);
    }
}