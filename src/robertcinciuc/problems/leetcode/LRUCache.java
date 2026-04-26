package robertcinciuc.problems.leetcode;

import java.util.LinkedHashMap;

public class LRUCache {

    private final int capacity;
    private final LinkedHashMap<Integer, Integer> linkedHashMap;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.linkedHashMap = new LinkedHashMap<>();
    }

    public int get(int key) {
        if (!linkedHashMap.containsKey(key)) {
            return -1;
        }
        Integer removedValue = linkedHashMap.remove(key);
        linkedHashMap.put(key, removedValue);
        return removedValue;
    }

    public void put(int key, int value) {
        if(!linkedHashMap.isEmpty()) {
            if (linkedHashMap.containsKey(key)) {
                linkedHashMap.remove(key);
            } else if (linkedHashMap.size() == capacity) {
                linkedHashMap.remove(linkedHashMap.keySet().iterator().next());
            }
        }
        linkedHashMap.put(key, value);
    }

    public static void main(String[] args){
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // return 1
        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // returns -1 (not found)
        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // return -1 (not found)
        System.out.println(lRUCache.get(3));    // return 3
        System.out.println(lRUCache.get(4));    // return 4
    }
}
