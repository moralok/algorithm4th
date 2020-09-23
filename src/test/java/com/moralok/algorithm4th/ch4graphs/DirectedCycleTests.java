package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21
 */
public class DirectedCycleTests {

    private final String tinyDG = "edu/princeton/cs/algs4/data/tinyDG.txt";

    @Test
    public void testDirectedCycle() {
        Digraph digraph = new Digraph(new In(tinyDG));
        DirectedCycle directedCycle = new DirectedCycle(digraph);
        // 后进先出
        System.out.println(directedCycle.cycle());
    }
}
