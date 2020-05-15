package com.evan.juc.base.synchronizedDemo;

import java.util.concurrent.TimeUnit;

class Bank {
    private int money;

    public int addMoney(int num) {

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        money += num;
        System.out.println("sum= :" + money);
        return money;
    }
}

class Customer implements Runnable {

    private Bank bank = new Bank();
    private String name;

    public Customer(String name) {
        this.name = name;
    }




    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            bank.addMoney(1);
        }
    }
}

public class BandDemo {

    public static void main(String[] args) {
        Customer customer = new Customer("evan");
        Customer john = new Customer("john");
        Thread t1 = new Thread(customer);
        Thread t2 = new Thread(customer);
        Thread t3 = new Thread(john);
        Thread t4 = new Thread(john);
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}