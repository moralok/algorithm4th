package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;

import java.util.HashSet;
import java.util.Set;

/**
 * 加权有向图
 *
 * @author moralok
 * @since 2020/9/24 2:33 下午
 */
public class EdgeWeightedDigraph {

    private int V;

    private int E;

    private Set<DirectedEdge>[] adj;

    @SuppressWarnings("unchecked")
    public EdgeWeightedDigraph(int V) {
        this.V = V;
        adj = (Set<DirectedEdge>[]) new Set[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new HashSet<>();
        }
    }

    public EdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            double weight = in.readDouble();
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            addEdge(edge);
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(DirectedEdge edge) {
        int v = edge.from();
        adj[v].add(edge);
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Set<DirectedEdge> set = new HashSet<>(E);
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj[v]) {
                set.add(e);
            }
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append("\n");
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }
}
