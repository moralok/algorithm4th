package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Set;

/**
 * 有向图
 *
 * @author moralok
 * @since 2020/9/21 5:37 下午
 */
public class Digraph {

    /**
     * 顶点数
     */
    private final int V;

    /**
     * 边数
     */
    private int E;

    /**
     * 邻接表
     */
    private Set<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Digraph(int V) {
        this.V = V;
        adj = (Set<Integer>[]) new Set[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public Digraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            addEdge(v, w);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph digraph = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                digraph.addEdge(w, v);
            }
        }
        return digraph;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj[v]) {
                s.append(w).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
