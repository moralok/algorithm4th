package com.moralok.algorithm4th.ch4graphs;

import java.util.LinkedList;

/**
 * 加权有向环
 *
 * @author moralok
 * @since 2020/9/24 4:35 下午
 */
public class EdgeWeightedDirectedCycle {

    private boolean[] marked;

    private DirectedEdge[] edgeTo;

    private boolean[] onStack;

    private LinkedList<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                // 注意未触及的才需要搜索
                dfs(G, i);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            if (hasCycle()) {
                return;
            }
            int w = e.to();
            if (!marked[w]) {
                edgeTo[w] = e;
                dfs(G, w);
            } else if (onStack[w]) {
                // 如果该点在调用栈上
                cycle = new LinkedList<>();
                while (e.from() != w) {
                    cycle.push(e);
                    e = edgeTo[e.from()];
                }
                cycle.push(e);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
