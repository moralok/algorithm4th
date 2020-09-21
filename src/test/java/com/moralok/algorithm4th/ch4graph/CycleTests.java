package com.moralok.algorithm4th.ch4graph;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21 4:33 下午
 */
public class CycleTests {

    private final String tinyG = "edu/princeton/cs/algs4/data/tinyG.txt";

    @Test
    public void testCycle() {
        System.out.println("深度优先搜索检查是否为无环图");
        Graph G = new Graph(new In(tinyG));
        Cycle cycle = new Cycle(G);
        System.out.println("是否有环 " + cycle.hasCycle());
    }
}
