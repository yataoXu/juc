package com.evan.juc.the_art_of_java_concurrency_programming;

import java.lang.reflect.InvocationHandler;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @ClassName TestLinkedList
 * @Author Evanddd
 * @date 2020.06.02 22:57
 */
public class TestConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public TestConnectionPool(int initPoolSize) {
        if (initPoolSize > 0) {
            for (int i = 0; i < initPoolSize; i++) {
                pool.add(ConnectionDriver.createConnection());
            }
        }
    }

    // 回收链接到连接池
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            synchronized (pool) {
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }


    /**
     * 指定时间内从连接池中获取链接
     *
     * @param mills
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool) {
            //
            if (mills < 0) {
                while (pool.isEmpty()) {
                    pool.wait();
                }
                return pool.removeFirst();
            } else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining > 0) {
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if (!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}


class ConnectionDriver {
    static class ConnectionHandler implements InvocationHandler {

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("commit")) {
                TimeUnit.SECONDS.sleep(20);
            }
            return null;
        }
    }

    public static final Connection createConnection() {

        return (Connection) Proxy.newProxyInstance(ConnectionDriver.class.getClassLoader(),
                new Class<?>[]{
                        Connection.class
                }, new ConnectionHandler());
    }
}
