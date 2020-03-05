package org.wuzl.test.reference;

/**
 * 会空指针
 * 
 * @author ziliang.wu
 *
 */
public class TestReference {
    public static int foo() {
        return new A().val();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            try {
                foo();
            } catch (NullPointerException npe) {// JIT优化导致
                System.out.println("NPE happened at " + i + "th iteration");
                npe.printStackTrace(System.out);
                return;
            }
        }
    }
}

class A {
    private B b = new B();

    public void finalize() {
        this.b.clearC();
    }

    public int val() {
        return this.b.val();
    }
}

class B {
    private C c = new C();

    void clearC() {
        this.c = null;
    }

    public int val() {
        System.gc();
        System.runFinalization();
        return this.c.val();
    }
}

class C {
    public int val() {
        return 42;
    }
}
