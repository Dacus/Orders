package com.company;

import java.util.List;

/**
 * Created by intern on 3/23/15.
 */

public interface LastTouchMap<K, V> {
    public int size();

    public void put(K key, V value);

    public List<V> getLast(int n);
}