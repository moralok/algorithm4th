package com.moralok.algorithm4th.ch4graph;

/**
 * 有向图中的可达性
 *
 * @author moralok
 * @since 2020/9/21
 */
public class DirectedDfs {

    private boolean[] marked;

    public DirectedDfs(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    public DirectedDfs(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int s : sources) {
            dfs(G, s);
        }
    }

    public void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean marked(int v) {
        return marked[v];
    }
}
