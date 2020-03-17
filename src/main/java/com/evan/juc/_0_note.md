#### JUC 是什么？
java.util.concurrent 在并发编程中使用的工具类

#### 进程与线程

**进程**：进程是一个具有一定独立功能的程序关于某个数据集合的一次运行活动。它是操作系统动态执行的基本单元，也是基本的执行单元

**线程**：通常在一个进程中可以包含若干了线程，当然一个进程中至少有一个线程，不然没有存在的意义。线程可以利用继承所拥有的的资源，在引入线程的操作系统中，通常都是把进程作为分配资源的基本单位，而把线程作为独立运行和独立调度的基本单位，由于线程比进程更小，基本上不拥有系统资源，故对它的调度所付出的开销就小得多，能更高效的提高系统多个程序间并发执行的程度 


####  intface Lock 的使用


```java
package seven.one;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description
 * 多线程的核心：
 * 线程  操作  资源类
 * @ClassName LockDemo
 * @Author Evan
 * @date 2020.02.03 17:39
 */


// 资源类 = 类变量 + 操作类变量的方法
class Ticket {
    private int count = 30;
    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();
        try {
            if (count > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出的是\t" + (count--) + "\t还剩\t" + count);
            } else {
                System.out.println(Thread.currentThread().getName() + "\t没票了");
            }
        } finally {
            lock.unlock();
        }
    }
}

public class LockDemo {

    public static void main(String[] args) {

        final Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "AAA").start();


        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "BBB").start();

        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "CCCC").start();
    }
}

```

会发现 main函数中的线程使用的是匿名内部类的方式进行codding,会发现除了线程名不一样其他部分完全一样，
采用Lambda Express：
```java
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

    }
}

```

#### 函数式编程
java是面向对象编程，其实工作久了，你会发现java 也是面向接口编程。

1. 接口可不可以new
```java
public class LambdaExpressDemo {

    public static void main(String[] args) {
        Foo foo = new Foo() {
            @Override
            public void sayHello() {
                System.out.println("这是 new 接口的操作");
            }
        };
        // 调用
        foo.sayHello();
    }
}

interface Foo {
    public void sayHello();
}

```
可以发现接口是可以进行new的，也就是接口的实例化。只是需要重写接口中的方法。匿名内部类实现接口中的方法
```java
public class LambdaExpressDemo {

    public static void main(String[] args) {
 // copy中括号，写死右箭头，落地大括号

        Foo foo1 = () -> {
            System.out.println("这是 使用 Lambda Express 实现接口中的方法");
        };
        foo1.sayHello();
    }
}
```
会发现 Lambda Express 主要解决的是之前匿名内部类代码冗余的问题

**Lambda Express 口诀：copy中括号，写死右箭头，落地大括号**


若接口中定义两个方法
```java
interface Foo {
    public void sayHello();
    public int add(int x, int y);

}
```

```
Multiple non-overriding abstract methods found in interface com.xx.xx.Foo
```
若使用Lambda Express ，接口中只能有一个方法

```java
interface Foo {
    public int add(int x, int y);
    }
```

```java
public class LambdaExpressDemo {

    public static void main(String[] args) {
       // copy中括号，写死右箭头，落地大括号
        Foo foo1 = (int x ,int y) -> {
            System.out.println("这是一个有入参，有返回值的方法实现");
            return x+y;
        };
        int sum = foo1.add(1, 2);
        System.out.println(sum);
    }
}
```
#### 函数式接口

Java 8 出现了一个新的注解
@FunctionalInterface 


```java
@FunctionalInterface
interface Foo {
    public void sayHello();
    public int add(int x, int y);

}
```
加上注解后会报错
```
Multiple non-overriding abstract methods found in interface com.xx.xx.Foo
```
函数式接口中只能有一个方法，若接口中只有一个方法，java 8 会饮食的加上@FunctionalInterface ，
若接口中有多个方法，则会被认为传统正规的接口。

若接口中需要有多个方法，且想使用函数式接口的特性呢，这个时候需要用到另一个新特性（接口中可以有一部分的方法实现）

```java
interface Foo {
    public int add(int x, int y);
    public default int mul(int x, int y) {
        return x * y;
    }
}

```

接口中定义静态方法
```java
@FunctionalInterface
interface Foo {
    public int add(int x, int y);
    public default int mul(int x, int y) {
        return x * y;
    }
    public static int div(int x,int y){
        return x/y;
    }
}
```

全部代码
```java
package seven.one;

/**
 * @Description
 * @ClassName LambdaExpressDemo
 * @Author Evan
 * @date 2020.02.03 23:04
 * 函数式编程
 */
public class LambdaExpressDemo {

    public static void main(String[] args) {

        // copy中括号，写死右箭头，落地大括号
        Foo foo1 = (int x, int y) -> {
            System.out.println("这是一个有入参，有返回值的方法实现");
            return x + y;
        };

        System.out.println(foo1.add(1, 2));
        System.out.println(foo1.mul(2, 3));
        System.out.println(Foo.div(2, 1));
    }
}

@FunctionalInterface
interface Foo {
    public int add(int x, int y);

    public default int mul(int x, int y) {
        return x * y;
    }

    public static int div(int x, int y) {
        return x / y;
    }
}

```

通过阅读Runnable 源代码可以法相Runnnable 接口为函数式接口，所以可以使用Lambda Express 进行操作。
```java
package java.lang;
@FunctionalInterface
public interface Runnable {
    public abstract void run();
}
```

