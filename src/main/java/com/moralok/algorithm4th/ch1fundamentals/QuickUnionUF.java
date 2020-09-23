package com.moralok.algorithm4th.ch1fundamentals;

/**
 * quick-union
 *
 * @author moralok
 * @since 2020/9/23 2:37 下午
 */
public class QuickUnionUF {

    /**
     * 同一个分量中另一个触点的名称，可以是自己
     */
    private int[] id;

    /**
     * 分量数量
     */
    private int count;

    public QuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            // 如果已经在相同的分量中则不需要进行操作
            return;
        }
        id[pRoot] = qRoot;
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
