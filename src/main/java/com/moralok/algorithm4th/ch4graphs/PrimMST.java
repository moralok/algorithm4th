package com.moralok.algorithm4th.ch4graphs;

import com.moralok.algorithm4th.ch2sorting.IndexMinPQ;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 最小生成树的 Prim 算法（即时版本）
 *
 * @author moralok
 * @since 2020/9/23 10:00 上午
 */
public class PrimMST {

    private boolean[] marked;

    private IndexMinPQ<Double> pq;

    private Edge[] edgeTo;

    private double[] distTo;

    private double weight;

    public PrimMST(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        weight = 0.0;
        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[0] = 0.0;
        pq.insert(1, 0.0);
        while (!pq.isEmpty()) {
            weight += pq.min();
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // indexMinPQ中索引从1开始！！！
        v--;
        // 将顶点添加到树中，更新数据
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }
            if (e.weight() < distTo[w]) {
                // 连接 w 和树的最佳边 Edge 变为 e
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w + 1)) {
                    pq.change(w + 1, e.weight());
                } else {
                    pq.insert(w + 1, e.weight());
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Set<Edge> mst = new HashSet<>(edgeTo.length);
        for (int i = 0; i < edgeTo.length; i++) {
            if (edgeTo[i] != null) {
                mst.add(edgeTo[i]);
            }
        }
        return mst;
    }

    /**
     * 即时策略维护总权重
     *
     * @return
     */
    public double weight() {
        return weight;
    }
}
