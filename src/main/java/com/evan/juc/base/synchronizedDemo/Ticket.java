package com.evan.juc.base.synchronizedDemo;

/**
 * 买票问题
 * <p>
 * 资源类
 * 调用资源类的线程
 * 调用资源类的线程调用资源类的方法
 */
class Ticket implements Runnable {
    private int ticketNum = 100;

    @Override
    public void run() {
        while (true) {
            if (ticketNum > 0) {
                ticketNum--;
                System.out.println("剩余的票数: " + ticketNum);
            } else {
                break;
            }
        }
    }
}

class TicketClient {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(ticket).start();
        new Thread(ticket).start();

    }
}