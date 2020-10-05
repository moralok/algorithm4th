package com.moralok.algorithm4th.ch5strings;

/**
 * 低位优先的字符串排序
 * 是一种适用于一般应用的线性时间排序算法
 *
 * @author moralok
 * @since 2020/10/5
 */
public class LSD {

    public static void sort(String[] a, int W) {
        // 通过前W个字符将a[]排序
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];
        for (int d = W - 1; d >= 0; d--) {
            int[] count = new int[R + 1];
            // 计算出现频率
            for (String s : a) {
                count[s.charAt(d) + 1]++;
            }
            // 将频率转换为索引
            for (int r = 0; r < R; r++) {
                count[r + 1] += count[r];
            }
            // 将元素分类
            for (String s : a) {
                aux[count[s.charAt(d)]++] = s;
            }
            // 回写
            System.arraycopy(aux, 0, a, 0, N);
        }

    }
}
