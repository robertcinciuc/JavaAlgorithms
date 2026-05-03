package robertcinciuc.problems.leetcode;

import java.util.*;

public class LRUCache {

//    private final int capacity;
//    private final LinkedHashMap<Integer, Integer> linkedHashMap;
//
//    public LRUCache(int capacity) {
//        this.capacity = capacity;
//        this.linkedHashMap = new LinkedHashMap<>();
//    }
//
//    public int get(int key) {
//        if (!linkedHashMap.containsKey(key)) {
//            return -1;
//        }
//        Integer removedValue = linkedHashMap.remove(key);
//        linkedHashMap.put(key, removedValue);
//        return removedValue;
//    }
//
//    public void put(int key, int value) {
//        if(!linkedHashMap.isEmpty()) {
//            if (linkedHashMap.containsKey(key)) {
//                linkedHashMap.remove(key);
//            } else if (linkedHashMap.size() == capacity) {
//                linkedHashMap.remove(linkedHashMap.keySet().iterator().next());
//            }
//        }
//        linkedHashMap.put(key, value);
//    }

    private static class Node {
        public Node next;
        public Node previous;
        public int data;
        public int key;

        public Node(int key, int data) {
            this.key = key;
            this.data = data;
        }
    }

    private final int capacity;
    private final Map<Integer, Node> map;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(-1, -1);
        this.tail = new Node(-1, -1);

        head.next = tail;
        tail.previous = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        removeNode(node);
        addNodeToTail(node);

        return node.data;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node existingNode = map.get(key);
            existingNode.data = value;
            removeNode(existingNode);
            addNodeToTail(existingNode);
        } else {
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addNodeToTail(newNode);

            if (map.size() > capacity) {
                Node lruNode = head.next;
                removeNode(lruNode);
                map.remove(lruNode.key);
            }
        }
    }

    private void removeNode(Node node) {
        Node prevNode = node.previous;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.previous = prevNode;
    }

    private void addNodeToTail(Node node) {
        Node prevEnd = tail.previous;

        prevEnd.next = node;
        node.previous = prevEnd;
        node.next = tail;
        tail.previous = node;
    }

    public static void main(String[] args) {
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(1, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1, 2=2}
//        System.out.println(lRUCache.get(1));    // return 1
//        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        System.out.println(lRUCache.get(2));    // returns -1 (not found)
//        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
//        System.out.println(lRUCache.get(1));    // return -1 (not found)
//        System.out.println(lRUCache.get(3));    // return 3
//        System.out.println(lRUCache.get(4));    // return 4

//        LRUCache lRUCache = new LRUCache(2);
//        System.out.println(lRUCache.get(2));    // return 1
//        lRUCache.put(2, 6); // cache is {1=1}
//        System.out.println(lRUCache.get(1));    // return 1
//        lRUCache.put(1, 5); // cache is {1=1, 2=2}
//        lRUCache.put(1, 2); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
//        System.out.println(lRUCache.get(1));    // returns -1 (not found)
//        System.out.println(lRUCache.get(2));    // returns -1 (not found)
//
//        LRUCache lRUCache = new LRUCache(1);
//        lRUCache.put(2, 1); // cache is {1=1}
//        System.out.println(lRUCache.get(2));    // return 1
//        lRUCache.put(3, 2); // cache is {1=1, 2=2}
//        System.out.println(lRUCache.get(2));    // return 1
//        System.out.println(lRUCache.get(3));    // returns -1 (not found)
//
//        LRUCache lRUCache = new LRUCache(2);
//        lRUCache.put(2, 1); // cache is {1=1}
//        lRUCache.put(2, 2); // cache is {1=1}
//        System.out.println(lRUCache.get(2));    // return 1
//        lRUCache.put(1, 1); // cache is {1=1, 2=2}
//        lRUCache.put(4, 1); // cache is {1=1, 2=2}
//        System.out.println(lRUCache.get(2));    // return 1

//        LRUCache lRUCache = new LRUCache(1);
//        System.out.println(lRUCache.get(6));    // return 1
//        System.out.println(lRUCache.get(8));    // return 1
//        lRUCache.put(12, 1); // cache is {1=1}
//        System.out.println(lRUCache.get(2));    // return 1
//        lRUCache.put(15, 11); // cache is {1=1}
//        lRUCache.put(5, 2); // cache is {1=1}
//        lRUCache.put(1, 15); // cache is {1=1}
//        lRUCache.put(4, 2); // cache is {1=1}
//        System.out.println(lRUCache.get(4));    // return 1
//        lRUCache.put(15,15); // cache is {1=1}

//        [[2],[2,1],[3,2],[3],[2],[4,3],[2],[3],[4]]
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(2, 1); // cache is {1=1}
        lRUCache.put(3, 2); // cache is {1=1}
        System.out.println(lRUCache.get(3));    // return 1
        System.out.println(lRUCache.get(2));    // return 1
        lRUCache.put(4, 3); // cache is {1=1}
        System.out.println(lRUCache.get(2));    // return 1
        System.out.println(lRUCache.get(3));    // return 1
        System.out.println(lRUCache.get(4));    // return 1
    }
}
