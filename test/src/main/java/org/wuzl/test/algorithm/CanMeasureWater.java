package org.wuzl.test.algorithm;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * 水壶问题
 * 
 * @author ziliang.wu
 *
 */
public class CanMeasureWater {
    public static void main(String[] args) {
        CanMeasureWater obj = new CanMeasureWater();
        System.out.println(obj.canMeasureWater(2, 6, 5));
        System.out.println(obj.canMeasureWater(2, 6, 5));
        System.out.println(obj.canMeasureWater(6, 9, 1));
        System.out.println(obj.canMeasureWater(11, 13, 0));

    }

    public boolean canMeasureWater(int x, int y, int z) {
        if (z > x + y) {
            return false;
        }

        if (z == 0) {
            return true;
        }
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        queue.add(new Pair<>(0, 0));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> entity = queue.poll();
            int cX = entity.getKey();
            int cY = entity.getValue();
            if (cX == z || cY == z || cX + cY == z) {
                return true;
            }
            if (cX == 0) {
                addAndCheck(x, cY, queue, visited);
            }
            if (cY == 0) {
                addAndCheck(cX, y, queue, visited);
            }
            addAndCheck(cX, 0, queue, visited);
            addAndCheck(0, cY, queue, visited);
            int min = Math.min(cX, y - cY);
            addAndCheck(cX - min, cY + min, queue, visited);
            min = Math.min(cY, x - cX);
            addAndCheck(cX + min, cY - min, queue, visited);
        }
        return false;
    }

    private void addAndCheck(int x, int y, Queue<Pair<Integer, Integer>> queue, Set<Pair<Integer, Integer>> visited) {
        Pair<Integer, Integer> entity = new Pair<>(x, y);
        if (!visited.contains(entity)) {
            visited.add(entity);
            queue.add(entity);
        }
    }

    static class Pair<K, V> {
        K key;
        V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        private boolean eq(Object o1, Object o2) {
            return o1 == null ? o2 == null : o1.equals(o2);
        }

        public boolean equals(Object o) {
            if (!(o instanceof Pair))
                return false;
            Pair<?, ?> p = (Pair<?, ?>) o;

            return eq(key, p.getKey()) && eq(value, p.getValue());
        }

        public int hashCode() {
            return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
        }

        @Override
        public String toString() {
            return "Pair [key=" + key + ", value=" + value + "]";
        }

    }
}
