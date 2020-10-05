package com.moralok.algorithm4th.ch5strings;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * @author moralok
 * @since 2020/10/5
 */
class LSDTests {

    private String[] a;

    @BeforeEach
    void setUp() {
        String license = "edu/princeton/cs/algs4/data/licenses.txt";
        In in = new In(license);
        a = in.readAllStrings();
    }

    @Test
    void testLSD() {
        LSD.sort(a, 7);
        StdOut.println(Arrays.toString(a));
    }
}
