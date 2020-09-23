package com.moralok.algorithm4th.ch4graphs;

import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/21 4:35 下午
 */
public class SymbolGraphTests {

    private final String routes = "edu/princeton/cs/algs4/data/routes.txt";

    @Test
    public void testSymbolGraph() {
        System.out.println("测试符号图");
        String filename = routes;
        String delimeter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimeter);
        Graph G = sg.G();
        String source = "JFK";
        System.out.println(source);
        for (int w : G.adj(sg.index(source))) {
            System.out.println(" " + sg.name(w));
        }
        source = "LAX";
        System.out.println(source);
        for (int w : G.adj(sg.index(source))) {
            System.out.println(" " + sg.name(w));
        }
    }

    @Test
    public void testDegreesOfSeparation() {
        System.out.println("测试符号图");
        String filename = routes;
        String delimeter = " ";
        SymbolGraph sg = new SymbolGraph(filename, delimeter);
        String source = "JFK";
        System.out.println("source " + source);
        if (!sg.contains(source)) {
            System.out.println(source + " not in database.");
            return;
        }
        int s = sg.index(source);
        Graph G = sg.G();
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, s);

        String sink = "LAS";
        System.out.println("sink " + sink);
        if (sg.contains(sink)) {
            int t = sg.index(sink);
            if (bfs.hasPathTo(t)) {
                for (int v : bfs.pathTo(t)) {
                    System.out.println(" " + sg.name(v));
                }
            } else {
                System.out.println("Not int database.");
            }
        }
    }
}
