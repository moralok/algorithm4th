package com.moralok.algorithm4th.ch2sorting;

/**
 * 插入排序
 *
 * @author moralok
 * @since 2020/9/15 1:38 下午
 */
public class Insertion extends BaseSortTemplate {

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            for (int j = i; j >= 1 && less(arr[j], arr[j - 1]); j--) {
                exch(arr, j, j - 1);
            }
        }
    }

    public static void sort(String[] arr, int lo, int hi, int d) {
        for (int i = lo + 1; i <= hi; i++) {
            for (int j = i; j >= lo + 1 && less(arr[j], arr[j - 1], d); j--) {
                exch(arr, j, j - 1);
            }
        }
    }

    private static boolean less(String v, String w, int d) {
        return v.substring(d).compareTo(w.substring(d)) < 0;
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {3,6,5,2,1,1,6,7,3};
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}
