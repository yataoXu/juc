//package com.evan.juc.cas;
//
//class Counter {
//    private int count;
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    public void addCount() {
//        count++;
//    }
//
//    public void decCount() {
//        count--;
//    }
//}
//
//class Consumer extends Thread {
//    Counter counter;
//
//    public Consumer(Counter counter) {
//        this.counter = counter;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < TestClient.LOOP; i++) {
//            counter.decCount();
//        }
//    }
//}
//
//class Producer extends Thread {
//    Counter counter;
//
//    public Producer(Counter counter) {
//        this.counter = counter;
//    }
//
//    @Override
//    public void run() {
//        for (int i = 0; i < TestClient.LOOP; i++) {
//            counter.addCount();
//        }
//    }
//}
//
//
///**
// * @author Evan
// */
//public class TestClient {
//
//}
//
