package com.evan.jc.enumdemo;

import java.text.Normalizer;

/**
 * @Description
 * @ClassName FavouriteColor
 * @Author Evan
 * @date 2020.06.30 20:41
 */
public enum FavouriteColor {
    //枚举成员
    RED, GREEN(2), BLACK(3), BLUE, WHITE, BROWN;

    private int colorValue;


    // 静态常量
    public static final int cc = 2;

    //无参构造器
    FavouriteColor() {

    }

    //有参构造器
    FavouriteColor(int colorValue) {
        this.colorValue = colorValue;
    }

    //方法
    public void print() {
        System.out.println(cc);
    }


    public int getColorValue() {
        return colorValue;
    }

}

class Client {
    public static void main(String[] args) {
        System.out.println(FavouriteColor.BLACK.name());
        System.out.println(FavouriteColor.GREEN.name());

        System.out.println("===============");
        for (FavouriteColor favouriteColor : FavouriteColor.values()) {
            System.out.println(favouriteColor);
        }

        System.out.println("===============");
        System.out.println(FavouriteColor.GREEN.getColorValue());

        System.out.println("===============");
        System.out.println(FavouriteColor.valueOf("GREEN").getColorValue());


    }
}
