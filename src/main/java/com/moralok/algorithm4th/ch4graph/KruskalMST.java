package com.moralok.algorithm4th.ch4graph;

import com.moralok.algorithm4th.ch1fundamentals.WeightedQuickUnionUF;
import com.moralok.algorithm4th.ch2sort.MinPQ;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 最小生成树的 Kruskal 算法
 *
 * @author moralok
 * @since 2020/9/23 3:24 下午
 */
public class KruskalMST {

    private Queue<Edge> mst;

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new LinkedList<>();
        MinPQ<Edge> pq = new MinPQ<>(G.E() + 1);
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            mst.offer(edge);
            uf.union(v, w);
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
