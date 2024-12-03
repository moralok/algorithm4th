package com.moralok.algorithm4th.other;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

class LFUCacheTest {

    private LFUCache lfuCache;

    @BeforeEach
    void setUp() {
        lfuCache = new LFUCache(2);
    }

    @Test
    void wrongCapacity() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new LFUCache(0));
    }

    @Test
    void putAndGet() {
        lfuCache.put("a", 1);
        Integer res = lfuCache.get("a");
        Assertions.assertEquals(1, res);
    }

    @Test
    void evict() {
        lfuCache.put("a", 1);
        lfuCache.put("b", 2);
        lfuCache.get("a");
        lfuCache.put("c", 3);
        Assertions.assertEquals(1, lfuCache.get("a"));
        Assertions.assertNull(lfuCache.get("b"));
    }

    @Test
    void updateFrequency() throws NoSuchFieldException, IllegalAccessException {
        lfuCache.put("a", 1);
        lfuCache.put("b", 2);
        lfuCache.get("a");
        lfuCache.put("b", 3);
        Field field = LFUCache.class.getDeclaredField("minFrequency");
        field.setAccessible(true);
        Object o = field.get(lfuCache);
        Assertions.assertEquals(2, o);
    }
}