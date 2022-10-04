package org.wuzl.test.obj;

import org.openjdk.jol.info.ClassLayout;

public class TestObjectJol {
    Object obj = new Object();
    static Object obj2 = new Object();

    public static void main(String[] args) {
        // Object obj = new Object();
        // ClassLayout classLayout = ClassLayout.parseInstance(obj);
        // out(classLayout.toPrintable());
        //
        // TestObjectJol obj2 = new TestObjectJol();
        // out(ClassLayout.parseInstance(obj2).toPrintable());
        // Integer num = 12;
        // out(ClassLayout.parseInstance(num).toPrintable());
        Parent parent = new Parent();
        out(ClassLayout.parseInstance(parent).toPrintable());
        out(ClassLayout.parseInstance(new Child()).toPrintable());
    }

    private static void out(String message) {
        System.out.println(message);
        System.out.println("------");
    }

}

class Parent {
    private long value=1;
}

class Child extends Parent {
    private int intValue=2;
}
