package org.wuzl.test.concurrent;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 现在你总共有 n 门课需要选，记为 0 到 n-1。
 * 
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 * 
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * 
 * 
 * 链接：https://leetcode-cn.com/problems/course-schedule-ii
 * 
 * @author ziliang.wu
 *
 */
public class FndOrder {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, List<Integer>> map2 = new HashMap<>();
        for (int[] array : prerequisites) {
            map1.put(array[0], array[1]);
            List<Integer> list=map2.get(array[1]);
            if(list==null) {
                list=new LinkedList<>();
                map2.put(array[1], list);
            }
            list.add(array[0]);
        }
        if (map1.size() == numCourses) {
            return new int[0];
        }
        int count=0;
        while (!map2.isEmpty()) {
            Set<Integer> set = map2.keySet();
            Iterator<Integer> ite=set.iterator();
            while(ite.hasNext()) {
                int key=ite.next();
                List<Integer> values=map2.get(key);
                if(map1.containsKey(key)) {
                    continue;
                }
                map2.remove(key);
                result [count++]=key;
                
                break;
            }
        }
        return result;
    }
    private void  process(int[] result,int index,List<Integer> values,Map<Integer, List<Integer>> map2) {
        for(int value:values) {
            map2.remove(value);
            result [index++]=value;
        }
    }
}
