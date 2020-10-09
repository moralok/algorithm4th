package com.moralok.algorithm4th.ch3searching;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 顺序查找（基于无序链表）
 *
 * @author moralok
 * @since 2020/10/9 10:17 上午
 */
public class SequentialSearchST<Key, Value> {

    /**
     * the linked list of key-value pairs
     */
    private Node first;

    /**
     * number of key-value pairs
     */
    private int n;

    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if the {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    /**
     * Inserts the specified key-value pair into the symbol table,
     * overwriting the old value with the new value if the symbol table contains the specified key.
     * Deletes the specified key (and its associated value) from this symbol table
     * if the specified value is {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("first argument to put() is null");
        }
        if (val == null) {
            delete(key);
            return;
        }
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    /**
     * Remove the specified key and its associated value from the symbol table
     * (if the key is in the symbol table).
     *
     * @param key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to delete() is null");
        }
        first = delete(first, key);
    }

    // delete key in linked list beginning at Node x
    // warning: function call stack too large if table is large
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * Returns true if the symbol table contains the specified key
     *
     * @param key the key
     * @return {@code true} if the symbol table contains the {@code key};
     *         {@code false} otherwise
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to contains() is null");
        }
        return get(key) != null;
    }

    /**
     * Returns the number of the key-value pairs in this symbol table
     *
     * @return the number of the key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Returns true if the symbol table is empty.
     *
     * @return {@code true} if the symbol table is empty;
     *         {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st} ,
     * use the foreach notation: {@code for(Key key : st.keys())}.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {
        Queue<Key> queue = new LinkedList<>();
        for (Node x = first; x != null; x = x.next) {
            queue.add(x.key);
        }
        return queue;
    }

    /**
     * a helper linked list data type
     */
    private class Node {

        Key key;

        Value val;

        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }
}
