package com.evan.juc;


/**
 * @Description
 * @ClassName LockDemo
 * @Author Evan
 * @date 2020.02.03 17:39
 */


public class LockDemo1 {

    public static void main(String[] args) {

        // 资源类
        final Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "AAA").start();

        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"BBB").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        },"CCC").start();

        new Thread(()->{},"DDD").start();

    }
}
