package com.moralok.algorithm4th.ch4graph;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21 5:42 下午
 */
public class DigraphTests {

    private final String tinyDG = "edu/princeton/cs/algs4/data/tinyDG.txt";

    @Test
    public void testDigraph() {
        Digraph digraph = new Digraph(new In(tinyDG));
        System.out.println(digraph);
    }
}
