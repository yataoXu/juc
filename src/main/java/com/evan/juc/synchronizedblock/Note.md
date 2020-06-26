### 同步代码块
同步代码块（synchronized block）用来标记方法或者代码块是同步的。Java同步块用来避免竞争

### 关键字 ： synchronized
Java中的同步块用synchronized标记。同步块在Java中是同步在某个对象上。所有同步在一个对象上的同步块在同时只能被一个线程进入并执行操作。所有其他等待进入该同步块的线程将被阻塞，直到执行该同步块中的线程退出
 


#### Java的同步机制是通过对 对象（资源）加锁实现的，举个例子：
现在有一个对象A，线程T1和T2都会修改对象A的值。
- 首先假设线程T1比线程T2更早运行到修改对象A的代码。
- 此时线程T1修改对象A的流程应该是这样的：线程T1从主存中获取对象A，并在自己缓存中存下对象A的副本，然后修改自己缓存中的对象A的副本。修改完毕后将自己缓存中的对象A的副本赋值给主存中的对象A，实现对象A的修改。
- 但是在线程T1执行修改对象A时，线程T2可能也跑到了修改对象A值的代码，而此时线程T1还在修改自己缓存中的对象A的副本，还没有将副本更新到主存中的对象A，但是线程T2又不知道，所以直接就从主存去取对象A了，这样就出现了竞态条件。
- 直观点，如果A=1，T1&T2都是将A+1，那么我们期望执行完T1&T2后A=3，但是上面栗子中最后A=2，因此我们为了防止这种竞态条件的出现，就需要给对象A加锁。
在上面的例子中当线程T1从主存获取对象A时，对象A就会加锁，这时如果线程T2要从主存中取对象A时就会被阻塞，直到线程T1完成对主存中对象A的修改，这时锁会被打开，线程T2才可以调用对象A。


### 有四种不同的同步块：
- 实例方法
- 静态方法
- 实例方法中的同步块
- 静态方法中的同步块



##### synchronized 修饰实例方法
```java
package com.evan.juc.synchronizedblock;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 修饰实例方法
 * 输出是混合交错的，即只会对对象加锁。
 */
public class InstanceMethodSynchronizedLock {
    public synchronized void add1() {
        for (int i = 0; i < 100; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public void add2() {
        for (int i = 100; i < 200; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class Client {
    public static void main(String[] args) {
        final InstanceMethodSynchronizedLock Test = new InstanceMethodSynchronizedLock();

        Thread thread1 = new Thread(() -> {
            Test.add1();
        }, "synchronized lock of instance method");

        Thread thread2 = new Thread(() -> {
            Test.add2();

        }, " instance method ");

        thread1.start();
        thread2.start();

    }
}

```
输出结果：输出是混合交错的，即只会对对象加锁。
```
 instance method :196
synchronized lock of instance method:96
 instance method :197
synchronized lock of instance method:97
 instance method :198
synchronized lock of instance method:98
 instance method :199
synchronized lock of instance method:99
```



```java
package com.evan.juc.synchronizedblock;

/**
 * synchronized 修饰实例方法
 * 输出是混合交错的，即只会对对象加锁。
 */
public class InstanceMethodSynchronizedLock01 {
    public synchronized void add1() {
        for (int i = 0; i < 100; i++) {

            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public synchronized void add2() {
        for (int i = 100; i < 200; i++) {

            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }
}

class Client1 {
    public static void main(String[] args) {
        final InstanceMethodSynchronizedLock01 test = new InstanceMethodSynchronizedLock01();

        Thread thread1 = new Thread(() -> {
            test.add1();
        }, "synchronized lock of instance method 1");

        Thread thread2 = new Thread(() -> {
            test.add2();

        }, "synchronized lock of instance method 2 ");

        thread1.start();
        thread2.start();

    }
}

```
检查该方法的对象（拥有者）是否已经加锁，如果加锁了，该线程就会阻塞，等待该对象解锁
```
synchronized lock of instance method 1:94
synchronized lock of instance method 1:95
synchronized lock of instance method 1:96
synchronized lock of instance method 1:97
synchronized lock of instance method 1:98
synchronized lock of instance method 1:99
synchronized lock of instance method 2 :100
synchronized lock of instance method 2 :101
synchronized lock of instance method 2 :102
synchronized lock of instance method 2 :103
synchronized lock of instance method 2 :104
synchronized lock of instance method 2 :105
synchronized lock of instance method 2 :106
```


