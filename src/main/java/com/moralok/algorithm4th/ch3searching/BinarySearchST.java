package com.moralok.algorithm4th.ch3searching;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * 二分查找（基于有序数组）
 *
 * @author moralok
 * @since 2020/10/9 11:49 上午
 */
@SuppressWarnings("unchecked")
public class BinarySearchST<Key extends Comparable<Key>, Value> {

    private Key[] keys;

    private Value[] vals;

    private int n;

    /**
     * Initializes an empty symbol table with the specified initial capacity
     *
     * @param capacity the maximum capacity
     */
    public BinarySearchST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        vals = (Value[]) new Object[capacity];
    }

    /**
     * Returns the value associated with the given key in this symbol table.
     *
     * @param key the key
     * @return the value associated with the given key in this symbol table
     *         and {@code null} if the key is not in the symbol table
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to get() is null");
        }
        if (isEmpty()) {
            return null;
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return vals[i];
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
        int i = rank(key);

        // key is already in table
        if (i < n && key.compareTo(keys[i]) == 0) {
            vals[i] = val;
            return;
        }

        // insert new key-value pair
        if (n == keys.length) {
            resize(2 * keys.length);
        }
        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            vals[j] = vals[j - 1];
        }
        keys[i] = key;
        vals[i] = val;
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
        if (isEmpty()) {
            return;
        }

        // compute rank
        int i = rank(key);

        // key not in table
        if (i == n || key.compareTo(keys[i]) != 0) {
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            vals[j] = vals[j + 1];
        }
        n--;

        // to avoid loitering
        keys[n] = null;
        vals[n] = null;

        // resize if 1/4 full
        if (n > 0 && n == keys.length / 4) {
            resize(keys.length/2);
        }
    }

    /**
     * Removes the smallest key and associated value from this symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(min());
    }

    /**
     * Removes the largest key and associated value from this symbol table.
     *
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol table underflow error");
        }
        delete(max());
    }

    /**
     * Returns the number of keys in this symbol table strictly less than the {@code key}.
     *
     * @param key the key
     * @return the number of keys in this symbol table strictly less than the {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public int rank(Key key) {
        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) {
                hi = mid - 1;
            } else if (cmp > 0) {
                lo = mid + 1;
            } else {
                return mid;
            }
        }
        return lo;
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
        return n == 0;
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
     * Returns the smallest key in this symbol table.
     *
     * @return the smallest key in this symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("called min() with empty symbol table");
        }
        return keys[0];
    }

    /**
     * Returns the largest key in this symbol table.
     *
     * @return the largest key in this symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("called max() with empty symbol table");
        }
        return keys[n - 1];
    }

    /**
     * Returns the kth smallest key in this symbol table.
     *
     * @param k the order statistic
     * @return the {@code k}th smallest key in this symbol table
     * @throws IllegalArgumentException unless {@code k} is between 0 and n-1
     */
    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new IllegalArgumentException("called select() with invalid argument: " + k);
        }
        return keys[k];
    }

    /**
     * Returns the largest key in this symbol table less than or equal to {@code key}.
     *
     * @param key the key
     * @return the largest key in this symbol table less than or equal to {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key floor(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to floor() is null");
        }
        int i = rank(key);
        if (i < n && key.compareTo(keys[i]) == 0) {
            return keys[i];
        }
        if (i == 0) {
            return null;
        }
        return keys[i - 1];
    }

    /**
     * Returns the smallest key in this symbol table greater than or equal to {@code key}.
     *
     * @param key the key
     * @return the smallest key in this symbol table greater than or equal to {@code key}
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public Key ceiling(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("argument to ceiling() is null");
        }
        int i = rank(key);
        if (i == n) {
            return null;
        }
        return keys[i];
    }

    /**
     * Returns all keys in this symbol table in the given range.
     *
     * @param lo minimum endpoint
     * @param hi maximum endpoint
     * @return all keys in this symbol table between {@code lo} (inclusive) and {@code hi} (inclusive)
     * @throws IllegalArgumentException if either {@code lo} or {@code hi} is {@code null}
     */
    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) {
            throw new IllegalArgumentException("first argument to keys() is null");
        }
        if (hi == null) {
            throw new IllegalArgumentException("second argument to keys() is null");
        }
        Queue<Key> queue = new LinkedList<>();
        if (lo.compareTo(hi) > 0) {
            return queue;
        }
        queue.addAll(Arrays.asList(keys).subList(rank(lo), rank(hi)));
        if (contains(hi)) {
            queue.add(hi);
        }
        return queue;
    }

    /**
     * Returns all keys in the symbol table as an {@code Iterable}.
     * To iterate over all of the keys in the symbol table named {@code st} ,
     * use the foreach notation: {@code for(Key key : st.keys())}.
     *
     * @return all keys in the symbol table
     */
    public Iterable<Key> keys() {
        return new LinkedList<>(Arrays.asList(keys).subList(0, n));
    }

    // resize the underlying arrays
    private void resize(int capacity) {
        assert capacity >= n;
        Key[]   tmpKeys = (Key[])   new Comparable[capacity];
        Value[] tmpVals = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tmpKeys[i] = keys[i];
            tmpVals[i] = vals[i];
        }
        vals = tmpVals;
        keys = tmpKeys;
    }
}