### 多线程下的集合
 
#### interface List 
 
 
 ArrayList 部分源码
 ```
    /**
     * Default initial capacity.
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};
    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    // ArrayList 的构造方法
    
   
     /**
     * Constructs an empty list with an initial capacity of ten.
     */
    public ArrayList() {
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    
    public ArrayList(Collection<? extends E> c) {
        elementData = c.toArray();
        if ((size = elementData.length) != 0) {
            // c.toArray might (incorrectly) not return Object[] (see 6260652)
            if (elementData.getClass() != Object[].class)
                elementData = Arrays.copyOf(elementData, size, Object[].class);
        } else {
            // replace with empty array.
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }
    
```
会发现在List 接口实例化的操作中，new ArrayList 底层是new 一个空的数组引用，Java 8 在list.add()的时候才会list分配内存空间。

```
    public boolean add(E e) {
        ensureCapacityInternal(size + 1);  // Increments modCount!!
        elementData[size++] = e;
        return true;
    }
    
    ...
    
   private void ensureExplicitCapacity(int minCapacity) {
        modCount++;

        // overflow-conscious code
        if (minCapacity - elementData.length > 0)
            grow(minCapacity);
    }
    
   private void grow(int minCapacity) {
        // overflow-conscious code
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - MAX_ARRAY_SIZE > 0)
            newCapacity = hugeCapacity(minCapacity);
        // minCapacity is usually close to size, so this is a win:
        elementData = Arrays.copyOf(elementData, newCapacity);
    }
```
- 初始值为空
- 第一次add()操作 init length : 10
- 每次扩容：0.5

我们都知道ArrayList 是线程不安全的，而Vector是线程安全的。

验证代码：
```
public class NoSafeDemo {

    public static void main(String[] args) {

        List<String> list = new ArrayList();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName() + "\t" + UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
```



```
[null, 1	4c25fc01]
[null, 1	4c25fc01, 2	4c859224]
[null, 1	4c25fc01]

```

多运行几次会发现,会出现各种各样不确定性的输出结果。我们都知道ArrayList是线程不安全的，而Vector 是线程安全的，若把
        List<String> list = new ArrayList();改写成        List<String> list = new Vector<>();

结果为
```
[0	90f30d20]
[0	90f30d20, 2	40c62c24]
[0	90f30d20, 2	40c62c24, 1	9035ed1e]
```


若程序中的for 循环次增加到30

```java
package seven.one;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoSafeDemo {
    public static void main(String[] args) {
        List<String> list = new ArrayList();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName() + "\t" + UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
```
异常信息
```
java.util.ConcurrentModificationException
	at java.util.ArrayList$Itr.checkForComodification(ArrayList.java:909)
	at java.util.ArrayList$Itr.next(ArrayList.java:859)
	at java.util.AbstractCollection.toString(AbstractCollection.java:461)
	at java.lang.String.valueOf(String.java:2994)
	at java.io.PrintStream.println(PrintStream.java:821)
	at seven.one.NoSafeDemo.lambda$main$0(NoSafeDemo.java:21)
	at java.lang.Thread.run(Thread.java:748)
```

1. 故障信息：
java.util.ConcurrentModificationException

2. 导致原因
    多线程并发争抢资源类且没有加锁
3. 解决方法
    3.1 使用Vector 但是Vector使用的是synchronize ,在保持一致性的前提下牺牲并发性
    3.2 使用 Collections.synchronizedList(new ArrayList<>())
    3.3 使用 CopyOnWriteArrayList 
```java
package seven.one;

import java.util.*;

/**
 * @Description
 * @ClassName NoSafeDemo
 * @Author Evan
 * @date 2020.02.04 11:37
 */
public class NoSafeDemo {

    public static void main(String[] args) {

        List<String> list = Collections.synchronizedList(new ArrayList<>());

        List<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName() + "\t" + UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
```
4. 优化建议

---

#### CopyOnWriteArrayList 源码

阅读CopyOnWriteArrayList 可以发现CopyOnWriteArrayList、ArrayList、Vector 一样实现了List接口。
```

    private transient volatile Object[] array;

    public CopyOnWriteArrayList() {
            setArray(new Object[0]);
        }
        
     /**
      * Creates an empty list.
      */
     public CopyOnWriteArrayList() {
         setArray(new Object[0]);
     }
     
    public boolean add(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            // 写时复制的核心代码
            Object[] elements = getArray();
            int len = elements.length;
            Object[] newElements = Arrays.copyOf(elements, len + 1);
            newElements[len] = e;
            setArray(newElements);
            return true;
        } finally {
            lock.unlock();
        }
    }
    
    /**
     * Gets the array.  Non-private so as to also be accessible
     * from CopyOnWriteArraySet class.
     */
    final Object[] getArray() {
        return array;
    }
```
可以发现CopyOnWriteArrayList 实现和ArrayList的实现有相似的地方。实例化的时候空引用，再第一次添加的时候才会申请空间。
不一样的地方就是Cop也OnWriteArrayList的add方法使用了可重入锁。
---

#### HashSet 部分源码

```
    // Dummy value to associate with an Object in the backing Map
    private static final Object PRESENT = new Object();
    
    /**
     * Constructs a new, empty set; the backing <tt>HashMap</tt> instance has
     * default initial capacity (16) and load factor (0.75).
     */
    public HashSet() {
        map = new HashMap<>();
    }
    
    public boolean add(E e) {
        return map.put(e, PRESENT)==null;
    }

```
#### HashMap 部分源码

