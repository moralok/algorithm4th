package com.moralok.algorithm4th.ch1fundamentals;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/23 2:53 下午
 */
public class UFTests {

    private final String tinyUF = "edu/princeton/cs/algs4/data/tinyUF.txt";
    private final String mediumUF = "edu/princeton/cs/algs4/data/mediumUF.txt";
    // 鉴于文件过大，不上传github
    private final String largeUF = "edu/princeton/cs/algs4/data/largeUF.txt";

    /**
     * 测试到70万条边时，weighted quick union 也没优于 quick union。。。
     */
    private final int max = 100000;

    @Test
    public void testQuickFind() {
        StdOut.println("测试 quick-find");
        In[] ins = new In[2];
        ins[0] = new In(tinyUF);
        ins[1] = new In(mediumUF);
        for (int i = 0; i < ins.length; i++) {
            Stopwatch stopwatch = new Stopwatch();
            In in = ins[i];
            StdOut.println("第 " + i + " 轮");
            int N = in.readInt();
            QuickFindUF uf = new QuickFindUF(N);
            int count = 0;
            while (!in.isEmpty()) {
                count++;
                if (count >= max) {
                    break;
                }
                int p = in.readInt();
                int q = in.readInt();
                if (uf.connected(p, q)) {
                    continue;
                }
                uf.union(p, q);
            }
            StdOut.println(uf.count() + " components " + count + " 整数对");
            StdOut.println("耗时 " + stopwatch.elapsedTime());
        }
    }

    @Test
    public void testQuickUnion() {
        StdOut.println("测试 quick-union");
        In[] ins = new In[3];
        ins[0] = new In(tinyUF);
        ins[1] = new In(mediumUF);
        ins[2] = new In(largeUF);
        for (int i = 0; i < ins.length; i++) {
            Stopwatch stopwatch = new Stopwatch();
            In in = ins[i];
            StdOut.println("第 " + i + " 轮");
            int N = in.readInt();
            QuickUnionUF uf = new QuickUnionUF(N);
            int count = 0;
            while (!in.isEmpty()) {
                count++;
                if (count >= max) {
                    break;
                }
                int p = in.readInt();
                int q = in.readInt();
                if (uf.connected(p, q)) {
                    continue;
                }
                uf.union(p, q);
            }
            StdOut.println(uf.count() + " components " + count + " 整数对");
            StdOut.println("耗时 " + stopwatch.elapsedTime());
        }
    }

    @Test
    public void testWeightedQuickUnionUF() {
        StdOut.println("测试 weighted-quick-union");
        In[] ins = new In[3];
        ins[0] = new In(tinyUF);
        ins[1] = new In(mediumUF);
        ins[2] = new In(largeUF);
        for (int i = 0; i < ins.length; i++) {
            Stopwatch stopwatch = new Stopwatch();
            In in = ins[i];
            StdOut.println("第 " + i + " 轮");
            int N = in.readInt();
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
            int count = 0;
            while (!in.isEmpty()) {
                count++;
                if (count >= max) {
                    break;
                }
                int p = in.readInt();
                int q = in.readInt();
                if (uf.connected(p, q)) {
                    continue;
                }
                uf.union(p, q);
            }
            StdOut.println(uf.count() + " components " + count + " 整数对");
            StdOut.println("耗时 " + stopwatch.elapsedTime());
        }
    }
}
