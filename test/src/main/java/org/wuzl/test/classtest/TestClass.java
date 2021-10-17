package org.wuzl.test.classtest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        System.out.println(TestClass.class.getName());
        System.out.println(TestClass.class.getCanonicalName());
        System.out.println(String.class.getName().equals("java.lang.String"));
        List<String> list = Arrays.asList("1", "2");
        System.out.println(list.getClass());
    }
}
