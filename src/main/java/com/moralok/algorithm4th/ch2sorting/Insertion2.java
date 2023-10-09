package com.moralok.algorithm4th.ch2sorting;

/**
 * 插入排序
 * 优化：在内循环中将较大的元素都向右移动而不总是交换两个元素，这样访问数组的次数就能减半
 * 每次外循环，访问数组的次数最多为 1 + (1 + 2) * i + 1
 * 访问数组的次数最多为 3 * n * (n - 1) / 2 + 2 * (n - 1)
 *
 * @author moralok
 * @since 2023/10/09 22:15 下午
 */
public class Insertion2 extends BaseSortTemplate {

    public static void sort(Comparable[] arr) {
        int len = arr.length;
        for (int i = 1; i < len; i++) {
            Comparable t = arr[i];  // 1 次访问数组
            int j;
            for (j = i; j >= 1 && less(t, arr[j - 1]); j--) {   // 1 次访问数组
                arr[j] = arr[j - 1];    // 2 次访问数组
            }
            arr[j] = t; // 1 次访问数组
        }
    }

    public static void main(String[] args) {
        Integer[] arr = new Integer[] {3,6,5,2,1,1,6,7,3};
        sort(arr);
        assert isSorted(arr);
        show(arr);
    }
}
