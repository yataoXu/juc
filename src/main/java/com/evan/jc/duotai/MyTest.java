package com.evan.jc.duotai;

/**
 * @Description
 * @ClassName MyTest
 * @Author Evan
 * @date 2020.07.01 00:40
 */

class ParentClass {
    public String a = "parent";
    protected final String name = "parentClass";

    /**
     * final方法，子类既不能重写，也不能隐藏
     */
    public final void finalMethod() {
        System.out.println("final方法");
    }

    /**
     * 静态方法
     */
    public static void monday() {
        System.out.println("父类ParentClass的monday()方法");
    }

    /**
     * 可继承的成员方法
     */
    public void count() {
        System.out.println("父类ParentClass的count()方法");
    }

    /**
     * 内部类
     */
    class InnerClass {

        public InnerClass() {
            System.out.println("父类ParentClass的内部类");
        }
    }
}

class ChildClass extends ParentClass {
    public String a = "child";
    protected final String name = "ChildClass";

    //编译不通过
//    public final void finalMethod() {
//        System.out.println("final方法");
//    }

    /**
     * 静态方法
     */
    public static void monday() {
        System.out.println("子类ChildClass的monday()方法");
    }


    /**
     * 可继承的成员方法
     */
    @Override
    public void count() {
        System.out.println("子类ChildClass的count()方法");
    }

    /**
     * 内部类
     */
    class InnerClass {

        public InnerClass() {
            System.out.println("子类ChildClass的内部类");
        }
    }
}

public class MyTest {
    public static void main(String[] args) {
        ChildClass child = new ChildClass();
        ParentClass parent = child; //类型上转

        System.out.println("---------------变量的隐藏测试-----------------");
        child.a = "child modify";
        System.out.println("parent.a： " + parent.a); //parent.a： parent
        System.out.println("child.a： " + child.a); //child.a： child modify

        System.out.println("\n---------------静态方法的隐藏测试-----------------");
        parent.monday();//父类ParentClass的monday()方法
        child.monday();//子类ChildClass的monday()方法

        System.out.println("\n---------------方法的重写测试-----------------");
        parent.count(); //子类ChildClass的count()方法
        child.count();  //子类ChildClass的count()方法

        System.out.println("\n---------------内部类的隐藏测试-----------------");
        ParentClass.InnerClass pa = parent.new InnerClass(); //父类ParentClass的内部类
        ChildClass.InnerClass ch = child.new InnerClass();//子类ChildClass的内部类
    }
}
