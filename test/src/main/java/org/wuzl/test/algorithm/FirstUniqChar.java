package org.wuzl.test.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *  第一个只出现一次的字符
 * @author ziliang.wu
 *
 */
public class FirstUniqChar {
    public char firstUniqChar(String s) {
        Map<Character,Integer> map=new HashMap<>();
        for(int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            Integer count=map.get(c);
            if(count==null) {
                count=0;
            }
            count++;
            map.put(c, count);
        }
        for(int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            Integer count=map.get(c);
            if(count==1) {
                return c;
            }
        }
        return ' ';
    }
}
