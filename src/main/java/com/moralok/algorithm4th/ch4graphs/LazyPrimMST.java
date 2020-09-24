package com.moralok.algorithm4th.ch4graphs;

import com.moralok.algorithm4th.ch2sorting.MinPQ;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最小生成树的 Prim 算法的延时实现
 *
 * @author moralok
 * @since 2020/9/22 3:34 下午
 */
public class LazyPrimMST {

    private boolean[] marked;

    private Queue<Edge> mst;

    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        mst = new LinkedList<>();
        pq = new MinPQ<>(G.E());

        visit(G, 0);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.offer(e);
            if (!marked[v]) {
                visit(G, v);
            }
            if (!marked[w]) {
                visit(G, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * 延时计算
     *
     * @return
     */
    public double weight() {
        double sum = 0;
        for (Edge e : edges()) {
            sum += e.weight();
        }
        return sum;
    }
}
