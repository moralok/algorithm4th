package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/22 1:59 下午
 */
public class EdgeWeightedGraphTests {

    private final String tinyEWG = "edu/princeton/cs/algs4/data/tinyEWG.txt";

    @Test
    public void testEdgeWeightedGraph() {
        EdgeWeightedGraph G = new EdgeWeightedGraph(new In(tinyEWG));
        System.out.println(G);
    }
}
