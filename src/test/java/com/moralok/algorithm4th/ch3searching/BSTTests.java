package com.moralok.algorithm4th.ch3searching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author moralok
 * @since 2020/10/9 5:46 下午
 */
class BSTTests {

    private String tinyST;
    private String tale;
    private BST<String, Integer> st;

    @BeforeEach
    void setUp() {
        tinyST = "edu/princeton/cs/algs4/data/tinyST.txt";
        tale = "edu/princeton/cs/algs4/data/tale.txt";
        In in = new In(tinyST);
        st = new BST<>();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1);
            }
        }
    }

    @Test
    void testFrequencyCounter() {
        int minLength = 8;
        In in = new In(tale);
        BST<String, Integer> st = new BST<>();
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() < minLength) {
                continue;
            }
            if (st.contains(word)) {
                st.put(word, st.get(word) + 1);
            } else {
                st.put(word, 1);
            }
        }
        String max = " ";
        st.put(max, 0);
        for (String key : st.keys()) {
            if (st.get(key) > st.get(max)) {
                max = key;
            }
        }
        assertEquals(max, "business");
        assertEquals(st.get(max), 122);
    }

    @Test
    void testKeys() {
        System.out.println(st.keys());
    }

    @Test
    void testSize() {
        assertEquals(10, st.size());
    }

    @Test
    void testMin() {
        assertEquals("A", st.min());
    }

    @Test
    void testMax() {
        assertEquals("X", st.max());
    }

    @Test
    void testFloor() {
        assertEquals("A", st.floor("A"));
        assertEquals("X", st.floor("X"));
        assertEquals("X", st.floor("Y"));
        assertEquals("M", st.floor("N"));
    }

    @Test
    void testCeiling() {
        assertNull(st.ceiling("a"));
        assertEquals("A", st.ceiling("A"));
        assertEquals("X", st.ceiling("X"));
        assertNull(st.ceiling("Y"));
        assertEquals("L", st.ceiling("I"));
    }

    @Test
    void testSelect() {
        assertEquals("A", st.select(0));
    }

    @Test
    void testRank() {
        assertEquals(4, st.rank("L"));
    }

    @Test
    void testDeleteMin() {
        assertTrue(st.contains("A"));
        st.deleteMin();
        assertFalse(st.contains("A"));
    }

    @Test
    void testDeleteMax() {
        assertTrue(st.contains("X"));
        st.deleteMax();
        assertFalse(st.contains("X"));
    }

    @Test
    void testDelete() {
        assertTrue(st.contains("L"));
        st.delete("L");
        assertFalse(st.contains("L"));
    }
}
