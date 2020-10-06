package com.moralok.algorithm4th.ch5strings;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author moralok
 * @since 2020/10/6
 */
public class TrieST<Value> {

    private static int R = 256;

    private Node root;

    private static class Node {
        private Object val;
        private Node[] next = new Node[R];
    }

    /**
     * 键key所对应的值（如果键不存在则返回null）
     *
     * @param key 键
     * @return 值
     */
    @SuppressWarnings("unchecked")
    public Value get(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        Node x = get(root, key, 0);
        if (x == null) {
            return null;
        }
        return (Value) x.val;
    }

    private Node get(Node x, String key, int d) {
        // 返回以x作为根结点的子单词查找树中与key相关联的值
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return x;
        }
        // 找到第d个字符所对应的子单词查找树
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }

    /**
     * 表中是否保存着key的值
     *
     * @param key 键
     * @return 是或者否
     */
    public boolean contains(String key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * 向表中插入键值对（如果值为null则删除键key）
     *
     * @param key 键
     * @param val 值
     */
    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        // 如果key存在于以x为根结点的子单词查找树中则更新与它相关联的值
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            // 即时size的实现 n++
            x.val = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    /**
     * 键值对的数量
     *
     * @return 数量
     */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        int cnt = 0;
        if (x.val != null) {
            cnt++;
        }
        for (char c = 0; c < R; c++) {
            cnt += size(x.next[c]);
        }
        return cnt;
    }

    /**
     * 符号表是否为空
     * @return 结果
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * 查找所有键
     *
     * @return 键
     */
    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    /**
     * 所有以s为前缀的键
     *
     * @param pre 前缀
     * @return 键
     */
    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new LinkedList<>();
        collect(get(root, pre, 0), pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.add(pre);
        }
        for (char c = 0; c < R; c++) {
            collect(x.next[c], pre + c, q);
        }
    }

    /**
     * 所有和s匹配的键
     *
     * @param pat 字符串模式
     * @return
     */
    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new LinkedList<>();
        collect(root, "", pat, q);
        return q;
    }

    private void collect(Node x, String pre, String pat, Queue<String> q) {
        int d = pre.length();
        if (x == null) {
            return;
        }
        if (d == pat.length() && x.val != null) {
            q.add(pre);
        }
        if (d == pat.length()) {
            return;
        }
        char next = pat.charAt(d);
        for (char c = 0; c < R; c++) {
            if (next == '.' || next == c) {
                collect(x.next[c], pre + c, pat, q);
            }
        }
    }

    /**
     * s的前缀中最长的键
     *
     * @param s 字符串
     * @return 键
     */
    public String longestPrefixOf(String s) {
        int length = search(root, s, 0, 0);
        return s.substring(0, length);
    }

    private int search(Node x, String s, int d, int length) {
        if (x == null) {
            return length;
        }
        if (x.val != null) {
            length = d;
        }
        if (d == s.length()) {
            return length;
        }
        char c = s.charAt(d);
        return search(x.next[c], s, d + 1, length);
    }

    /**
     * 删除键key（和它的值）
     *
     * @param key 键
     */
    public void delete(String key) {
        root = delete(root, key , 0);
    }

    private Node delete(Node x, String key, int d) {
        // 真漂亮
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            // 即时size的实现 n--
            x.val = null;
        } else {
            char c = key.charAt(d);
            x.next[c] = delete(x.next[c], key, d + 1);
        }
        if (x.val != null) {
            return x;
        }
        for (char c = 0; c < R; c++) {
            if (x.next[c] != null) {
                return x;
            }
        }
        return null;
    }
}
