package org.wuzl.test.algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 卡牌分组
 * 
 * 
 * 给定一副牌，每张牌上都写着一个整数。
 * 
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 * 
 * 每组都有 X 张牌。 组内所有的牌上都写着相同的整数。 仅当你可选的 X >= 2 时返回 true。
 * 
 * 链接：https://leetcode-cn.com/problems/x-of-a-kind-in-a-deck-of-cards
 * 
 * @author ziliang.wu
 *
 */
public class HasGroupsSizeX {
    public static void main(String[] args) {
        HasGroupsSizeX obj = new HasGroupsSizeX();

    }

    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            Integer count = map.get(num);
            if (count == null) {
                count = 0;
            }
            map.put(num, count + 1);
        }
        if (map.size() == 1) {
            return true;
        }
        Collection<Integer> colleciton = map.values();
        Iterator<Integer> ite = colleciton.iterator();
        int temp = ite.next();
        while (ite.hasNext()) {
            temp = getGcd(temp, ite.next());
            if(temp==1) {
                return false;
            }
        }
        return true;
    }

    public boolean hasGroupsSizeXV1(int[] deck) {
        if (deck == null || deck.length < 2) {
            return false;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : deck) {
            Integer count = map.get(num);
            if (count == null) {
                count = 0;
            }
            map.put(num, count + 1);
        }
        if (map.size() == 1) {
            return true;
        }
        List<Integer> list = new ArrayList(map.values());
        Collections.sort(list);
        boolean result = true;
        int min = list.get(0);
        if (min == 1) {
            return false;
        }
        for (; min >= 2; min--) {
            result = true;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) % min != 0) {
                    result = false;
                    break;
                }
            }
            if (result) {
                return true;
            }
        }

        return false;
    }

    private int getGcd(int i, int j) {
        if (i < j) {
            int temp = j;
            j = i;
            i = temp;
        }
        int gcd = i % j;
        if (gcd == 0) {
            return j;
        }
        return getGcd(j, gcd);
    }
}
