package com.moralok.algorithm4th.ch1fundamentals;

/**
 * quick-find
 *
 * @author moralok
 * @since 2020/9/23 2:19 下午
 */
public class QuickFindUF {

    /**
     * 分量id（以触点为索引）
     */
    private int[] id;

    /**
     * 分量数量
     */
    private int count;

    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pid = find(p);
        int qid = find(q);
        if (pid == qid) {
            // 如果已经在相同的分量中则不需要进行操作
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
        count--;
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public int count() {
        return count;
    }
}
