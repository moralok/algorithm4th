package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21 4:34 下午
 */
public class TwoColorTests {

    private final String tinyG = "edu/princeton/cs/algs4/data/tinyG.txt";

    @Test
    public void testTwoColor() {
        System.out.println("深度优先搜索检查是否为二分图");
        Graph G = new Graph(new In(tinyG));
        TwoColor twoColor = new TwoColor(G);
        System.out.println("是否为二分图 " + twoColor.isBipartite());
    }
}
