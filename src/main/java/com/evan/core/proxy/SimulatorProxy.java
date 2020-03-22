package com.evan.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName SimulatorProxy
 * @Author Evan
 * @date 2020.03.22 14:32
 */
public class SimulatorProxy implements Simulator {

    // 调用处理器对象的引用
    protected InvocationHandler handler;

    // 以调用处理器为参数的构造函数
    public SimulatorProxy(InvocationHandler handler){
        this.handler = handler;
    }

    // 实现接口方法 simulate
    @Override
    public short simulate(int arg1, long arg2, String arg3) throws Exception {
        // 第一步是获取 simulate 方法的 Method 对象
        Method method = null;

        method = Simulator.class.getMethod(
                "simulate",
                new Class[] {int.class, long.class, String.class} );

        // 第二步是调用 handler 的 invoke 方法分派转发方法调用
        Object r = null;
        try {
            r = handler.invoke(this,
                    method,
                    // 对于原始类型参数需要进行装箱操作
                    new Object[]{new Integer(arg1), new Long(arg2), arg3});
        }catch (Exception e){
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }


        // 第三步是返回结果（返回类型是原始类型则需要进行拆箱操作）
        return ((Short)r).shortValue();
    }
}
