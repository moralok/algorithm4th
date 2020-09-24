package com.moralok.algorithm4th.ch4graphs;

import com.moralok.algorithm4th.ch2sorting.MinPQ;

import java.util.LinkedList;

/**
 * 最短路径的 Dijkstra 算法（Lazy 版本）
 * 和 Prim 算法比对考虑，感觉确实有启发
 *
 * @author moralok
 * @since 2020/9/24 3:25 下午
 */
public class LazyDijkstraSP {

    private DirectedEdge[] edgeTo;

    private double[] distTo;

    private MinPQ<DirectedEdge> pq;

    public LazyDijkstraSP(EdgeWeightedDigraph G, int s) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < G.V(); i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        pq = new MinPQ<>(G.E());
        relax(G, s);
        while (!pq.isEmpty()) {
            relax(G, pq.delMin().to());
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                pq.insert(e);
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