```
    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
        /**
         * Constructs an empty <tt>HashMap</tt> with the default initial capacity
         * (16) and the default load factor (0.75).
         */
        public HashMap() {
            this.loadFactor = DEFAULT_LOAD_FACTOR; // all other fields defaulted
        }
        
    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old
     * value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     *         <tt>null</tt> if there was no mapping for <tt>key</tt>.
     *         (A <tt>null</tt> return can also indicate that the map
     *         previously associated <tt>null</tt> with <tt>key</tt>.)
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }

```
通过阅读源码可以发现 hashMap、hashSet，在多线程并发的情况下是线程不安全的。在多线程的情况下操作也会报线程修改异常
```
java.util.ConcurrentModificationException
```
解决办法：
1. 使用 Collections.synchronizedMap(new HashMap())/ Collections.synchronizedSet(new HashSet())
2. 使用 java.util.concurrent 包下提供的解决方案：
```
package seven.one;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class NoSafeDemo {

    public static void main(String[] args) {

//        List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList();

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                list.add(Thread.currentThread().getName() + "\t" + UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

//        Set<String> set = new HashSet();
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 4; i++) {
            new Thread(()->{
                set.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
//        Map<String, String> map = new HashMap();
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 4; i++) {
            new Thread(()->{
               [link](https://note.youdao.com/)[link](https://note.youdao.com/)[link](https://note.youdao.com/)[link](https://note.youdao.com/)[link](https://note.youdao.com/)[link](https://note.youdao.com/)[link](https://note.youdao.com/) map.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    
}
```



---
#### JUC辅助类

- CountDownLatch
- CyclicBarrier
- Semaphore


##### CountDownLatch

###### 原理

- CountDownLatch 主要两种方法，当一个或多个想成调用await方法时，这些线程会阻塞。
- 其他线程调用counDown 方法会将计数器减1（调用counDown方法的线程不会阻塞）
- 当计数器的值变为0，因await方法阻塞的线程会被唤醒，继续被执行




###### 例子
班级内有7个同学上自习，班长锁门，班长只能在所有的同学都走后才能锁门。

若不用CountDownLatch
```
package com.evan.juc;

public class CountDowmLatchDemo {

    public static void main(String[] args) {

        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
            }).start();
        }

        System.out.println(Thread.currentThread().getName()+"\t 班长关门走人");
    }
}

```

结果
```
Thread-0	 离开教室
main	 班长关门走人
Thread-1	 离开教室
Thread-2	 离开教室
Thread-3	 离开教室
Thread-5	 离开教室
Thread-4	 离开教室
```
班长把5位同学锁在了教室。


```
package com.evan.juc;

import java.util.concurrent.CountDownLatch;


public class CountDowmLatchDemo {

    public static void main(String[] args) {


        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 0; i < 6; i++) {

            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"\t 班长关门走人");
    }
}



```


```
0	 离开教室
3	 离开教室
2	 离开教室
1	 离开教室
4	 离开教室
5	 离开教室
main	 班长关门走人

```


##### CyclicBarrier 



###### demo
```
package com.evan.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier  cycles = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 7  ; i++) {

            int temp = i ;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 收集到第"+ temp +"颗龙珠" );
                try {
                    cycles.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }

    }
}

```
结果

```
1	 收集到第1颗龙珠
2	 收集到第2颗龙珠
3	 收集到第3颗龙珠
4	 收集到第4颗龙珠
5	 收集到第5颗龙珠
6	 收集到第6颗龙珠
7	 收集到第7颗龙珠
召唤神龙
```


##### Semaphore


在信号量上有两种操作
- acquire(获取) 当一个线程调用acquire操作时，它要么通过成功获取信号量(信号量减1)，要么一直等下去，直到有线程释放信号量，或超时
- release(释放)：实际上会将信号量的值加1，然后唤醒等待的线程。


信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制

```
package com.evan.juc;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);  // 有三个资源
        Random random = new Random(99);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"\t抢到了车位");
                    TimeUnit.SECONDS.sleep(random.nextInt(10));
                    System.out.println(Thread.currentThread().getName() +"\t 离开了车位");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();

        }

    }
}

```



```
0	抢到了车位
1	抢到了车位
2	抢到了车位
1	 离开了车位
3	抢到了车位
0	 离开了车位
3	 离开了车位
4	抢到了车位
4	 离开了车位
5	抢到了车位
2	 离开了车位
5	 离开了车位
```


##### ReadWriteLock
ReadWriteLock是JDK5中提供的读写分离锁。读写分离锁可以有效地帮助减少锁竞争，以提高系统性能。用锁分离的机制来提升性能非常容易理解

读写锁访问约束如下：
1. 读-读不互斥：读读之间不阻塞
2. 读-写互斥：读阻塞写，写也会阻塞读
3. 写-写互斥：写写阻塞




```
package com.evan.juc;

import java.util.concurrent.locks.ReadWriteLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @Description
 * @ClassName ReadWriteLockDeomo
 * @Author Evan
 * @date 2020.02.08 14:24
 */
public class ReadWriteLockDeomo {

    public static void main(String[] args) {

        MyCache mycache = new MyCache();


        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                mycache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {

            final int temp = i;
            new Thread(() -> {
                mycache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {

        readWriteLock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
        try {
            TimeUnit.SECONDS.sleep(3);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入数据成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "\t 读取数据");
        readWriteLock.readLock().lock();
        try {
            TimeUnit.SECONDS.sleep(3);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取数据完成" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

```


