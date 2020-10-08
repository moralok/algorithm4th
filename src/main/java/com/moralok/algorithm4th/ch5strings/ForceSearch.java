package com.moralok.algorithm4th.ch5strings;

/**
 * 暴力子字符串查找算法
 *
 * @author moralok
 * @since 2020/10/7
 */
public class ForceSearch {

    public static int search1(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        for (int i = 0; i <= N - M; i++) {
            int j;
            for (j = 0; j < M; j++) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return N;
    }

    /**
     * 显式回退
     *
     * @param pat 模式
     * @param txt 文本
     * @return 索引
     */
    public static int search2(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        for (i = 0, j = 0; i < N && j < M; i++) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
            } else {
                i = i - j;
                j = 0;
            }
        }
        if (j == M) {
            return i - M;
        } else {
            return N;
        }
    }
}
