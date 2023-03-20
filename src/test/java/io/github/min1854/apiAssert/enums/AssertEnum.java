package io.github.min1854.apiAssert.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AssertEnum {

    NULL_PARAM(5001, "空参数"),
    NON_NULL_PARAM(5002, "不允许存在"),
    TEST_ENTITY_ID_NULL(5003, "实体 id 为空"),
    TEST_ENTITY_REMOVE(5004, "实体已被删除"),
    TEST_ENTITY_NOT_REMOVE(5005, "实体未删除"),

    CONDITION_IS_TRUE(5006,"条件为真"),
    CONDITION_IS_FALSE(5007,"条件不成立"),


    ;
    private int code;
    private String message;
}
