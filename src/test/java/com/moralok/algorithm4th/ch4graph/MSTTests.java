package com.moralok.algorithm4th.ch4graph;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/22 3:49 下午
 */
public class MSTTests {

    private final String tinyEWG = "edu/princeton/cs/algs4/data/tinyEWG.txt";
    private final String mediumEWG = "edu/princeton/cs/algs4/data/mediumEWG.txt";

    @Test
    public void testLazyPrimMST() {
        System.out.println("测试最小生成树的 Prim 算法的延时实现");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(tinyEWG));
        LazyPrimMST lazyPrimMST = new LazyPrimMST(graph);
        System.out.println("最小生成树的边有 " + lazyPrimMST.edges());
        System.out.println("最小生成树的边权重和 " + lazyPrimMST.weight());
    }

    @Test
    public void testPrimMST() {
        System.out.println("测试最小生成树的 Prim 算法的即时实现");
        EdgeWeightedGraph graph = new EdgeWeightedGraph(new In(tinyEWG));
        PrimMST primMST = new PrimMST(graph);
        System.out.println("最小生成树的边有 " + primMST.edges());
        System.out.println("最小生成树的边权重和 " + primMST.weight());
    }
}
