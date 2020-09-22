package com.moralok.algorithm4th.ch4graph;

/**
 * 计算强连通分量的 Kosaraju 算法
 * 
 * @author moralok
 * @since 2020/9/22 10:28 上午
 */
public class KosarajuSCC {

    private boolean[] marked;

    private int[] id;

    private int count;

    public KosarajuSCC(Digraph G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G.reverse());
        for (int v : depthFirstOrder.reversePost()){
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Digraph G, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }

    boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public int id(int v) {
        return id[v];
    }
}
