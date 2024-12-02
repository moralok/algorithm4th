package com.moralok.algorithm4th.other;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private final int capacity;
    private final Map<String, Node> cache;
    private final Node head;
    private final Node tail;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        // 初始化哈希表
        cache = new HashMap<>();
        // 初始化双向链表
        head = new Node(null, 0);
        tail = new Node(null, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void put(String key, Integer value) {
        if (cache.containsKey(key)) {
            // 删除旧节点
            remove(cache.get(key));
        }

        // 插入新节点
        Node node = new Node(key, value);
        add(node);
        cache.put(key, node);

        if (cache.size() > capacity) {
            // 如果缓存已满
            Node lru = tail.prev;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    public Integer get(String key) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            // 移动到头部
            remove(node);
            add(node);
            return node.value;
        }
        return null;
    }

    /**
     * 将节点添加到头部，表示最近使用
     *
     * @param node 节点
     */
    private void add(Node node) {
        node.next =  head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    /**
     * 将节点删除
     *
     * @param node 节点
     */
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.next = null;
        node.prev = null;
    }

    private static class Node {
        private final String key;
        private final Integer value;
        private Node prev;
        private Node next;

        Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

}
