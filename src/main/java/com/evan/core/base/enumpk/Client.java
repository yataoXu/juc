package com.evan.core.base.enumpk;

import java.util.Arrays;

/**
 * @Description
 * @ClassName Client
 * @Author Evan
 * @date 2020.06.12 01:39
 */
public class Client {
    public static void main(String[] args) {
        Frutit.APPLE.printFruitInfo();
        System.out.println("-----------------");
        Arrays.stream(Frutit.values()).forEach(System.out::println);
        System.out.println("-----------------");
        Arrays.stream(FavouriteColor.values()).forEach(System.out::println);
        System.out.println("-----------------");
        Food food = Food.Coffee.ESPERSSO;//ESPERSSO不仅是coffee,也属于大类Food，达到分类的效果
        System.out.println(food);

    }
}
