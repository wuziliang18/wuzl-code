package org.wuzl.test.classtest;

/**
 * 测试类加载顺序
 * 
 * @author ziliang.wu
 *
 */
public class TestClassLoaderOrder {
    public static void main(String[] args) {
        ChildClass obj = new ChildClass();
        obj.out2(null);
    }
}

class ParentClass {
    static {
        System.out.println("ParentClass class init");
    }
    private ParentRefClass refObj;//不会加载类
//    private ParentRefClass refObj=new ParentRefClass();//加载类

    ParentClass() {
        System.out.println("ParentClass object init");
    }

    public void out() {
        System.out.println("ParentClass out");
    }
    public void out2() {
        System.out.println(refObj);//不会加载类
        System.out.println("ParentClass out");
    }
    public void out2(ParentRefClass refObj) {
        System.out.println(refObj);//不会加载类
        System.out.println("ParentClass out");
    }
}

class ChildClass extends ParentClass {
    static {
        System.out.println("ChildClass class init");
    }

    ChildClass() {
        System.out.println("ChildClass object init");
    }

    public void out() {
        System.out.println("ChildClass out");
    }
}

class ParentRefClass {
    static {
        System.out.println("ParentRefClass class init");
    }
}
