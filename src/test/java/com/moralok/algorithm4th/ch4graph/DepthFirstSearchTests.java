package com.moralok.algorithm4th.ch4graph;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21 4:27 下午
 */
public class DepthFirstSearchTests {

    private final String tinyG = "edu/princeton/cs/algs4/data/tinyG.txt";

    @Test
    public void testDepthFirstSearch() {
        System.out.println("深度优先搜索");
        Graph G = new Graph(new In(tinyG));
        int s = 0;
        DepthFirstSearch search = new DepthFirstSearch(G, s);
        System.out.println("与0连通的顶点数量 " + search.count());
        for (int i = 0; i < G.V(); i++) {
            if (search.marked(i)) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
        if (search.count() != G.V()) {
            System.out.print("Not ");
        }
        System.out.println("connected");
    }
}
