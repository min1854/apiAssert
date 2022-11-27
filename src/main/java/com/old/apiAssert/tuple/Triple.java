package com.old.apiAssert.tuple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Triple<LEFT, MIDDLE, RIGHT> implements Serializable {
    private LEFT left;
    private MIDDLE middle;
    private RIGHT right;

    public static <LEFT, MIDDLE, RIGHT> Triple<LEFT, MIDDLE, RIGHT> create(LEFT left, MIDDLE middle, RIGHT right) {
        return new Triple<>(left, middle, right);
    }
}