任何一个线程在调用函数时将会判断这个函数是否是同步函数（即加了synchronized关键字），如果是，则会去检查该方法的对象（拥有者）是否已经加锁，如果加锁了，该线程就会阻塞，等待该对象解锁；如果是同步函数且对象没有加锁，则会继续运行，并给对象加锁；如果不是同步函数的话，就不需要去检查对象是否加锁，直接可以继续运行。

##### synchronized 修饰静态方法


```java
package com.evan.juc.synchronizedblock;

import java.util.concurrent.TimeUnit;


/**
 * 静态方法同步是同步在静态方法的类对象（不是类的实例，是指保存类信息的对象）上的，事实上，Java虚拟机中一个类只能对应一个类对象，
 * 所以只允许一个线程执行同一个类的静态同步方法。
 *
 * 其实和实例方法同步是类似的，只不过实例方法同步是在类实例上加锁，静态方法同步是在类对象上加锁。
 *
 * @author Evan
 */
public class StaticInstanceMethodSynchronizedLock {
    public static synchronized void addJ1(){
        for(int i=400 ;i<500;i++){

            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
    public static  void addJ2(){
        for(int i=500 ;i<600;i++){
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }
}

class StaticInstanceMethodSynchronizedLockClient{
    public static void main(String[] args) {

        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ1();},"static instance synchronized method 1").start();
        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ2();},"static instance method 1").start();
        new Thread(()->{StaticInstanceMethodSynchronizedLock.addJ1();},"static instance synchronized method 2").start();
    }
}

```

其实和实例方法同步是类似的，只不过实例方法同步是在类实例上加锁，静态方法同步是在类对象上加锁。

##### 实例方法中的同步块

```java
package com.evan.juc.synchronizedblock;

/**
 * synchronized 修饰实例方法
 * 输出是混合交错的，即只会对对象加锁。
 *
 * @author Evan
 */
public class InnerSynchronizedLock {
    public void add1() {
        synchronized (this) {
            for (int i = 0; i < 100; i++) {

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }

    public void add2() {
        synchronized (InnerSynchronizedLock.class) {
            for (int i = 100; i < 200; i++) {

                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
        }
    }
}

class InnerClient {
    public static void main(String[] args) {
        final InnerSynchronizedLock test = new InnerSynchronizedLock();

        Thread thread1 = new Thread(() -> {
            test.add1();
        }, "synchronized lock of inner method 1");

        Thread thread2 = new Thread(() -> {
            test.add2();

        }, "synchronized lock of inner method 2 ");

        thread1.start();
        thread2.start();

    }
}

```

示例使用Java同步块构造器来标记一块代码是同步的。该代码在执行时和同步方法一样。

注意Java同步块构造器用括号将对象括起来。在上例中，使用了“this”，即为调用add方法的实例本身。在同步构造器中用括号括起来的对象叫做监视器对象。上述代码使用监视器对象同步，同步实例方法使用调用方法本身的实例作为监视器对象。

一次只有一个线程能够在同步于同一个监视器对象的Java方法内执行。

下面两个例子都同步他们所调用的实例对象上，因此他们在同步的执行效果上是等效的。
```
public class MyClass {

   public synchronized void log1(String msg1, String msg2){
      log.writeln(msg1);
      log.writeln(msg2);
   }

   public void log2(String msg1, String msg2){
      synchronized(this){
         log.writeln(msg1);
         log.writeln(msg2);
      }
   }
 }
```
在上例中，每次只有一个线程能够在两个同步块中任意一个方法内执行。

如果第二个同步块不是同步在this实例对象上，那么两个方法可以被线程同时执行。


##### 静态方法中的同步块
和上面类似，下面是两个静态方法同步的例子。这些方法同步在该方法所属的类对象上。
```
public class MyClass {
    public static synchronized void log1(String msg1, String msg2){
       log.writeln(msg1);
       log.writeln(msg2);
    }

    public static void log2(String msg1, String msg2){
       synchronized(MyClass.class){
          log.writeln(msg1);
          log.writeln(msg2);
       }
    }
  }
```
这两个方法不允许同时被线程访问。

如果第二个同步块不是同步在MyClass.class这个对象上。那么这两个方法可以同时被线程访问。



