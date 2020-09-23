package com.moralok.algorithm4th.ch2sorting;

import edu.princeton.cs.algs4.In;
import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/22 4:27 下午
 */
public class MinPQTests {

    private final String _1Kints = "edu/princeton/cs/algs4/data/1Kints.txt";

    @Test
    public void testMinPQ() {
        String file = _1Kints;
        In in = new In(file);
        int[] nums = in.readAllInts();
        MinPQ<Integer> pq = new MinPQ<>(nums.length + 1);
        for (int num : nums) {
            pq.insert(num);
        }
        assert pq.isMinHeap();
        System.out.println(pq.size() + " left on the pq");
    }
}
