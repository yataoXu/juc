package com.evan.core.base.itype;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class TestType {
    Map<String, String> map;
    public static void main(String[] args) throws Exception {
        Field f = TestType.class.getDeclaredField("map");
        System.out.println(f.getGenericType());                               // java.util.Map<java.lang.String, java.lang.String>
        System.out.println(f.getGenericType() instanceof ParameterizedType);  // true
        ParameterizedType pType = (ParameterizedType) f.getGenericType();
        System.out.println(pType.getRawType());                               // interface java.util.Map
        for (Type type : pType.getActualTypeArguments()) {
            System.out.println(type);                                         // 打印两遍: class java.lang.String
        }
        System.out.println(pType.getOwnerType());                             // null
    }
}
