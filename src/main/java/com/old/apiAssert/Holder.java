package com.old.apiAssert;

import lombok.Data;

@Data
public class Holder {

    private Boolean flag;

    public static Holder holder() {
        return new Holder();
    }

}
