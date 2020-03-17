package com.evan.juc.threadpool;

import lombok.Data;

@Data
public class PoolInfo {

    private int poolSize;
    private int queueSize;
    private int activeCount;
    private long completedTaskCount;
    private boolean allowsCoreThreadTimeOut;
    private int corePoolSize;
    private int maximumPoolSize;
    private int largestPoolSize;
    private  long keepAliveTime;

}
