package com.moralok.algorithm4th.ch2sorting;

/**
 * 基于堆的优先队列
 *
 * @author moralok
 * @since 2020/9/22 4:01 下午
 */
public class MinPQ<Key extends Comparable<Key>> {

    private Key[] pq;
    private int N = 0;

    @SuppressWarnings("unchecked")
    public MinPQ(int maxN) {
        pq = (Key[]) new Comparable[maxN + 1];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public void insert(Key key) {
        pq[++N] = key;
        swim(N);
    }

    public Key min() {
        return pq[1];
    }

    public Key delMin() {
        Key min = pq[1];
        exch(1, N);
        pq[N] = null;
        N--;
        sink(1);
        return min;
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private boolean greater(int i, int j) {
        return pq[i].compareTo(pq[j]) > 0;
    }

    private void exch(int i, int j) {
        Key tmp = pq[i];
        pq[i] = pq[j];
        pq[j] = tmp;
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

    public boolean isMinHeap() {
        for (int i = 1; i <= N; i++) {
            if (pq[i] == null) {
                return false;
            }
        }
        for (int i = N + 1; i < pq.length; i++) {
            if (pq[i] != null) {
                return false;
            }
        }
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
        if (k > N) {
            return true;
        }
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= N && greater(k , left)) {
            return false;
        }
        if (right <= N && greater(k, right)) {
            return false;
        }
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }
}
