package com.moralok.algorithm4th.ch2sort;

/**
 * 索引优先序列
 *
 * @author moralok
 * @since 2020/9/22 4:55 下午
 */
public class IndexMinPQ<Key extends Comparable<Key>> {

    /**
     * PQ 中的元素数量
     */
    private int N;

    /**
     * 索引二叉堆，从1开始
     */
    private int[] pq;

    /**
     * 逆序：qp[pq[i] = pq[qp[i]] = i
     */
    private int[] qp;

    /**
     * 有优先级的元素
     */
    private Key[] keys;

    @SuppressWarnings("unchecked")
    public IndexMinPQ(int maxN) {
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        keys = (Key[]) new Comparable[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public void insert(int k, Key key) {
        // 各种操作里的顺序和细节，不注意容易出错啊
        N++;
        qp[k] = N;
        pq[N] = k;
        keys[k] = key;
        swim(N);
    }

    public void change(int k, Key key) {
        keys[k] = key;
        swim(qp[k]);
        sink(qp[k]);
    }

    public boolean contains(int k) {
        return qp[k] != -1;
    }

    public void delete(int k) {
        int index = qp[k];
        exch(index, N--);
        swim(index);
        sink(index);
        keys[k] = null;
        qp[k] = -1;
    }

    public Key min() {
        return keys[pq[1]];
    }

    public int minIndex() {
        return pq[1];
    }

    public int delMin() {
        int indexOfMin = pq[1];
        exch(1, N--);
        sink(1);
        keys[pq[N + 1]] = null;
        qp[pq[N + 1]] = -1;
        return indexOfMin;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    private boolean less(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) < 0;
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void exch(int i, int j) {
        int tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }

    private void swim(int k) {
        while (k > 1 && less(k, k / 2)) {
            exch(k, k / 2);
            k /= 2;
        }
    }

    private void sink(int k) {
        while (k <= N / 2) {
            int j = 2 * k;
            if (j + 1 <= N && less(j+1, j)) {
                j++;
            }
            if (less(k, j)) {
                break;
            }
            exch(k, j);
            k = j;
        }
    }
}
