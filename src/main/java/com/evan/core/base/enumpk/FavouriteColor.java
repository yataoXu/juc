package com.evan.core.base.enumpk;


public enum FavouriteColor {

    //枚举成员
    RED, GREEN(2), BLACK(3), BLUE, WHITE, BROWN;// 必须要有分号

    // 非枚举类型的成员
    private int colorValue;

    public int aa;

    // 静态常量也可以
    public static final int cc = 2;

    //无参构造器
    private FavouriteColor() {

    }

    //有参构造器
    FavouriteColor(int colorValue) {
        this.colorValue = colorValue;
    }

    //方法
    public void print() {
        System.out.println(cc);
    }
}