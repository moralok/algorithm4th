package com.moralok.algorithm4th.other;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    private final Map<String, Node> cache;
    private final Map<Integer, DoubleLinkedList> frequencyMap;
    private int minFrequency;
    private final int capacity;

    public LFUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("illegal argument: capacity = " + capacity);
        }
        cache = new HashMap<>();
        frequencyMap = new HashMap<>();
        minFrequency = 0;
        this.capacity = capacity;
    }

    public void put(String key, Integer value) {
        if (cache.containsKey(key)) {
            // 如果缓存已存在，更新缓存和频率
            Node node = cache.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            if (cache.size() >= capacity) {
                // 如果达到最大容量，删除最少使用的缓存项
                DoubleLinkedList l1 = frequencyMap.get(minFrequency);
                Node lfu = l1.pop();
                if (lfu != null) {
                    cache.remove(lfu.key);
                }
            }
            // 添加新缓存并更新频率哈希表
            Node node = new Node(key, value);
            cache.put(key, node);
            minFrequency = 1;
            DoubleLinkedList l2 = frequencyMap.getOrDefault(1, new DoubleLinkedList());
            l2.append(node);
            frequencyMap.putIfAbsent(1, l2);
        }
    }

    public Integer get(String key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            updateFrequency(node);
            return node.value;
        }
        return null;
    }

    private void updateFrequency(Node node) {
        // 获取节点的频率并将其从相应的双向链表上移除
        int frequency = node.frequency;
        DoubleLinkedList l1 = frequencyMap.get(frequency);
        l1.remove(node);
        if (l1.isEmpty()) {
            // 如果双向链表为空，则从频率哈希表中移除
            frequencyMap.remove(frequency);
            if (minFrequency == frequency) {
                // 如果被移除的频率正是当前最小频率，则最小频率加1
                minFrequency++;
            }
        }
        // 将节点添加到新频率映射的双向链表上
        frequency = ++node.frequency;
        DoubleLinkedList l2 = frequencyMap.getOrDefault(frequency, new DoubleLinkedList());
        l2.append(node);
        frequencyMap.putIfAbsent(frequency, l2);
    }

    private static class Node {
        private final String key;
        private Integer value;
        private int frequency;
        private Node prev;
        private Node next;

        Node(String key, Integer value) {
            this.key = key;
            this.value = value;
            frequency = 1;
        }
    }

    private static class DoubleLinkedList {
        private final Node head;
        private final Node tail;

        DoubleLinkedList() {
            head = new Node(null, 0);
            tail = new Node(null, 0);
            head.next = tail;
            tail.prev = head;
        }

        void append(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.prev = null;
            node.next = null;
        }

        Node pop() {
            if (isEmpty()) {
                return null;
            }
            Node node = tail.prev;
            remove(node);
            return node;
        }

        boolean isEmpty() {
            return head.next == tail;
        }
    }
}