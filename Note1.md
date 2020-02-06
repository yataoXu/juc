
##### synchronized 关键字的使用

#### 1. 一个资源类实例，两个同步方法，两个线程。先执行哪一个同步方法
```
package com.evan.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName Lock8Demo
 * @Author Evan
 * @date 2020.02.04 19:51
 */

class Phone{
    public synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() +"\t发送短信....");
    }

    public synchronized void sendMail(){
        System.out.println(Thread.currentThread().getName() +"\t发送邮件....");
    }
}
public class Lock8Demo {


    public static void main(String[] args) {
        // 线程操作资源类
        // 资源类为 Phone ，所以需要new Phone
        Phone phone = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{phone.sendMail();},"BBB").start();
    }
}

```
会发现先直线AAA线程，当AAA线程执行完成后，才会执行BBB线程


**结论**：只要在一个资源类里面，不管他有多少个同步方法，只要一个线程先访问了资源类的任何一个同步方法，这个线程锁的不是访问的那个方法，锁的是这个资源类实例，也就是说锁的是这个对象。



#### 2. 一个资源类实例，两个同步方法（资源类中的邮件方法暂停4s），两个线程。先执行哪一个同步方法


进一步进行验证，若sendSMS() 方法中sleep 4sec
```
    public synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() +"\t发送短信....");
    }
```

会发现最后代码是先执行完AAA线程，当AAA线程执行完成后，才会执行BBB线程

---
**结论**：一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronized方法了，其他的线程都只能等待。换句话说：某一个时刻内，只能有唯一一个线程去访问这些synchronized方法。

**锁的是当前对象this,被锁后，其他线程都不能进入到当前对象其他的synchronized方法。**

---


#### 3. 若资源类中有一个普通的方法，两个线程调用，先打印普通方法还是先打印同步方法。

sendSMS() sendMail() 方法都是使用synchronized修饰的方法，若加上一个普通的方法，如下
```
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName Lock8Demo
 * @Author Evan
 * @date 2020.02.04 19:51
 */

class Phone{
     public synchronized void sendSMS() throws InterruptedException {
         System.out.println(Thread.currentThread().getName() +"线程\t发送短信....");
     }
 
     public synchronized void sendMail(){
         System.out.println(Thread.currentThread().getName() +"线程\t发送邮件....");
     }

    public void sayHello(){
        System.out.println("hello 这是一个普通的方法");
    }
}
public class Lock8Demo {


    public static void main(String[] args) {
        // 线程操作资源类
        // 资源类为 Phone ，所以需要new Phone
        Phone phone = new Phone();

        new Thread(()->{
            try {
                phone.sendSMS();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"AAA").start();
        new Thread(()->{phone.sayHello();},"BBB").start();
    }
}

```
执行结果：
```
AAA线程	发送短信....
BBB线程	hello 这是一个普通的方法
```
若sendSMS() 方法中添加TimeUnit.SECONDS.sleep(4),如下：
```
    public synchronized void sendSMS() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println(Thread.currentThread().getName() +"线程\t发送短信....");
    }
```
输出结果为
```
BBB线程	hello 这是一个普通的方法
AAA线程	发送短信....
```
会发现BBB线程先执行。

**分析：** 加个普通方法后，发现和同步锁无关。因为两个线程根本就不存在竞争资源

#### 4. 若两部手机，两个同步方法， sendSMS() 方法中添加TimeUnit.SECONDS.sleep(4);
```
package com.evan.juc;

import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName Lock8Demo
 * @Author Evan
 * @date 2020.02.04 19:51
 */

class Phone{
    public  synchronized void sendSMS() throws InterruptedException {
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

```
结果为：
```
BBB线程	发送邮件....
AAA线程	发送短信....
```

**分析：** 为什么加锁：加锁的原因是因为多个线程操作同一个资源类，上述情况是两个资源类实例，也就是两个对象，不是同一把锁。所以两个线程根本就不存在竞争资源。

#### 5. 资源类中两个静态同步方法，两个实例，请问先打印邮件还是短信
```
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

    public static synchronized void sendMail(){
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

```

```
AAA线程	发送短信....
BBB线程	发送邮件....
```




- 同步方法中，同一个资源实例化（也就是同一个对象）共用一个对象锁。
- 静态同步方法中，同一个资源类共用一个类锁。





上面代码虽然存在两个资源类的实例，但是同步方法
属于静态同步方法。所以共用一把类锁。

synchronized 实现同步的基础：Java中的每一个对象都可以作为锁。具体表现为以下3中形式
- 对于普通同步方法，锁是当前实例对象
- 对于同步方法块，锁是synchronized括号里配置的对象。
- 对于静态同步方法，锁是当前类的class是对象。


**结论：** 对象锁和全局锁的区别：对象锁锁的是锁的是一个实例，全局锁锁的是当前类。


#### 6. 资源类中一个静态同步方法，1个普通的同步方法，一个资源类实例，请问先打印邮件还是短信

```
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

        new Thread(()->{phone.sendMail();},"BBB").start();

    }
}

```
结果为

```
BBB线程	发送邮件....
AAA线程	发送短信....
```
**分析：** 锁的对象不同，所以两个线程不存在资源竞争。

#### 7. 资源类中一个静态同步方法，1个普通的同步方法，两个资源类实例，请问先打印邮件还是短信

```
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

```

结果为
```
BBB线程	发送邮件....
AAA线程	发送短信....
```

**结论：**

当一个对象试图访问同步代码块时，它首先必须得到锁，退出或者抛出异常释放锁。

也就是说如果一个实例对象的非静态同步方法获取锁后，该实例对象其他的其他非静态同步方法必须等待获取锁的方法释放锁后才能获取锁。

可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁。所以无须等待该实例对象已经获取锁的非静态同步方法释放锁就可以获取到自己的锁。

所有的静态同步方法用的也是同一把锁————类对象本身，这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会存在竞态条件的，但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待已获取锁的同步方法释放锁后才能获取锁，而不管是同一个实例对象的静态同步方法之间，还是不同的实例对象的静态同步方法之间，只管他们当前类的class是对象

