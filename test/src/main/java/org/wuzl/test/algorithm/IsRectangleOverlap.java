package org.wuzl.test.algorithm;

/**
 * 矩形以列表 [x1, y1, x2, y2] 的形式表示，其中 (x1, y1) 为左下角的坐标，(x2, y2) 是右上角的坐标。
 * 
 * 如果相交的面积为正，则称两矩形重叠。需要明确的是，只在角或边接触的两个矩形不构成重叠。
 * 
 * 给出两个矩形，判断它们是否重叠并返回结果。 https://leetcode-cn.com/problems/rectangle-overlap/
 * 
 * @author ziliang.wu
 *
 */
public class IsRectangleOverlap {
    public static void main(String[] args) {
        IsRectangleOverlap obj = new IsRectangleOverlap();
        // System.out.println(obj.isRectangleOverlap(new int[] { 0, 0, 2, 2 }, new int[] { 0, 0, 2, 2 }));
        // System.out.println(obj.isRectangleOverlap(new int[] { 0, 0, 2, 2 }, new int[] { 1, 1, 3, 3 }));
        // System.out.println(obj.isRectangleOverlap(new int[] { 0, 0, 1, 1 }, new int[] { 1, 0, 2, 1 }));
        System.out.println(obj.isRectangleOverlap(new int[] { 7, 8, 13, 15 }, new int[] { 10, 8, 12, 20 }));
    }

    public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        return !(rec1[2] <= rec2[0] || rec1[3] <= rec2[1] || rec1[0] >= rec2[2] || rec1[1] >= rec2[3]); 
//        return (Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
//                && Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]));
    }
}
