package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

/**
 * @author moralok
 * @since 2020/9/21 5:42 下午
 */
public class DigraphTests {

    private final String tinyDG = "edu/princeton/cs/algs4/data/tinyDG.txt";

    @Test
    public void testDigraph() {
        Digraph digraph = new Digraph(new In(tinyDG));
        System.out.println(digraph);
    }

    @Test
    public void testDirectedDfs() {
        Digraph digraph = new Digraph(new In(tinyDG));
        Set<Integer> sources = new HashSet<>();
        sources.add(1);
        sources.add(2);
        sources.add(6);
        DirectedDFS reachable = new DirectedDFS(digraph, sources);
        for (int v = 0; v < digraph.V(); v++) {
            if (reachable.marked(v)) {
                System.out.print(v + " ");
            }
        }
    }

    @Test
    public void testTransitiveClosure() {
        System.out.println("测试顶点对的可达性");
        Digraph digraph = new Digraph(new In(tinyDG));
        TransitiveClosure transitiveClosure = new TransitiveClosure(digraph);
        System.out.println(transitiveClosure.reachable(4, 11));
        System.out.println(transitiveClosure.reachable(4, 0));
    }
}
