package io.github.min1854.apiAssert.tuple;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Tuple4<T1, T2, T3, T4> implements Serializable {
    private final T1 t1;
    private final T2 t2;
    private final T3 t3;
    private final T4 t4;

    public static <T1, T2, T3, T4> Tuple4<T1, T2, T3, T4> of(T1 t1, T2 t2, T3 t3, T4 t4) {
        return new Tuple4<>(t1, t2, t3, t4);
    }
}
