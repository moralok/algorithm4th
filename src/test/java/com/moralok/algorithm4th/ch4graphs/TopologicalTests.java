package com.moralok.algorithm4th.ch4graphs;

import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21
 */
public class TopologicalTests {

    private final String jobs = "edu/princeton/cs/algs4/data/jobs.txt";

    @Test
    public void testTopological() {
        String filename = jobs;
        String separator = "/";
        SymbolDigraph sg = new SymbolDigraph(filename, separator);
        Topological topological = new Topological(sg.G());
        for (int v : topological.order()){
            System.out.println(v + " " + sg.name(v));
        }
    }
}
