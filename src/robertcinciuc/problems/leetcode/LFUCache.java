package robertcinciuc.problems.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    public static class FreqNode {
        public int key;
        public int freq;
        public FreqNode prev;
        public FreqNode next;

        public FreqNode(int key) {
            this.key = key;
            this.freq = 1;
        }
    }

    public int capacity;
    public final FreqNode start;
    public final FreqNode end;
    public Map<Integer, Integer> dataMap = new HashMap<>();
    public Map<Integer, FreqNode> freqMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.start = new FreqNode(-1);
        this.end = new FreqNode(-1);
        start.next = end;
        end.prev = start;
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!dataMap.containsKey(key)) {
            return -1;
        }

        FreqNode foundNode = freqMap.get(key);
        foundNode.freq += 1;

        FreqNode iter = foundNode;
        while (iter != end && iter.freq <= foundNode.freq) {
            iter = iter.next;
        }

        //Update old location
        foundNode.prev.next = foundNode.next;
        foundNode.next.prev = foundNode.prev;

        //Add to new location
        foundNode.next = iter;
        foundNode.prev = iter.prev;
        iter.prev.next = foundNode;
        iter.prev = foundNode;

        return dataMap.get(key);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }

        if (dataMap.containsKey(key)) {
            dataMap.put(key, value);
            get(key);
            return;
        }

        if (dataMap.size() == capacity) {
            dataMap.remove(start.next.key);
            freqMap.remove(start.next.key);
            start.next.next.prev = start;
            start.next = start.next.next;
        }

        dataMap.put(key, value);
        FreqNode freqNode = new FreqNode(key);

        FreqNode iter = start;
        while(iter != end && iter.freq == 1) {
            iter = iter.next;
        }

        freqNode.next = iter;
        freqNode.prev = iter.prev;
        iter.prev.next = freqNode;
        iter.prev = freqNode;

        freqMap.put(key, freqNode);
    }

    public static void defaultTest() {
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        System.out.println(lfuCache.get(1));
        lfuCache.put(3, 3);
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
    }

    public static void test1() {
//        [[2],[3,1],[2,1],[2,2],[4,4],[2]]
        LFUCache lfuCache = new LFUCache(2);
        lfuCache.put(3, 1);
        lfuCache.put(2, 1);
        lfuCache.put(2, 2);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(2));
    }

    public static void test2() {
//        [[3],[1,1],[2,2],[3,3],[4,4],[4],[3],[2],[1],[5,5],[1],[2],[3],[4],[5]]
        LFUCache lfuCache = new LFUCache(3);
        lfuCache.put(1, 1);
        lfuCache.put(2, 2);
        lfuCache.put(3, 3);
        lfuCache.put(4, 4);
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(1));
        lfuCache.put(5, 5);
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(3));
        System.out.println(lfuCache.get(4));
        System.out.println(lfuCache.get(5));
    }

    public static void main(String[] args) {
//        defaultTest();
//        test1();
        test2();
    }
}
