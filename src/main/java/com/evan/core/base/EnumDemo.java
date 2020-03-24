package com.evan.core.base;

/**
 * @Description
 * @ClassName VehicleDemo
 * @Author Evan
 * @date 2020.03.23 22:32
 */


public class EnumDemo {
    public static void main(String[] args) {
        System.out.println(Vehicle.BIKE.name());
        System.out.println(Vehicle.BUS.name());
        System.out.println(Vehicle.CAR.name());
        System.out.println(Vehicle.TRUCK.name());
    }
}