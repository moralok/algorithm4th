package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @author moralok
 * @since 2020/9/21 4:32 下午
 */
public class ConnectedComponentTests {

    private final String tinyG = "edu/princeton/cs/algs4/data/tinyG.txt";

    @SuppressWarnings("unchecked")
    @Test
    public void testConnectedComponent() {
        System.out.println("深度优先搜索寻找连通分量");
        Graph G = new Graph(new In(tinyG));
        ConnectedComponent cc = new ConnectedComponent(G);
        int count = cc.count();
        System.out.println(count + " 连通分量");
        Set<Integer>[] component = (Set<Integer>[]) new Set[count];
        for (int i = 0; i < count; i++) {
            component[i] = new HashSet<>();
        }
        for (int i = 0; i < G.V(); i++) {
            component[cc.id(i)].add(i);
        }
        for (int i = 0; i < count; i++) {
            System.out.println("id " + i + " 连通分量中的顶点 " + component[i]);
        }
    }
}
