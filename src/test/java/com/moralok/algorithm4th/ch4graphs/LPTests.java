package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/24 5:30 下午
 */
public class LPTests {

    private final String tinyEWDAG = "edu/princeton/cs/algs4/data/tinyEWDAG.txt";

    @Test
    public void testAcyclicSP() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(tinyEWDAG));
        int s = 5;
        AcyclicLP sp = new AcyclicLP(G, s);
        for (int t = 0; t < G.V(); t++) {
            StdOut.print(s + " to " + t);
            StdOut.printf("(%4.2f): ", sp.distTo(t));
            if (sp.hasPathTo(t)) {
                for (DirectedEdge e : sp.pathTo(t)) {
                    StdOut.print(e + " ");
                }
            }
            StdOut.println();
        }
    }
}
