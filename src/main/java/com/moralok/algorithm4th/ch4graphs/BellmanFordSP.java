package com.moralok.algorithm4th.ch4graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于队列的 Bellman-Ford 算法
 *
 * @author moralok
 * @since 2020/9/25 3:06 下午
 */
public class BellmanFordSP {

    /**
     * 从起点到某个顶点的路径长度
     */
    private double[] distTo;

    /**
     * 从起点到某个顶点的最后一条边
     */
    private DirectedEdge[] edgeTo;

    /**
     * 该顶点是否存在于队列中
     */
    private boolean[] onQ;

    /**
     * 即将被放松的顶点
     */
    private Queue<Integer> queue;

    /**
     * relax() 的调用次数
     */
    private int cost;

    /**
     * edgeTo[] 是否又负权重环
     */
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph G, int s) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        onQ = new boolean[G.V()];
        queue = new LinkedList<>();
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;
        queue.add(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.remove();
            onQ[v] = false;
            relax(G, v);
        }
    }

    private void relax(EdgeWeightedDigraph G, int v) {
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.add(w);
                    onQ[w] = true;
                }
            }
        }
        if (cost++ % G.V() == 0) {
            findNegativeCycle();
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

    public boolean hasNegativeCycle() {
        return cycle != null;
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph digraph = new EdgeWeightedDigraph(V);
        for (DirectedEdge edge : edgeTo) {
            if (edge != null) {
                digraph.addEdge(edge);
            }
        }
        EdgeWeightedDirectedCycle cycleFinder = new EdgeWeightedDirectedCycle(digraph);
        cycle = cycleFinder.cycle();
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }
}
