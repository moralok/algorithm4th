package com.moralok.algorithm4th.ch2sort;

import org.junit.Test;

/**
 * @author moralok
 * @since 2020/9/22 6:17 下午
 */
public class IndexMinPQTests {

    @Test
    public void testIndexMinPQ() {
        IndexMinPQ<String> indexMinPQ = new IndexMinPQ<>(10);
        indexMinPQ.insert(2, "two");
        indexMinPQ.insert(1, "one");
        indexMinPQ.insert(3, "three");
        while (!indexMinPQ.isEmpty()) {
            System.out.println(indexMinPQ.min() + " " +  indexMinPQ.delMin());
        }
    }
}
