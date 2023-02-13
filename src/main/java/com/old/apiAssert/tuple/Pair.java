package com.old.apiAssert.tuple;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Pair<K,V> {

    private final K k;
    private final V v;

    public static <K,V> Pair<K,V> create(K k, V v) {
        return new Pair<>(k, v);
    }

}
