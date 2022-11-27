package com.old.apiAssert.tuple;

public class Pair<K,V> extends javafx.util.Pair<K,V> {


    public static <K,V> Pair<K,V> create(K k, V v) {
        return new Pair<>(k, v);
    }

    /**
     * Creates a new pair
     *
     * @param key   The key for this pair
     * @param value The value to use for this pair
     */
    public Pair(K key, V value) {
        super(key, value);
    }
}
