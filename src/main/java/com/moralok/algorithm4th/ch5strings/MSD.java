package com.moralok.algorithm4th.ch5strings;

import com.moralok.algorithm4th.ch2sorting.Insertion;

/**
 * 高位优先的字符串排序
 *
 * @author moralok
 * @since 2020/10/5
 */
public class MSD {

    private static int R = 256;

    private static final int M = 2;

    private static String[] aux;

    private static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    private static void sort(String[] a, int lo, int hi, int d) {
        // 以第d个字符为键将a[lo]至a[hi]排序
        if (hi <= lo + M) {
            Insertion.sort(a, lo, hi, d);
            return;
        }
        int[] count = new int[R + 2];
        // 计算频率
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        // 将频率转换为索引
        for (int i = 0; i < R + 1; i++) {
            count[i+1] += count[i];
        }
        // 数据分类
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
        }
        // 回写
        if (hi + 1 - lo >= 0) {
            System.arraycopy(aux, lo, a, lo, hi + 1 - lo);
        }
        // 递归地以每个字符为键进行排序
        for (int i = 0; i < R; i++) {
            sort(a, lo + count[i], lo + count[i + 1] - 1, d + 1);
        }
    }
}
