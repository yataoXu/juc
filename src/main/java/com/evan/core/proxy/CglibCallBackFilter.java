package com.evan.core.proxy;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * @Description
 * @ClassName CglibCallBackFilter
 * @Author Evan
 * @date 2020.03.22 17:08
 */
class CallBackFilterTest {

    public void doOne() {
        System.out.println("====>1");
    }

    public void doTwo() {
        System.out.println("====>2");
    }
}

public class CglibCallBackFilter {

    static class MethodInterceptorImpl implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {


            doBefore();
            System.out.println(method);
            Object result = proxy.invokeSuper(obj, args);
            doAfter();
            return result;
        }


        public void doBefore() {
            System.out.println("before....");
        }

        public void doAfter() {
            System.out.println("after....");
        }
    }

    static class CallbackFilterImpl implements CallbackFilter {
        @Override
        public int accept(Method method) {
            return ("doTwo".equals(method.getName())) ? 1 : 0;
        }
    }


    public static void main(String[] args) {
        Callback[] callbacks = new Callback[]{new MethodInterceptorImpl(), NoOp.INSTANCE};

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CallBackFilterTest.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilterImpl());
        CallBackFilterTest callBackFilterTest = (CallBackFilterTest) enhancer.create();
        callBackFilterTest.doOne();
        callBackFilterTest.doTwo();
    }
}
