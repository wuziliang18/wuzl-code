package org.wuzl.test.algorithm;

public class MaxMoney {
    public static void main(String[] args) {
        MaxMoney obj = new MaxMoney();
        System.out.println(obj.getMaxMoney(new int[] { 7, 1, 5, 3, 6, 4 }));
        System.out.println(obj.getMaxMoney(new int[] { 7, 6, 4, 3, 1 }));

    }

    public int getMaxMoney(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        int temp = 0;
        int max = 0;
        for(int i=1;i<array.length;i++) {
            temp+=(array[i]-array[i-1]);
            if(temp>0) {
                max=Math.max(max, temp);
            }else {
                temp=0;
            }
        }
        return max;
    }
}
