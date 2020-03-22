package org.wuzl.test.algorithm;

public class MyPow {
    public static void main(String[] args) {
        MyPow obj = new MyPow();
        // System.out.println(obj.myPow(2.00000, 10));
        // System.out.println(obj.myPow(2.10000, 3));
        System.out.println(obj.myPow(1.00000, -2147483648));
    }

    public double myPow(double x, int n) {
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
        if (n == 0) {//极限的时候n会他妈是负数
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
