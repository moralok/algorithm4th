package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

/**
 * @author moralok
 * @since 2020/9/21 4:31 下午
 */
public class BreadthFirstPathsTests {

    private final String tinyCG = "edu/princeton/cs/algs4/data/tinyCG.txt";

    @Test
    public void testBreadthFirstPaths() {
        System.out.println("广度优先搜索寻找路径");
        Graph G = new Graph(new In(tinyCG));
        int s = 0;
        BreadthFirstPaths paths = new BreadthFirstPaths(G, s);
        for (int i = 0; i < G.V(); i++) {
            System.out.print(s + " to " + i + " : ");
            if (paths.hasPathTo(i)) {
                for (int x : paths.pathTo(i)) {
                    if (x == s) {
                        System.out.print(x);
                    } else {
                        System.out.print("-" + x);
                    }
                }
                System.out.println();
            }
        }
    }
}
