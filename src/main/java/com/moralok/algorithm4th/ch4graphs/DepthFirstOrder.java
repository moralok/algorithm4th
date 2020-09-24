package com.moralok.algorithm4th.ch4graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 基于深度优先搜索的顶点排序
 *
 * @author moralok
 * @since 2020/9/21
 */
public class DepthFirstOrder {

    private boolean[] marked;

    /**
     * 顶点的前序排列
     */
    private Queue<Integer> pre;

    /**
     * 顶点的后序排列
     */
    private Queue<Integer> post;

    /**
     * 顶点的逆后序排列
     */
    private LinkedList<Integer> reversePost;

    public DepthFirstOrder(Digraph G) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new LinkedList<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    public DepthFirstOrder(EdgeWeightedDigraph G) {
        pre = new LinkedList<>();
        post = new LinkedList<>();
        reversePost = new LinkedList<>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
            }
        }
    }

    private void dfs(Digraph G, int v) {
        pre.add(v);
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    private void dfs(EdgeWeightedDigraph G, int v) {
        pre.add(v);
        marked[v] = true;
        for (DirectedEdge e : G.adj(v)) {
            int w = e.to();
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        post.add(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
