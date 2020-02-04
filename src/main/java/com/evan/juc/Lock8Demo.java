package com.evan.juc;

/**
 * @Description
 * @ClassName Lock8Demo
 * @Author Evan
 * @date 2020.02.04 19:51
 */

class Phone{
    public synchronized void sendSMS(){
        System.out.println("发送短信....");
    }

    public synchronized void sendMail(){
        System.out.println("发送邮件....");
    }
}
public class Lock8Demo {


    public static void main(String[] args) {
        // 线程操作资源类
        // 资源类为 Phone ，所以需要new Phone
        Phone phone = new Phone();

        new Thread(()->{phone.sendSMS();},"AAA").start();
        new Thread(()->{phone.sendMail();},"BBB").start();

    }

}
