package org.wuzl.test.algorithm;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * 
 * @author ziliang.wu
 *
 */
public class SumNums {
    public static void main(String[] args) {
        System.out.println(new SumNums().sumNums(3));
        System.out.println(new SumNums().sumNums(9));

    }

    public int sumNums(int n) {
        int sum = n;
        boolean check = n > 0 && (sum += sumNums(n - 1)) > 0;
        return sum;
    }
}
