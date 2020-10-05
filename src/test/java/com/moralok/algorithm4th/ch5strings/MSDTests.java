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
class MSDTests {

    private String[] a;

    @BeforeEach
    void setUp() {
        String shells = "edu/princeton/cs/algs4/data/shells.txt";
        String shellsST = "edu/princeton/cs/algs4/data/shellsST.txt";
        In in = new In(shellsST);
        a = in.readAllStrings();
    }

    @Test
    void testMSD() {
        MSD.sort(a);
        StdOut.println(Arrays.toString(a));
    }
}
