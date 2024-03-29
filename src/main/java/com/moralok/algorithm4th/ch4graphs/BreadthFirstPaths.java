package com.moralok.algorithm4th.ch4graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 广度优先搜索寻找路径
 *
 * @author moralok
 * @since 2020/9/17 9:37 上午
 */
public class BreadthFirstPaths {

    private boolean[] marked;

    private int count;

    private final int s;

    private int[] edgeTo;

    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        this.s = s;
        edgeTo = new int[G.V()];
        bfs(G, s);
    }

    private void bfs(Graph G, int s) {
        marked[s] = true;
        count++;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()) {
            int v = queue.remove();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    count++;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public int count() {
        return count;
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            // 防御没有触及的顶点
            return null;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            // 注意循环的条件
            stack.push(x);
        }
        stack.push(s);
        return stack;
    }
}
