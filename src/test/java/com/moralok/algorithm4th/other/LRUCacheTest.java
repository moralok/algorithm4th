package com.moralok.algorithm4th.other;

import org.junit.jupiter.api.Test;

import java.util.Objects;

class LRUCacheTest {

    @Test
    void putAndGet() {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put("a", 1);
        assert Objects.equals(lruCache.get("a"), 1);
        lruCache.put("b", 2);
        lruCache.put("c", 3);
        assert lruCache.get("a") == null;
        lruCache.put("b", 4);
        lruCache.put("d", 5);
        assert lruCache.get("c") == null;
        assert Objects.equals(lruCache.get("b"), 4);
    }
}