package com.old.apiAssert.check;

import com.old.apiAssert.Holder;
import com.old.apiAssert.api.StandardApiAssert;
import com.old.apiAssert.exception.ApiAssertException;
import org.junit.Test;

/**
 *
 */
public class FirstApiAssertTest {


    @Test(expected = ApiAssertException.class)
    public void testFirstApiAssert() {

        System.out.println();
        StandardApiAssert<Object, FirstApiAssert, String> apiAssert = FirstApiAssert.create();
        Holder<Boolean> hodler = Holder.holder();
        apiAssert.isNull(new Object(), "对象为空")
                .isEmpty("不为空", "对象不存在")
                .holder(hodler)
                .ifFail(() -> {
                    System.out.println("此时已经失败");
                })
                .ifSuccess(() -> {
                    System.out.println("此时仍无异常");
                })
                .handler(flag -> {
                    System.out.println("当前是否出现异常：" + flag);
                }).process(() -> {
                    System.out.println("业务逻辑");
                })
                .handler((flag, errorMsg) -> {
                    System.out.println("当前有无异常：" + flag + "异常信息为：" + errorMsg);
                })
                .failThrow(ApiAssertException::new);
        if (hodler.getData()) {
            System.out.println("此时无异常");
        }
        apiAssert.isNull(null, "对象为空");
        Object transitionResult = apiAssert.process(() -> {
            Object o = new Object();
            System.out.println("返回对象: " + o);
            return o;
        });
        apiAssert.isNull(transitionResult, "校验过程中出现为空的对象");
        System.out.println("校验结束");
        apiAssert.self().failThrow(ApiAssertException::new);
    }

}
