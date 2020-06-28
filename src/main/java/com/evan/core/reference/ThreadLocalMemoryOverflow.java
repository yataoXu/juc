package com.evan.core.reference;

import java.util.ArrayList;
import java.util.List;

public class ThreadLocalMemoryOverflow {

    // Thread local variable containing each thread's ID
    public ThreadLocal<List<Object>> threadId = new ThreadLocal<List<Object>>() {
        @Override
        protected List<Object> initialValue() {
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < 10000; i++) {
                list.add(String.valueOf(i));
            }
            return list;
        }
    };

    // Returns the current thread's unique ID, assigning it if necessary
    public List<Object> get() {
        return threadId.get();
    }

    // remove currentid
    public void remove() {
        threadId.remove();
    }

    public static void main(String[] args) {

    }
}
