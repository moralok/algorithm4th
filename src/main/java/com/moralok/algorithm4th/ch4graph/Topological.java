package com.moralok.algorithm4th.ch4graph;

/**
 * 拓扑排序
 *
 * @author moralok
 * @since 2020/9/21
 */
public class Topological {

    private Iterable<Integer> order;

    public Topological(Digraph G) {
        DirectedCycle cycleFinder = new DirectedCycle(G);
        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(G);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
