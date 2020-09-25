package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/24 3:17 下午
 */
public class SPTests {

    private final String tinyEWD = "edu/princeton/cs/algs4/data/tinyEWD.txt";
    private final String mediumEWD = "edu/princeton/cs/algs4/data/mediumEWD.txt";
    private final String tinyEWDAG = "edu/princeton/cs/algs4/data/tinyEWDAG.txt";
    private final String tinyEWDn = "edu/princeton/cs/algs4/data/tinyEWDn.txt";
    private final String tinyEWDnc = "edu/princeton/cs/algs4/data/tinyEWDnc.txt";

    @Test
    public void testDijkstraSP() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(mediumEWD));
        int s = 0;
        DijkstraSP sp = new DijkstraSP(G, s);
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

    @Test
    public void testLazyDijkstraSP() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(mediumEWD));
        int s = 0;
        LazyDijkstraSP sp = new LazyDijkstraSP(G, s);
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

    @Test
    public void testAcyclicSP() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(tinyEWDAG));
        int s = 5;
        AcyclicSP sp = new AcyclicSP(G, s);
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

    @Test
    public void testBellmanFordSP() {
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(new In(tinyEWDn));
        int s = 0;
        BellmanFordSP sp = new BellmanFordSP(G, s);
        if (sp.hasNegativeCycle()) {
            StdOut.println(sp.negativeCycle());
        } else {
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
}
