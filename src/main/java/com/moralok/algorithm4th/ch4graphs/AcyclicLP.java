package com.moralok.algorithm4th.ch4graphs;

import org.junit.Assert;

import java.util.LinkedList;

/**
 * 无环加权有向图的最长路径算法
 *
 * @author moralok
 * @since 2020/9/24 5:28 下午
 */
public class AcyclicLP {

    private DirectedEdge[] edgeTo;

    private double[] distTo;

    public AcyclicLP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.NEGATIVE_INFINITY;
        }
        distTo[s] = 0.0;
        Topological topological = new Topological(G);
        Assert.assertTrue("该图不是无环图", topological.isDAG());
        for (int v : topological.order()) {
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] < distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
            }
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        LinkedList<DirectedEdge> path = new LinkedList<>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }
}
