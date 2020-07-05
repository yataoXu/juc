
### 可变参数简介

在不确定参数的个数时，可以使用可变的参数列表。

#### 语法：
参数类型...（三个点）

例如：  void varArgMethod(int... a) 

注意： 每个方法最多只有一个可变参数，因为：可变参数必须是方法的最后一个参数

#### 可变参数的类型
可变参数可以设置为任意类型：引用类型，基本类型；当然也会进行类型检查的；

#### 参数的个数：

- 0个参数
- 1个参数： 如果是数组，那么就直接将这个数组作为参数传进方法里面，不再填充新的数组；
- 多个参数： 参数可以是数组，也可以是单个变量、常量；但是这时候会，将这些参数填充进新的数组里面，再将这个数组，传进方法里面


#### 可变参数的使用

可变参数完全可以当作一个数组来使用，或者说，本质上可变参数就是一个数组（下面详细介绍）。所以，数组拥有的方法、属性，可变参数一样拥有。
```
   public static void varArgMethod(int... a) {

        //和数组一样，拥有属性length
        int lenth = a.length;

        //索引遍历
        for(int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
        //forEach循环遍历
        for(int ele:a) {
            System.out.println(ele);
        }
    }
```
上面的例子中，可变参数的使用跟数组的使用是完全一样，也就是说，可变参数是可以等价成数组的

#### 可变参数的方法重载
可变参数列表的方法的重载不同于普通方法： 无法仅通过改变 可变参数的类型，来重载方法。

如：varArray(int... a)、varArray(Object... a)，这两个方法在调用时会出错，方法重载失败。


### 深入分析可变参数的原理

前面已经很详细地介绍了可变参数的各个方面。这一小节将深入去了解可变参数的实现原理，特别是为什么可变参数的使用与数组是一样的。

```
package com.evan.jc.varargs;

import java.util.Arrays;

/**
 * @Description
 * @ClassName VarargsDemo1
 * @Author Evan
 * @date 2020.06.30 16:44
 */
public class VarargsDemo1 {
    public static void main(String[] args) {
        varArgMethod(1,2,4,56,7,8,9);
    }

    public static void varArgMethod(int... a) {

        int lenth = a.length;
        System.out.println("length:" +lenth);


        int sum = Arrays.stream(a).sum();

        System.out.println(sum);
    }


}

```

用jad对上面例子的class文件进行反编译：

```
package com.evan.jc.varargs;

import java.util.Arrays;

public class VarargsDemo1 {
  public static void main(String[] paramArrayOfString) {
    varArgMethod(new int[] { 1, 2, 4, 56, 7, 8, 9 });
  }
  
  public static void varArgMethod(int... paramVarArgs) {
    int i = paramVarArgs.length;
    System.out.println("length:" + i);
    int j = Arrays.stream(paramVarArgs).sum();
    System.out.println(j);
  }
}

```

可变参数的使用和数组一样也就不出奇了，因为可变参数最后还是被编译器处理成了数组，可变参数就是数组。