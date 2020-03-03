package org.wuzl.test.algorithm;

/**
 * n的阶乘
 * 
 * @author ziliang.wu
 *
 */
public class NFactorial {
    public static void main(String[] args) {
        System.out.println(factorial(0));
        System.out.println(factorial(1));
        System.out.println(factorial(5));
        System.out.println(factorial(6));
        System.out.println(factorial(7));
    }

    public static int factorial(int num) {
        if (num <= 1) {
            return num;
        }
        return num * factorial(num - 1);
    }
}