```
1	 写入数据1
1	 读取数据
2	 读取数据
3	 读取数据
4	 读取数据
5	 读取数据
1	 写入数据成功
2	 写入数据2
2	 写入数据成功
3	 写入数据3
3	 写入数据成功
4	 写入数据4
4	 写入数据成功
5	 写入数据5
5	 写入数据成功
4	 读取数据完成4
1	 读取数据完成1
3	 读取数据完成3
5	 读取数据完成5
2	 读取数据完成2
```

#### BlockingQueue


![image](https://mmbiz.qpic.cn/mmbiz_png/vb4xFWPs1Fg6I9lRyUyfMrxQLO0Bh129NzZmQhT2dM79jgksQQ4eoDKnmrcn1BFKUD0HmkH8CEHyq7f6vOKlBQ/0?wx_fmt=png)

##### 什么是阻塞队列

阻塞队列是一个队列，在数据结构中起的作用如下图：
![image](https://mmbiz.qpic.cn/mmbiz_png/vb4xFWPs1Fg6I9lRyUyfMrxQLO0Bh129iakdzeozMBJ5eclmoXUK3Tu9P5JCicicdZMdnibAlU0zSTIzRTI10icOOWg/0?wx_fmt=png)

- 当队列是空的，从队列中**获取**元素的操作将会被阻塞
- 当队列是满的，从队列中**添加**元素的操作会被阻塞


试图从空的队列中获取元素的线程将会被阻塞，直到其他线程往空的队列插入新的元素

试图向已满的队列中添加新的元素的线程将会被阻塞，直到其他线程从队列中移除一个或多个元素或者被清空，使队列变得空闲起来并后续的新增

##### 阻塞队列的用处
在多线程领域：所谓阻塞，在某些情况下会挂起线程(即阻塞)，一旦条件满足，被挂起的线程又会被自动被唤起。

为什么需要BlockingQueue

好处是我们不需要关心什么时候需要阻塞线程，什么时候需要唤醒线程，因为这一切BlockingQueue都一手包办了

在concurrent包发布以前，在多线程环境下，我们每个程序员都必须去自己控制这些细节，尤其还要兼顾效率和线程安全，而这会给我们的程序带来不小的复杂度。


方法类型 | 抛出异常| 特殊值| 阻塞| 超时
---|---|---|---|---
插入 | add(e)| offer(e) | put(e) | offer(e,time,unit)
移除 | remove()| poll()| take()| poll(time,unit)
检查 | element()| peek()| 不可用 | 不可用|



类型 | 解释
---|---
抛出异常| 1. 当阻塞队列满时，再往队列里add插入元素会抛出 illegaStateException:Queue full2. 当阻塞队列空时，再往队列里remove移除元素会抛出NoSuchElementException
特殊值 | 插入方法，成功true失败false。移除方法，成功返回出队列的元素，队列里没有就返回null
一直阻塞| 当阻塞队列满时，生产者线程继续往队列里put元素，队列会一直会一直阻塞生产者线程直到put数据or 响应中断退出。当阻塞队列空时，消费者线程试图从队列里take元素，队列会一直阻塞消费者线程直到队列可用
超时退出| 当阻塞队列满时，队列会阻塞生产者线程一定时间，超过限时后生产者线程会退出


#### 种类分析
- **ArrayBlockingQueue**:由数组结构组成的有界阻塞队列
- **LinkingQueue**: 由链表结构组成的有界(但大小默认值为integer.MAX_VALUE)阻塞队列
- **PriorityBlockingQueue**:支持优先级排序的无界阻塞队列
- **DelayQueue**:使用优先级队列实现的延迟无界阻塞队列
- **SynchronousQueue**:不存储元素的阻塞队列，也即单个元素队列
- **LinkedTransferQueue**:由链表组成的无界阻塞队列
- **LinkedBlockingDeque**:由链表组成的双向阻塞队列



```
package com.evan.juc;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {


    public static void main(String[] args) {

        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));  //true
        System.out.println(blockingQueue.add("b"));  //true
        System.out.println(blockingQueue.add("c"));  //true
//        System.out.println(blockingQueue.add("d"));  //Exception in thread "main" java.lang.IllegalStateException: Queue full


        System.out.println(blockingQueue.element()); //a

        System.out.println(blockingQueue.remove()); // a
        System.out.println(blockingQueue.remove()); // b
        System.out.println(blockingQueue.remove());  // c
//        System.out.println(blockingQueue.remove());  // Exception in thread "main" java.util.NoSuchElementException

        BlockingQueue<String> blockingQueue1 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue1.offer("a")); //true
        System.out.println(blockingQueue1.offer("b")); //true
        System.out.println(blockingQueue1.offer("c")); //true
        System.out.println(blockingQueue1.offer("d")); //false

        System.out.println(blockingQueue1.poll()); // a
        System.out.println(blockingQueue1.poll()); // b
        System.out.println(blockingQueue1.poll()); // c
        System.out.println(blockingQueue1.poll()); // null

        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);

        try {
            blockingQueue2.put("a");
            blockingQueue2.put("b");
            blockingQueue2.put("c");
//            blockingQueue2.put("d");  //程序会一直被阻塞


            System.out.println("+++++++++++++++blockingQueue2 +++++++++++++++");
            System.out.println(blockingQueue2.take());
            System.out.println(blockingQueue2.take());
            System.out.println(blockingQueue2.take());
            System.out.println(blockingQueue2.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);
        try {
            System.out.println("+++++++++++++++blockingQueue3 +++++++++++++++");
            System.out.println(blockingQueue3.offer("a", 2, TimeUnit.SECONDS)); //true
            System.out.println(blockingQueue3.offer("b", 2, TimeUnit.SECONDS)); //true
            System.out.println(blockingQueue3.offer("c", 2, TimeUnit.SECONDS)); //true
            System.out.println(blockingQueue3.offer("d", 2, TimeUnit.SECONDS)); //false
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(blockingQueue3.poll());
        System.out.println(blockingQueue3.poll());
        System.out.println(blockingQueue3.poll());
        System.out.println(blockingQueue3.poll());

    }
}
```


#### 线程池

##### 为什么要用线程池

 我们有两种常见的创建线程的方法，一种是继承Thread类，一种是实现Runnable的接口，Thread类其实也是实现了Runnable接口。但是我们创建这两种线程在运行结束后都会被虚拟机销毁，如果线程数量多的话，频繁的创建和销毁线程会大大浪费时间和效率，更重要的是浪费内存，因为正常来说线程执行完毕后死亡，线程对象变成垃圾！那么有没有一种方法能让线程运行完后不立即销毁，而是让线程重复使用，继续执行其他的任务哪？我们使用线程池就能很好地解决这个问题。

线程池的优势：
线程池做的工作只要是控制运行的线程数量，处理过程中将任务放入队列，然后在线程创建后启动这些任务，如果线程的数量超过最大数量，超出数量的线程排队等待，等其他线程执行完毕，再从队列中取出任务来执行。

它的主要特点为：线程复用，控制最大并发数，管理线程

1. 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗
2. 提高响应速度，当任务到达时，任务可以不需要等待线程创建就立即执行
3. 提高线程的可管理性，线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。


#### 架构
 线程池的最上层接口是Executor，这个接口定义了一个核心方法execute(Runnabel command)，这个方法最后被ThreadPoolExecutor类实现，这个方法是用来传入任务的。而且ThreadPoolExecutor是线程池的核心类，此类的构造方法如下：


```
public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
            BlockingQueue<Runnable> workQueue);
 
public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
        BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory);

public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
        BlockingQueue<Runnable> workQueue,RejectedExecutionHandler handler);

public ThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,TimeUnit unit,
    BlockingQueue<Runnable> workQueue,ThreadFactory threadFactory,RejectedExecutionHandler handler);
```





- newFixedThreadPool(int): 执行长期任务性能好，创建一个线程池，一池中有N个固定的线程，有固定线程数的线程
- newSingleThreadExecutor():一个任务一个任务的执行，一池一线线程
- newCachedThreadPool():执行很多短期异步任务，线程池根据需要创建新线程，但在先前构建的线程可用时将重用他们，可扩容，遇强则强。

##### demo


```
package com.evan.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName SingleThreadDemo
 * @Author Evan
 * @date 2020.02.08 18:27
 */
public class SingleThreadDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一池中一个线程

        try {


            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 执行");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 执行完成");

                });
            }
        }finally {
            threadPool.shutdown();
        }
    }
}

```



```
package com.evan.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName FixedThreadDemo
 * @Author Evan
 * @date 2020.02.08 18:22
 */
public class FixedThreadDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);//一池中5个线程
        try {
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 执行");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 执行完成");

                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}

```


```
package com.evan.juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName CachedThreadDemo
 * @Author Evan
 * @date 2020.02.08 18:30
 */
public class CachedThreadDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();//一池N个线程
        try {
            for (int i = 0; i < 10; i++) {

                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 执行");
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 执行完成");

                });
            }
        } finally {
            threadPool.shutdown();
        }
    }
}

```
#### 线程池底层工作原理

###### newFixedThreadPool 源码：
```
 public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
```






###### newSingleThreadExecutor 源码：
```
    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>()));
    }
```

###### newCachedThreadPool 源码：
```
 public static ExecutorService newCachedThreadPool() {
        return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                      60L, TimeUnit.SECONDS,
                                      new SynchronousQueue<Runnable>());
    }

```

###### ThreadPoolExecutor 源码：
```
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
    }
    
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler) {
        if (corePoolSize < 0 ||
            maximumPoolSize <= 0 ||
            maximumPoolSize < corePoolSize ||
            keepAliveTime < 0)
            throw new IllegalArgumentException();
        if (workQueue == null || threadFactory == null || handler == null)
            throw new NullPointerException();
        this.acc = System.getSecurityManager() == null ?
                null :
                AccessController.getContext();
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.workQueue = workQueue;
        this.keepAliveTime = unit.toNanos(keepAliveTime);
        this.threadFactory = threadFactory;
        this.
        handler：= handler;
    }
```

1. corePoolSize：线程池中常驻核心线程数
2. maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值大于1
3. keepAliveTime：多余的空闲线程的存活时间当前池中线程数量超过corePoolsize时，当空闲时间达到keepAliveTim时，多余线程会被注销直到只剩下corePoolSize个线程为止
4. unit：keepAliveTime的单位
5. workQueue：任务队列，被提交但尚未被执行的任务

6. threadFactory：线程工厂，用来创建线程，一般有三种选择策略。
```
ArrayBlockingQueue;
LinkedBlockingQueue;
SynchronousQueue;
```
7. handler：拒绝处理策略，线程数量大于最大线程数就会采用拒绝处理策略，四种策略为
```
ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。 
ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。 
ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务 
```

Executor接口有一个子接口ExecutorService，ExecutorService的实现类为AbstracExecutorService，而ThreadPoolExcutor正是AbstrcExecutorService的子类。

ThreadPoolExecutor还有两个常用的方法shutdown和submit，两者都用来关闭线程池，但是后者有一个结果返回。


#### 线程池实现原理

线程池图：

![image](https://mmbiz.qpic.cn/mmbiz_png/vb4xFWPs1Fg6I9lRyUyfMrxQLO0Bh129Qq7a1eBglibVl5B4KyMNvPBKnNfm2LKicSQIUtvoawcatmBiaueeibXFkA/0?wx_fmt=png)

1. 线程池状态

线程池和线程一样拥有自己的状态，在ThreadPoolExecutor类中定义了一个volatile变量runState来表示线程池的状态，线程池有四种状态，分别为RUNNING、SHURDOWN、STOP、TERMINATED。

线程池创建后处于RUNNING状态。

调用shutdown后处于SHUTDOWN状态，线程池不能接受新的任务，会等待缓冲队列的任务完成。

调用shutdownNow后处于STOP状态，线程池不能接受新的任务，并尝试终止正在执行的任务。

当线程池处于SHUTDOWN或STOP状态，并且所有工作线程已经销毁，任务缓存队列已经清空或执行结束后，线程池被设置为TERMINATED状态。

2. 线程池任务的执行

当执行execute(Runnable command)方法后，传入了一个任务，我们看一下execute方法的实现原理。


```
public void execute(Runnable command) {
    if (command == null)
        throw new NullPointerException();
    if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
        if (runState == RUNNING && workQueue.offer(command)) {
            if (runState != RUNNING || poolSize == 0)
                ensureQueuedTaskHandled(command);
        }
        else if (!addIfUnderMaximumPoolSize(command))
            reject(command); 
    }
}
```


整个方法的执行过程是这样的，首先判断任务是否为空，空抛空指针异常，否则执行下一个判断，如果目前线程的数量小于核心线程池大小，就执行addIfUnderCorePollSize(command)方法，在核心线程池创建新的线程，并且执行这个任务。

我们看这个方法的具体实现：
```
private boolean addIfUnderCorePoolSize(Runnable firstTask) {
    Thread t = null;
    final ReentrantLock mainLock = this.mainLock;
    mainLock.lock();
    try {
        if (poolSize < corePoolSize && runState == RUNNING)
            t = addThread(firstTask);        //创建线程去执行firstTask任务  
        } finally {
        mainLock.unlock();
    }
    if (t == null)
        return false;
    t.start();
    return true;
}
```


这个方法首先是获取线程池的锁，参考别人的博客，说是和线程池状态有关，没有搞懂......，后面又进行一次判断，判断线程池线程数量和核心线程池的比较，前面execute()已经判断过，这里为什么还要进行判断哪？因为我们执行完Execute()中的判断后，可能有新的任务进来了，并且为这个任务在核心线程池创建了新的线程去执行，如果刚好这是核心线程池满了，那么就不能再加入新的县城到核心线程池了。这种可能性是存在的，因为你不知道cpu什么时间分配给谁，所以我们要加一个判断，至于线程池状态为什么也要判断，也是因为可能有其他线程执行了shutdown或者shutdownNow方法，导致线程池状态不是RUNNING，那么线程池就停止接收新的任务，也就不会创建新的线程去执行这个任务了。

t=addThread(firstTask)；这句代码至关重要，我们看方法的实现代码：
```
private Thread addThread(Runnable firstTask) {
    Worker w = new Worker(firstTask);
    Thread t = threadFactory.newThread(w);  //创建一个线程，执行任务   
    if (t != null) {
        w.thread = t;            //将创建的线程的引用赋值为w的成员变量       
        workers.add(w);          //将当前任务添加到任务集
        int nt = ++poolSize;     //当前线程数加1       
        if (nt > largestPoolSize)
            largestPoolSize = nt;
    }
    return t;
}
```
这个方法返回类型是Thread，所以我们可以新建一个线程并执行任务，之后将线程对象返回给外面的线程对象，然后执行t.start()，我们看到有一个Worker对象接收了任务，我们看Worker类的实现：
```
private final class Worker implements Runnable {
    private final ReentrantLock runLock = new ReentrantLock();
    private Runnable firstTask;
    volatile long completedTasks;
    Thread thread;
    Worker(Runnable firstTask) {
        this.firstTask = firstTask;
    }
    boolean isActive() {
        return runLock.isLocked();
    }
    void interruptIfIdle() {
        final ReentrantLock runLock = this.runLock;
        if (runLock.tryLock()) {
            try {
        if (thread != Thread.currentThread())
        thread.interrupt();
            } finally {
                runLock.unlock();
            }
        }
    }
    void interruptNow() {
        thread.interrupt();
    }
 
    private void runTask(Runnable task) {
        final ReentrantLock runLock = this.runLock;
        runLock.lock();
        try {
            if (runState < STOP &&
                Thread.interrupted() &&
                runState >= STOP)
            boolean ran = false;
            beforeExecute(thread, task);   //beforeExecute方法是ThreadPoolExecutor类的一个方法，没有具体实现，用户可以根据
            //自己需要重载这个方法和后面的afterExecute方法来进行一些统计信息，比如某个任务的执行时间等           
            try {
                task.run();
                ran = true;
                afterExecute(task, null);
                ++completedTasks;
            } catch (RuntimeException ex) {
                if (!ran)
                    afterExecute(task, ex);
                throw ex;
            }
        } finally {
            runLock.unlock();
        }
    }
 
    public void run() {
        try {
            Runnable task = firstTask;
            firstTask = null;
            while (task != null || (task = getTask()) != null) {
                runTask(task);
                task = null;
            }
        } finally {
            workerDone(this);   //当任务队列中没有任务时，进行清理工作       
        }
    }
}
```

这个类实现了Runnable接口，所以会有run()方法，我们看到run中执行的还是传入的任务，其实相当于调用传入任务对象的run方法，我们之所以费力气将任务对象加到Worker类中去执行，是因为这个线程执行之后会进入阻塞队列等待被执行，这个线程的生命并没有结束，这也正是我们使用线程池的最大原因。我们用一个Set集合存储Worker，这样不会有重复的任务被存储，firstTask被执行完后进入缓存队列，而这个新创建的线程就一直从缓存队列中拿到任务去执行。这个方法为getTask()，所以我们来看看线程如何从缓存队列拿到任务。

```
Runnable getTask() {
    for (;;) {
        try {
            int state = runState;
            if (state > SHUTDOWN)
                return null;
            Runnable r;
            if (state == SHUTDOWN)  // Help drain queue
                r = workQueue.poll();
            else if (poolSize > corePoolSize || allowCoreThreadTimeOut) //如果线程数大于核心池大小或者允许为核心池线程设置空闲时间，
                //则通过poll取任务，若等待一定的时间取不到任务，则返回null
                r = workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS);
            else
                r = workQueue.take();
            if (r != null)
                return r;
            if (workerCanExit()) {    //如果没取到任务，即r为null，则判断当前的worker是否可以退出
                if (runState >= SHUTDOWN) // Wake up others
                    interruptIdleWorkers();   //中断处于空闲状态的worker
                return null;
            }
            // Else retry
        } catch (InterruptedException ie) {
            // On interruption, re-check runState
        }
    }
}
```

我们看到如果核心线程池中创建的线程想要拿到缓存队列中的任务，先要判断线程池的状态，如果STOP或者TERMINATED，返回NULL，如果是RUNNING或者SHUTDOWN，则从缓存队列中拿到任务去执行。

这就是核心线程池执行任务的原理。

那么如果线程数量超过核心线程池大小哪？我们回到executor()方法，如果发生这种情况，处理方式是 
```
if (runState == RUNNING && workQueue.offer(command))
```
这段代码意思是，如果线程数量超过核心线程池大小，先进行线程池状态的判断，如果是RUNNING，则将新进来的线程加入缓存队列。如果失败，往往是因为缓存队列满了或者线程池状态不是RUNNING，就直接创建新的线程去执行任务，调用addIfUnderMaximumPoolSize(command)，就会新创建线程，但是这个县城不是核心线程池中的，是临时扩展的，要保证线程数最大不超过线程池大小 maximumPoolSize，如果超过执行 reject(command);操作，拒绝接受新的任务。

还有如果任务已经加入缓存队列成功还要继续进行判断
```
if (runState != RUNNING || poolSize == 0)
```
这是为了防止在将任务加入缓存队列的同时其他线程调用shutdown或者shutdownNow方法，所以采取了保护措施
```
ensureQueuedTaskHandled(command)
 ```

我们看addIfUnderMaximumPoolSize的实现方法：

```
private boolean addIfUnderMaximumPoolSize(Runnable firstTask) {
    Thread t = null;
    final ReentrantLock mainLock = this.mainLock;
    mainLock.lock();
    try {
        if (poolSize < maximumPoolSize && runState == RUNNING)
            t = addThread(firstTask);
    } finally {
        mainLock.unlock();
    }
    if (t == null)
        return false;
    t.start();
    return true;
}
```
这个方法和addIfUnderCorePoolSize基本一样，只是方法中判断条件改变了，这个方法是在缓冲队列满了并且线程池状态是在RUNNING状态下才会执行，里面的判断条件是线程池数量小于线程池最大容量，并且线程池状态是RUNNING。

 

我们进行总结：

- 如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
- 如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
- 如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
- 如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过keepAliveTime，线程也会被终止。

三.线程池使用示例

      

```
package cn.yqg.java;

public class Task implements Runnable{
      private int num;
      public Task(int num) {
          this.num=num;
      }
    @Override
    public void run() {
        System.out.println("正在执行任务  "+num);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+num+"执行完毕");
    }
}
```
```
package cn.yqg.java;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test4 {
    public static void main(String[] args) {
        ThreadPoolExecutor pool=new ThreadPoolExecutor(5,10,200, TimeUnit.MILLISECONDS,  new ArrayBlockingQueue<Runnable>(5));
        for(int i=0;i<15;i++) {
            Task task=new Task(i);
            pool.execute(task);
            System.out.println("线程池中线程数目："+pool.getPoolSize()+"，队列中等待执行的任务数目："+
                     pool.getQueue().size()+"，已执行玩别的任务数目："+pool.getCompletedTaskCount());
        }
        pool.shutdown();
    }
}
```
一种可能情况：

```
正在执行任务  0
线程池中线程数目：1，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
线程池中线程数目：2，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
线程池中线程数目：3，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
正在执行任务  1
正在执行任务  2
线程池中线程数目：4，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
正在执行任务  3
线程池中线程数目：5，队列中等待执行的任务数目：0，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：1，已执行玩别的任务数目：0
正在执行任务  4
线程池中线程数目：5，队列中等待执行的任务数目：2，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：3，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：4，已执行玩别的任务数目：0
线程池中线程数目：5，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
线程池中线程数目：6，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行任务  10
线程池中线程数目：7，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行任务  11
线程池中线程数目：8，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行任务  12
线程池中线程数目：9，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行任务  13
线程池中线程数目：10，队列中等待执行的任务数目：5，已执行玩别的任务数目：0
正在执行任务  14
线程2执行完毕
线程1执行完毕
线程4执行完毕
线程3执行完毕
正在执行任务  8
线程0执行完毕
正在执行任务  9
正在执行任务  7
正在执行任务  6
正在执行任务  5
线程12执行完毕
线程13执行完毕
线程11执行完毕
线程10执行完毕
线程14执行完毕
线程7执行完毕
线程9执行完毕
线程8执行完毕
线程5执行完毕
线程6执行完毕
```
从执行结果可以看出，当线程池中线程的数目大于5时，便将任务放入任务缓存队列里面，当任务缓存队列满了之后，便创建新的线程。如果上面程序中，将for循环中改成执行超过15个任务，就会抛出任务拒绝异常了。

不过并不提倡我们直接使用ThreadPoolExecutor，而是使用Executors类中提供的几个静态方法来创建线程池：
```
Executors.newCachedThreadPool();        //创建一个缓冲池，缓冲池容量大小为Integer.MAX_VALUE
Executors.newSingleThreadExecutor();   //创建容量为1的缓冲池
Executors.newFixedThreadPool(int);    //创建固定容量大小的缓冲池
```
```
public static ExecutorService newFixedThreadPool(int nThreads) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                  0L, TimeUnit.MILLISECONDS,
                                  new LinkedBlockingQueue<Runnable>());
}
public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}
public static ExecutorService newCachedThreadPool() {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                  60L, TimeUnit.SECONDS,
                                  new SynchronousQueue<Runnable>());
}
```
从它们的具体实现来看，它们实际上也是调用了ThreadPoolExecutor，只不过参数都已配置好了。

1. newFixedThreadPool创建的线程池corePoolSize和maximumPoolSize值是相等的，它使用的LinkedBlockingQueue；

2. newSingleThreadExecutor将corePoolSize和maximumPoolSize都设置为1，也使用的LinkedBlockingQueue；

3. newCachedThreadPool将corePoolSize设置为0，将maximumPoolSize设置为Integer.MAX_VALUE，使用的SynchronousQueue，也就是说来了任务就创建线程运行，当线程空闲超过60秒，就销毁线程。

　　实际中，如果Executors提供的三个静态方法能满足要求，就尽量使用它提供的三个方法，因为自己去手动配置ThreadPoolExecutor的参数有点麻烦，要根据实际任务的类型和数量来进行配置。

　　另外，如果ThreadPoolExecutor达不到要求，可以自己继承ThreadPoolExecutor类进行重写。
### 分之合并框架——ForkJoin框架

![image](https://mmbiz.qpic.cn/mmbiz_png/vb4xFWPs1Fg6I9lRyUyfMrxQLO0Bh129mJ5Jic5lDxZyRbgF6oLfujjU0mhaykb3lyIVl2ibxjRlEh3GMHAW5dTA/0?wx_fmt=png)

ForkJoin采用“工作窃取模式”，当有新的任务它可以将其拆分成更小的任务去执行，之后将结果进行合并，得到最终的结果。这种思想，类似于map/reduce的分而治之的思想处理任务。

ForJoin的简单实现
```
ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
ForkJoinTask<Long> task = new ForkJoinWork(0L,10000000000L);//参数为起始值与结束值
Long invoke = forkJoinPool.invoke(task);#执行ForkJoin任务中的compute()
```
ForJoinTask必须继承RecursiveTask（有返回值）或者 RecursiveAction （没有返回值）
```
public class ForkJoinWork extends RecursiveTask<Long> {
    private Long start;//起始值
    private Long end;//结束值
    public static final  Long critical = 100000L;//临界值
    public ForkJoinWork(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

@Override
protected Long compute() {
    //判断是否是拆分完毕
    Long lenth = end - start;
    if(lenth<=critical){
        //如果拆分完毕就相加
        Long sum = 0L;
        for (Long i = start;i<=end;i++){
            sum += i;
        }
        return sum;
    }else {
        //没有拆分完毕就开始拆分
        Long middle = (end + start)/2;//计算的两个值的中间值
        ForkJoinWork right = new ForkJoinWork(start,middle);
        right.fork();//拆分，并压入线程队列
        ForkJoinWork left = new ForkJoinWork(middle+1,end);
        left.fork();//拆分，并压入线程队列

        //合并
        return right.join() + left.join();
    }
}
}
```