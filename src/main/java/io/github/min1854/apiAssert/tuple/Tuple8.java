package io.github.min1854.apiAssert.tuple;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> implements Serializable {
    private final T1 t1;
    private final T2 t2;
    private final T3 t3;
    private final T4 t4;
    private final T5 t5;
    private final T6 t6;
    private final T7 t7;
    private final T8 t8;

    public static <T1, T2, T3, T4, T5, T6, T7, T8> Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> of(T1 t1, T2 t2, T3 t3, T4 t4,
                                                                                             T5 t5, T6 t6, T7 t7, T8 t8) {
        return new Tuple8<>(t1, t2, t3, t4, t5, t6, t7, t8);
    }
}
