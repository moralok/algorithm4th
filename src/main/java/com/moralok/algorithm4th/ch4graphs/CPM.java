package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * 优先级限制下的并行任务调度问题的关键路径方法
 * Critical Path Method
 *
 * @author moralok
 * @since 2020/9/24 5:36 下午
 */
public class CPM {

    private int N;

    private AcyclicLP acyclicLP;

    public CPM(In in) {
        N = in.readInt();
        in.readLine();
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(2 * N + 2);
        int s = 2 * N, t = 2 * N +1;
        for (int i = 0; i < N; i++) {
            String[] a = in.readLine().split("\\s+");
            double duration = Double.parseDouble(a[0]);
            G.addEdge(new DirectedEdge(i, i + N, duration));
            G.addEdge(new DirectedEdge(s, i, 0.0));
            G.addEdge(new DirectedEdge(i + N, t, 0.0));
            // 文件和书中有出入，多了一个值表示后继者个数
            for (int j = 2; j < a.length; j++) {
                int successor = Integer.parseInt(a[j]);
                G.addEdge(new DirectedEdge(i + N, successor, 0.0));
            }
        }

        this.acyclicLP = new AcyclicLP(G, s);
    }

    public void print() {
        StdOut.println(" job   start  finish");
        StdOut.println("--------------------");
        for (int i = 0; i < N; i++) {
            StdOut.printf("%4d %7.1f %7.1f\n", i, acyclicLP.distTo(i), acyclicLP.distTo(i+N));
        }
        StdOut.printf("Finish time: %7.1f\n", acyclicLP.distTo(2 * N + 1));
    }
}
