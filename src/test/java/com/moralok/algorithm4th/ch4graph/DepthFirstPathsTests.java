package com.moralok.algorithm4th.ch4graph;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21 4:29 下午
 */
public class DepthFirstPathsTests {

    private final String tinyCG = "edu/princeton/cs/algs4/data/tinyCG.txt";

    @Test
    public void testDepthFirstPaths() {
        System.out.println("深度优先搜索寻找路径");
        Graph G = new Graph(new In(tinyCG));
        int s = 0;
        DepthFirstPaths paths = new DepthFirstPaths(G, s);
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
