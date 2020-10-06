package com.moralok.algorithm4th.ch5strings;

import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author moralok
 * @since 2020/10/6
 */
class TrieSTTests {

    private TrieST<Integer> st;

    @BeforeEach
    void setUp() {
        st = new TrieST<>();
        st.put("she", 0);
        st.put("sells", 1);
        st.put("sea", 2);
        st.put("shells", 3);
        st.put("by", 4);
        st.put("the", 5);
        st.put("sea", 6);
        st.put("shore", 7);
    }

    @Test
    void testGet() {
        // 命中的查找
        Integer actual = st.get("shells");
        assertEquals(3, actual);
        // 未命中
        actual = st.get("shell");
        assertNull(actual);
        // 终止于内部结点
        actual = st.get("she");
        assertEquals(0, actual);
    }

    @Test
    void testSize() {
        assertEquals(7, st.size());
    }

    @Test
    void testKeys() {
        StdOut.println(st.keys());
    }

    @Test
    void testDelete() {
        st.delete("shore");
    }

    @Test
    void testKeysWithPrefix() {
        StdOut.println(st.keysWithPrefix("sh"));
    }

    @Test
    void testKeysThatMatch() {
        StdOut.println(st.keysThatMatch("s.e"));
    }
}
