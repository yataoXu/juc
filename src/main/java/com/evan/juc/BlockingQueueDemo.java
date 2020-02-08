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