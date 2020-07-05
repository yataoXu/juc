package com.evan.jc.finaldemo;

/**
 * @Description
 * @ClassName FinalStaticDemo
 * @Author Evan
 * @date 2020.07.01 13:52
 */
class FinalStaticDemo {

    private static final int i = 10;

    public static void main(String[] args) {
        System.out.println(i);
    }
}

class FinalStaticDemo2 {
    private static final int i;

    static {
        i = 10;
    }


    public static void main(String[] args) {
        System.out.println(i);
    }
}

class FinalStaticDemo3 {
    final int i;

    {
        i = 10;
    }

    public static void main(String[] args) {
        FinalStaticDemo3 demo3 = new FinalStaticDemo3();
        System.out.println(demo3.i);
    }
}


class FinalStaticDemo4 {
    final int i;

    public FinalStaticDemo4() {
        i = 10;
    }

    public static void main(String[] args) {
        FinalStaticDemo3 demo3 = new FinalStaticDemo3();
        System.out.println(demo3.i);
    }
}

