package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

/**
 * @author moralok
 * @since 2020/9/16
 */
public class GraphTests {

    private final String tinyG = "edu/princeton/cs/algs4/data/tinyG.txt";

    @Test
    public void testGraph() {
        Graph G = new Graph(new In(tinyG));
        System.out.println(G);
    }
}
