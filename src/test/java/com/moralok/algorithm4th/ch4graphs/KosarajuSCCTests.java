package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * 计算强连通分量的 Kosaraju 算法
 *
 * @author moralok
 * @since 2020/9/22 10:37 上午
 */
public class KosarajuSCCTests {

    private final String tinyDG = "edu/princeton/cs/algs4/data/tinyDG.txt";

    @SuppressWarnings("unchecked")
    @Test
    public void testStronglyConnected() {
        System.out.println("计算强连通分量的 Kosaraju 算法");
        Digraph G = new Digraph(new In(tinyDG));
        KosarajuSCC scc = new KosarajuSCC(G);
        int count = scc.count();
        System.out.println(count + " 连通分量");
        Set<Integer>[] component = (Set<Integer>[]) new Set[count];
        for (int i = 0; i < count; i++) {
            component[i] = new HashSet<>();
        }
        for (int i = 0; i < G.V(); i++) {
            component[scc.id(i)].add(i);
        }
        for (int i = 0; i < count; i++) {
            System.out.println("id " + i + " 连通分量中的顶点 " + component[i]);
        }
    }
}
