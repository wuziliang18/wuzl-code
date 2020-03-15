package org.wuzl.test.algorithm;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m] 。请问 k[0]*k[1]*...*k[m]
 * 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @author ziliang.wu
 *
 */
public class CuttingRope {
    public static void main(String[] args) {
        CuttingRope rope = new CuttingRope();
        // System.out.println(rope.cuttingRope(2));
        // System.out.println(rope.cuttingRope(3));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(rope.cuttingRopeV1(59));

        System.out.println(rope.cuttingRope(59));
        System.out.println(rope.cuttingRopeV1(120));

        System.out.println(rope.cuttingRope(120));
        // System.out.println(rope.cuttingRope(8));

        // System.out.println(rope.cuttingRope(10));
    }

    /**
     * 求出几个3+(0,2)的2
     * 
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if (n <= 3) {// 3以下拆分的积会比和小
            return n - 1;
        }
        int m = n / 3;
        int surplus = n % 3;
        if (surplus == 1) {
            return (int) (Math.pow(3, m - 1) * 4);// 4实际是两个2
        }
        if (surplus == 2) {
            return (int) (Math.pow(3, m) * 2);// 一个2
        }
        return (int) Math.pow(3, m);
    }

    public int cuttingRopeV1(int n) {
        if (n <= 1) {
            return n;
        }
        int max = 1;
        for (int i = 2; i <= n - 1; i++) {
            int temp = getMax(n, i);
            if (temp >= max) {
                max = temp;
            } else {
                return max;
            }
        }
        return max;
    }

    /**
     * 可以按3拆分最大
     * 
     * @param n
     * @param m
     * @return
     */
    private int getMax(int n, int m) {
        int per = n / m;
        int surplus = n % m;
        int result = 1;
        for (int i = 0; i < m; i++) {
            int temp = result * (per + (--surplus >= 0 ? 1 : 0));
            if (temp < result) {
                return Integer.MAX_VALUE;
            }
            result = temp;

        }
        return result;
    }
}
