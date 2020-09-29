package com.moralok.algorithm4th.ch2sorting;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stopwatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

/**
 * @author moralok
 * @since 2020/9/21 4:45 下午
 */
public class SortTests {

    private final String _1Kints = "edu/princeton/cs/algs4/data/1Kints.txt";
    private final String _8Kints = "edu/princeton/cs/algs4/data/8Kints.txt";
    private final String _32Kints = "edu/princeton/cs/algs4/data/32Kints.txt";
    private final String _1Mints = "edu/princeton/cs/algs4/data/1Mints.txt";


    @Test
    public void testSelection() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        Selection.sort(integers);
        System.out.println(file + " 选择排序，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }

    @Test
    public void testInsertion() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        Insertion.sort(integers);
        System.out.println(file + " 插入排序，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }

    @Test
    public void testShell() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        Shell.sort(integers);
        System.out.println(file + " 希尔排序，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }

    @Test
    public void testMerge() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        Merge.sort(integers);
        System.out.println(file + " 归并排序1，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }

    @Test
    public void testMergeBu() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        MergeBu.sort(integers);
        System.out.println(file + " 归并排序2，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }

    @Test
    public void testQuick() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        Quick.sort(integers);
        System.out.println(file + " 快速排序，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }

    @Test
    public void testHeap() {
        String file = _32Kints;
        In in = new In(file);
        Integer[] integers = Arrays.stream(in.readAllInts()).boxed().toArray(Integer[]::new);
        Assertions.assertFalse(BaseSortTemplate.isSorted(integers));
        Stopwatch stopwatch = new Stopwatch();
        Heap.sort(integers);
        System.out.println(file + " 堆排序，耗时 " + stopwatch.elapsedTime());
        Assertions.assertTrue(BaseSortTemplate.isSorted(integers));
    }
}
