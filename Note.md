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
                map.put(UUID.randomUUID().toString().substring(0,8),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }
    }
    
}
```

