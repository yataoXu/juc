package com.evan.core.reference;

import org.checkerframework.checker.units.qual.C;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description
 * @ClassName R4_PhantomReference
 * @Author Evan
 * @date 2020.06.14 16:33
 */
public class R4_PhantomReference {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue QUEUE = new ReferenceQueue();

    public static void main(String[] args) {

        PhantomReference<A> phantomReference = new PhantomReference<>(new A(), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());
            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends A> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("-----虚引用对象被JVm回收了--------" + poll);
                    return;
                }
            }
        }).start();
    }

}
