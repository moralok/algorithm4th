package com.moralok.algorithm4th.ch4graphs;

/**
 * 顶点对的可达性
 *
 * @author moralok
 * @since 2020/9/22 10:50 上午
 */
public class TransitiveClosure {

    private DirectedDFS[] all;

    public TransitiveClosure(Digraph G) {
        all = new DirectedDFS[G.V()];
        for (int v = 0; v < G.V(); v++) {
            all[v] = new DirectedDFS(G, v);
        }
    }

    public boolean reachable(int v, int w) {
        return all[v].marked(w);
    }
}
