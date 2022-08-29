package com.old.apiAssert.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityEnum {

    NULL_OBJ(500, "空对象")

    ;
    private Integer value;
    private String desc;
}
