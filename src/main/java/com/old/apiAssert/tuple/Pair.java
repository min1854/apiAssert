package com.old.apiAssert.tuple;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Pair<K, V> implements Serializable {

    private final K key;
    private final V value;

    public static <K, V> Pair<K, V> of(K k, V v) {
        return new Pair<>(k, v);
    }

}
