package com.moralok.algorithm4th.ch3searching;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author moralok
 * @since 2020/10/9 11:03 上午
 */
class SequentialSearchingSTTests {

    private String tinyTale;
    private String tale;
    private String leipzig1M;

    @BeforeEach
    void setUp() {
        tinyTale = "edu/princeton/cs/algs4/data/tinyTale.txt";
        tale = "edu/princeton/cs/algs4/data/tale.txt";
        // 文件太大，不做上传
        leipzig1M = "edu/princeton/cs/algs4/data/leipzig1M.txt";
    }

    @Test
    void testFrequencyCounter() {
        int minLength = 8;
        In in = new In(tale);
        SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
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
        StdOut.println(max + " " + st.get(max));
        assertEquals(max, "business");
        assertEquals(st.get(max), 122);
    }
}
