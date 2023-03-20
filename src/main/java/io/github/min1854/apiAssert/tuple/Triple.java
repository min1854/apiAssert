package io.github.min1854.apiAssert.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Triple<LEFT, MIDDLE, RIGHT> implements Serializable {
    private final LEFT left;
    private final MIDDLE middle;
    private final RIGHT right;

    public static <LEFT, MIDDLE, RIGHT> Triple<LEFT, MIDDLE, RIGHT> of(LEFT left, MIDDLE middle, RIGHT right) {
        return new Triple<>(left, middle, right);
    }
}
