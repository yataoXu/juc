package com.evan.jvm.oom;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @ClassName KeylessEntry
 * @Author Evan
 * @date 2020.03.21 18:25
 */
public class KeylessEntry {
    static class Key {
        Integer id;

        Key(Integer id) {
            this.id = id;
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }
    }

    public static void main(String[] args) {
        Map<Key, String> m = new HashMap<Key, String>();
        while (true) {
            for (int i = 0; i < 10000; i++) {
                if (!m.containsKey(new Key(i))) {
                    m.put(new Key(i), "Number:" + i);
                }
            }
        }
    }
}