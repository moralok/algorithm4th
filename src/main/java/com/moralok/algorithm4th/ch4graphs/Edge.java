package com.moralok.algorithm4th.ch4graphs;

/**
 * 加权边
 *
 * @author moralok
 * @since 2020/9/22 11:34 上午
 */
public class Edge implements Comparable<Edge> {

    private final int v;

    private final int w;

    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if (vertex == v) {
            return w;
        } else if (vertex == w){
            return v;
        } else {
            throw new RuntimeException("Inconsistent edge");
        }
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.weight(), that.weight());
    }

    @Override
    public String toString() {
        return String.format("%d-%d %.2f", v, w, weight);
    }
}
