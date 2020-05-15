package org.wuzl.test.algorithm;

public class MyPow {
    public static void main(String[] args) {
        MyPow obj = new MyPow();
        // System.out.println(obj.myPow(2.00000, 10));
        // System.out.println(obj.myPow(2.10000, 3));
        System.out.println(obj.myPow(4, 2));
        System.out.println(obj.myPow(4, -2));
        // System.out.println(obj.myPow(1.00000, -2147483648));
    }

    /**
     * 第二次写 没写出来
     * 
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        double res = 1.0;
        for (int i = n; i != 0; i /= 2) {
            if (i % 2 != 0) {
                res *= x;
            }
            x *= x;
        }
        return n < 0 ? 1 / res : res;
    }

    public double myPowV1(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            x = 1 / x;
            n = -n;
        }
        return pow(x, n);
    }

    private double pow(double x, int n) {
        if (n == 0) {// 极限的时候n会他妈是负数
            return 1;
        }
        if (n == 1) {
            return x;
        }
        if (n == 2) {
            return x * x;
        }
        double result = 1;
        double surplus = n % 2 == 0 ? 1 : x;
        n /= 2;
        double value = pow(x, n);
        result = value * value * surplus;
        return result;
    }
}
