package com.moralok.algorithm4th.ch4graphs;

import java.util.LinkedList;

/**
 * 有向环
 *
 * @author moralok
 * @since 2020/9/21
 */
public class DirectedCycle {

    private boolean[] marked;

    private int[] edgeTo;

    private boolean[] onStack;

    private LinkedList<Integer> cycle;

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int i = 0; i < G.V(); i++) {
            if (!marked[i]) {
                // 注意未触及的才需要搜索
                dfs(G, i);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        onStack[v] = true;
        for (int w : G.adj(v)) {
            if (hasCycle()) {
                return;
            }
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            } else if (onStack[w]) {
                // 如果该点在调用栈上
                cycle = new LinkedList<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
