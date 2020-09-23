package com.moralok.algorithm4th.ch1fundamentals;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * 加权 quick-union
 *
 * @author moralok
 * @since 2020/9/23 2:45 下午
 */
public class WeightedQuickUnionUF {

    /**
     * 同一个分量中另一个触点的名称，可以是自己
     */
    private int[] id;

    /**
     * 分量数量
     */
    private int count;

    /**
     * 各个根结点所对应的分量的大小
     */
    private int[] sz;

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        Stopwatch stopwatch = new Stopwatch();
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
        StdOut.println("分配" + N + "大小的数组耗时 " + stopwatch.elapsedTime());
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            // 如果已经在相同的分量中则不需要进行操作
            return;
        }
        if (sz[pRoot] > sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] = sz[pRoot];
        }
        count--;
    }

    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return count;
    }
}
