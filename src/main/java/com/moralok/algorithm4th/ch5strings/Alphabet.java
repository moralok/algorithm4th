package com.moralok.algorithm4th.ch5strings;

import java.util.Arrays;

/**
 * @author moralok
 * @since 2020/10/5
 */
public class Alphabet {

    /**
     *  The binary alphabet { 0, 1 }.
     */
    public static final Alphabet BINARY = new Alphabet("01");

    /**
     *  The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final Alphabet OCTAL = new Alphabet("01234567");

    /**
     *  The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    /**
     *  The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    /**
     *  The DNA alphabet { A, C, T, G }.
     */
    public static final Alphabet DNA = new Alphabet("ACGT");

    /**
     *  The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     *  The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     *  The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     *  The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     *  The ASCII alphabet (0-127).
     */
    public static final Alphabet ASCII = new Alphabet(128);

    /**
     *  The extended ASCII alphabet (0-255).
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /**
     *  The Unicode 16 alphabet (0-65,535).
     */
    public static final Alphabet UNICODE16      = new Alphabet(65536);

    /**
     * the characters in the alphabet
     */
    private char[] alphabet;

    /**
     * indices
     */
    private int[] inverse;

    /**
     * the radix of the alphabet
     */
    private final int R;

    /**
     * 根据 s 中的字符创建一张新的字母表
     *
     * @param s 字符串
     */
    public Alphabet(String s) {
        // check that alphabet contains no duplicate chars
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (unicode[c]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            }
            unicode[c] = true;
        }
        alphabet = s.toCharArray();
        R = s.length();
        inverse = new int[Character.MAX_VALUE];
        Arrays.fill(inverse, -1);
        // can't use char since R can be as big as 65,536
        for (int i = 0; i < R; i++) {
            inverse[alphabet[i]] = i;
        }
    }

    public Alphabet(int radix) {
        this.R = radix;
        alphabet = new char[R];
        inverse = new int[R];
        // can't use char since R can be as big as 65,536
        for (int i = 0; i < R; i++) {
            alphabet[i] = (char) i;
        }
        for (int i = 0; i < R; i++) {
            inverse[i] = i;
        }
    }

    public Alphabet() {
        this(256);
    }

    /**
     * 获取字母表中索引位置的字符
     *
     * @param index 索引
     * @return 字符
     */
    public char toChar(int index) {
        if (index <0 || index >= R) {
            throw new IllegalArgumentException("index must be between 0 and " + R + ": " + index);
        }
        return alphabet[index];
    }

    /**
     * 获取字符 c 的索引，在 0 到 R-1 之间
     *
     * @param c 字符
     * @return 字符 c 的索引
     */
    public int toIndex(char c) {
        if (c >= inverse.length || inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return inverse[c];
    }

    /**
     * c 在字母表中吗
     *
     * @param c 字符
     * @return 是或否
     */
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    /**
     * 基数（字母表中的字符数量）
     *
     * @return 基数
     */
    public int radix() {
        return R;
    }

    /**
     * 表示一个索引所需的比特数
     *
     * @return
     */
    public int lgR() {
        int lgR = 0;
        for (int i = R - 1; i >= 1; i /= 2) {
            lgR++;
        }
        return lgR;
    }

    /**
     * 将 s 转换为 R 进制的整数
     *
     * @param s 字符串
     * @return R 进制的整数数组
     */
    public int[] toIndices(String s) {
        char[] source = s.toCharArray();
        int[] target = new int[s.length()];
        for (int i = 0; i < source.length; i++) {
            target[i] = toIndex(source[i]);
        }
        return target;
    }

    /**
     * 将 R 进制的整数数组转换为基于该字母表的字符串
     *
     * @param indices 整数数组
     * @return 字符串
     */
    public String toChars(int[] indices) {
        StringBuilder sb = new StringBuilder(indices.length);
        for (int index : indices) {
            sb.append(toChar(index));
        }
        return sb.toString();
    }
}
