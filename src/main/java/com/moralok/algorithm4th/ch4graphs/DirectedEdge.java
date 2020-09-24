package com.moralok.algorithm4th.ch4graphs;

/**
 * 加权有向边
 *
 * @author moralok
 * @since 2020/9/24 2:27 下午
 */
public class DirectedEdge implements Comparable<DirectedEdge> {

    private double weight;

    private int v;

    private int w;

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return this.weight;
    }

    public int from() {
        return this.v;
    }

    public int to() {
        return w;
    }

    @Override
    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return String.format("%d->%d %.2f", v, w, weight);
    }
}
