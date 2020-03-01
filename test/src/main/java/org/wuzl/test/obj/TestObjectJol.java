package org.wuzl.test.obj;

import org.openjdk.jol.info.ClassLayout;

public class TestObjectJol {
    Object obj = new Object();
    static Object obj2 = new Object();
    public static void main(String[] args) {
        Object obj = new Object();
        ClassLayout classLayout = ClassLayout.parseInstance(obj);
        out(classLayout.toPrintable());

        TestObjectJol obj2 = new TestObjectJol();
        out(ClassLayout.parseInstance(obj2).toPrintable());
    }

    private static void out(String message) {
        System.out.println(message);
        System.out.println("------");
    }
}
