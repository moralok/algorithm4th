package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

/**
 * @author moralok
 * @since 2020/9/24 2:48 下午
 */
public class EdgeWeightedDigraphTests {

    private final String tinyEWD = "edu/princeton/cs/algs4/data/tinyEWD.txt";

    @Test
    public void testEdgeWeightedDigraph() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(tinyEWD));
        System.out.println(G);
    }
}
