package com.moralok.algorithm4th.ch4graphs;

import edu.princeton.cs.algs4.In;

import java.util.HashMap;
import java.util.Map;

/**
 * 符号有向图
 *
 * @author moralok
 * @since 2020/9/21
 */
public class SymbolDigraph {

    /**
     * 符号名->索引
     */
    private Map<String, Integer> st;

    /**
     * 索引->符号名
     */
    private String[] keys;

    /**
     * 有向图
     */
    private Digraph G;

    public SymbolDigraph(String stream, String sp) {
        st = new HashMap<>();
        In in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            for (String s : a) {
                if (!st.containsKey(s)) {
                    st.put(s, st.size());
                }
            }
        }
        keys = new String[st.size()];
        for (String name : st.keySet()) {
            keys[st.get(name)] = name;
        }
        G = new Digraph(st.size());
        in = new In(stream);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(sp);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                G.addEdge(v, st.get(a[i]));
            }
        }
    }

    public boolean contains(String s) {
        return st.containsKey(s);
    }

    public int index(String s) {
        return st.get(s);
    }

    public String name(int v) {
        return keys[v];
    }

    public Digraph G() {
        return G;
    }
}
