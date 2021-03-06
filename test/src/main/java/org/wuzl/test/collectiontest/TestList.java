package org.wuzl.test.collectiontest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.google.common.base.Joiner;

public class TestList {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> iterable = list.iterator();
        while (iterable.hasNext()) {
            int id = iterable.next();
            if (id == 2) {
                iterable.remove();
                list.add(0, 2);
                break;
            }
        }
        System.out.println(list);
//        List<Integer> list = new ArrayList<>();
//        for (int i = 1; i <= 25; i++) {
//            list.add(i);
//        }
//        System.out.println(list.subList(0, 20));
//        System.out.println(list.subList(20, 25));
//        List<String> rows1 = new ArrayList<String>();
//        System.out.println(rows1.stream().collect(Collectors.joining(",")));
//        System.out.println("joiner: " + Joiner.on(",").join(rows1));
//        List<String> rows2 = new ArrayList<String>();
//        rows1.add("1");
//        rows1.add("2");
//        rows1.add("3");
//        rows1.add("4");
//        rows1.add("5");
//        rows1.add("1");
//        System.out.println(rows1.stream().collect(Collectors.joining(",")));
//        System.out.println("joiner: " + Joiner.on(",").join(rows1));
//        System.out.println(rows1.subList(0, 5));
//        System.out.println(rows1);
//        Collections.reverse(rows1);
//        System.out.println(rows1);
//        System.out.println(rows1.subList(0, 5));
//        List<String> rows3 = new ArrayList<String>(rows1);
//        rows2.add("2");
//        rows2.add("3");
//        rows2.add("4");
//        System.out.println(rows1.subList(1, 3));
//        System.out.println(rows1);
//        System.out.println(rows3);
//        System.out.println(rows1.subList(1, rows1.size()));
//        rows1.retainAll(rows2);// 交集[2, 3]
//        System.out.println(rows1);
//        List<Map> rows = new ArrayList<Map>();
//        Map dto = new HashMap();
//        dto.put("a1", "1");
//        rows.add(dto);
//        dto = new HashMap();
//        dto.put("a2", "2");
//        rows.add(dto);
//        dto = new HashMap();
//        dto.put("a3", "3");
//        rows.add(dto);
//        dto.put("a3", "3");
//        rows.add(dto);
//        System.out.println(rows);
//        // 在list中删除+add是不可以的 下边方法不可以
//        // Iterator<Map> ite=rows.iterator();
//        // while(ite.hasNext()){
//        // Map<String,String> map=ite.next();
//        // //找到包含a的key删除 值为3的新增b
//        // for(Entry<String, String> entry:map.entrySet()){
//        // if(entry.getKey().indexOf("a")>-1){
//        // ite.remove();
//        // }
//        // if(entry.getValue().indexOf("3")>-1){
//        // Map insert=new HashMap();
//        // insert.put("b3", "3");
//        // rows.add(insert);
//        // }
//        // }
//        // }
//        // System.out.println(rows);
//        // 只能用下边方法
//        Iterator<Map> ite = new ArrayList(rows).iterator();
//        while (ite.hasNext()) {
//            Map<String, String> map = ite.next();
//            // 找到包含a的key删除 值为3的新增b
//            for (Entry<String, String> entry : map.entrySet()) {
//                if (entry.getKey().indexOf("a") > -1) {
//                    ite.remove();
//                    rows.remove(map);
//                    System.out.println(rows);
//                }
//                if (entry.getValue().indexOf("3") > -1) {
//                    Map insert = new HashMap();
//                    insert.put("b3", "3");
//                    rows.add(insert);
//                }
//            }
//        }
//        System.out.println(rows);
    }
}
