package com.evan.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName Lock8Demo
 * @Author Evan
 * @date 2020.02.04 19:51
 */

class Phone{
    public static synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() +"线程\t发送短信....");
    }

    public  synchronized void sendMail(){
        System.out.println(Thread.currentThread().getName() +"线程\t发送邮件....");
    }

    public void sayHello(){
        System.out.println(Thread.currentThread().getName() +"线程\thello 这是一个普通的方法");
    }
}
public class Lock8Demo {


    public static void main(String[] args) {
        // 线程操作资源类
        // 资源类为 Phone ，所以需要new Phone
        Phone phone = new Phone();
        Phone phone1 = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();

        new Thread(()->{phone1.sendMail();},"BBB").start();

    }
}
