package com.company;

import java.util.List;
import java.util.Map;

public interface LastTouchMap<K, V> extends Map<K, V> {
    public int size();

    V put(K key, V value);

    public List<V> getLast(int n);
}