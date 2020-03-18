#### JVM 中的双亲委派机制

 
JVM对字节码文件采用的是按需加载方法，什么时候使用这个类才会将它的字节码加载到内存生成Class对象呢？
我们用案例测试一下什么是双亲委派机制。

```
package com.evan.core.doubleParent;

public class String {
    static {
        System.out.println("自定义的String");
    }
}

package com.evan.core.doubleParent;

public class TestDoubleParent {

    public static void main(String[] args) {
        String str = new String();
    }

}

```

那么这个String加载的是JDK提供的String还是我自定义的String呢？
答案就是加载的是JDK提供的String，

这个用的原理就是双亲委派机制。




#### 什么是双亲委派机制

工作原理：
1. 如果一个类加载器收到了类加载请求，它并不会自己先去加载，而是把这个请求委托给父类的加载器去执行；
2. 如果父类加载器还存在其父类加载器，则进一步向上委托，依次递归,请求最终将到达项层的启动类加载器；
3. 如果父类加载器可以完成类加载任务，就成功返回，倘若父类加载器无法完成此加载任务，子加载器才会尝试自己去加载，这就是双亲委派机制。

![](../../../../../../../images/微信图片_20200318102152.png)

String类如果是自己定义的话,那么自定义类的加载器是系统类加载器，那么就一直向上委托,一直到最上面引导类加载器，而引导类加载器正好可以加载核心类库中的String，一看你正好要加载java.lang下的，这事就贵扩展类加载器管啊,所以就把JDK中的String加载了！

所以比如你要加载自定义的Student，那么其实也是一层一层向上委派的，但是人家上面都不管,就只能用系统加载器了！

#### 为什么要采用双亲委派机制

因为如果随意的就去执行我自己写的String了，那么你说你的一个很完美的项目，我要是恶意攻击你给你发送一个String类，那么你的项目岂不是直接就崩溃了？这也是一种保护啊！

1. 避免核心API被篡改
2. 避免类的重复加载

大家考虑下下面这个代码为什么出错：
```
public class String {
    public static void main(String[] args) {
        System.out.println("xixi");
    }
}
```

因为什么？因为main方法在String中，要去加载String，但是由扩展类加载器加载的时候找到了核心类库中的String，但是核心类中根本就没有main方法，所以报错咯。

所以你不要去定义跟核心API一样的包(java.lang.Student)，一样的类(java.lang.String) 都会报错的。