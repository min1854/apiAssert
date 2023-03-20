package io.github.min1854.apiAssert.check;// package com.old.apiAssert;

import io.github.min1854.apiAssert.enums.AssertEnum;
import io.github.min1854.apiAssert.exception.EnumMessageException;
import org.junit.Test;

/**
 *
 */
public class EnumFunctionApiAssertTest {


    @Test
    public void testFunctionApiAssert() {
        Object obj = new Object();
        EnumFunctionApiAssert<AssertEnum> apiAssert =
                EnumFunctionApiAssert.create(EnumMessageException::new)
                .isNull(obj, AssertEnum.NULL_PARAM)
                .isEmpty(obj, AssertEnum.NULL_PARAM)
                .isTrue(false, AssertEnum.CONDITION_IS_TRUE)
                .process(() -> {
                    System.out.println("校验对象信息：" + obj);
                })
                .isFalse(true, AssertEnum.CONDITION_IS_FALSE);
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, AssertEnum.NULL_PARAM);
        System.out.println(apiAssert.getClass());
        System.out.println("校验结束");
    }


}
